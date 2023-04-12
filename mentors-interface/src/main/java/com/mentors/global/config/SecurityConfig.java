package com.mentors.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mentors.global.auth.filter.JwtAuthenticationFilter;
import com.mentors.global.auth.filter.LoginAuthenticationFilter;
import com.mentors.global.auth.handler.LoginAuthenticationEntryPoint;
import com.mentors.global.auth.handler.LoginAuthenticationFailureHandler;
import com.mentors.global.auth.handler.LoginAuthenticationSuccessHandler;
import com.mentors.global.auth.handler.LoginDeniedHandler;
import com.mentors.global.auth.jwt.JwtTokenProvider;
import com.mentors.global.auth.provider.LoginAuthenticationProvider;
import com.mentors.user.auth.UserContextService;
import com.mentors.user.authToken.service.AuthService;
import com.mentors.user.user.service.UserReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private static final String[] AUTH_WHITELIST = {
            "/api/v1/users/signup", "/api/v1/users/signin", "/h2-console/*"
    };

    private final UserContextService userContextService;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;
    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserReadService userReadService;
    private final String loginUrl="/api/v1/users/signin";

    @Bean
    public AuthenticationManager authenticationManager(final HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(loginAuthenticationProvider())
                .userDetailsService(userContextService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    @Bean
    protected SecurityFilterChain config(final HttpSecurity http,
                                         final AuthenticationManager authenticationManager) throws Exception {
        http
                .csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .shouldFilterAllDispatcherTypes(false)
                        .requestMatchers(AUTH_WHITELIST)
                        .permitAll()
                        .anyRequest()
                        .authenticated());
        http
                .headers()
                .frameOptions()
                .sameOrigin();
        http
                .addFilterBefore(loginAuthenticationFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter(), LoginAuthenticationFilter.class);

        http
                .exceptionHandling()
                .authenticationEntryPoint(loginAuthenticationEntryPoint())
                .accessDeniedHandler(loginDeniedHandler());

        return http.build();
    }

    @Bean
    public LoginAuthenticationFilter loginAuthenticationFilter(final AuthenticationManager authenticationManager) {
        LoginAuthenticationFilter loginAuthenticationFilter = new LoginAuthenticationFilter(objectMapper,loginUrl);
        loginAuthenticationFilter.setAuthenticationManager(authenticationManager);
        loginAuthenticationFilter.setAuthenticationSuccessHandler(loginAuthenticationSuccessHandler());
        loginAuthenticationFilter.setAuthenticationFailureHandler(loginAuthenticationFailureHandler());
        return loginAuthenticationFilter;
    }

    @Bean
    public AuthenticationProvider loginAuthenticationProvider() {
        return new LoginAuthenticationProvider(userContextService, passwordEncoder);
    }

    @Bean
    public LoginAuthenticationSuccessHandler loginAuthenticationSuccessHandler() {
        return new LoginAuthenticationSuccessHandler(objectMapper, jwtTokenProvider, authService);
    }

    @Bean
    public LoginAuthenticationFailureHandler loginAuthenticationFailureHandler() {
        return new LoginAuthenticationFailureHandler(objectMapper);
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(objectMapper, authService, jwtTokenProvider, userReadService);
    }

    @Bean
    public AccessDeniedHandler loginDeniedHandler() {
        return new LoginDeniedHandler();
    }

    @Bean
    public LoginAuthenticationEntryPoint loginAuthenticationEntryPoint() {
        return new LoginAuthenticationEntryPoint(objectMapper);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern(CorsConfiguration.ALL);
        configuration.addAllowedHeader(CorsConfiguration.ALL);
        configuration.addAllowedMethod(CorsConfiguration.ALL);
        configuration.setAllowCredentials(true);

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
