package com.mentors.global.config;

import com.mentors.global.resolver.ExtensionValidResolver;
import com.mentors.service.ImageValidator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final ImageValidator imageValidator;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .exposedHeaders("*")
                .allowedHeaders("*");
    }

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addViewController("/docs")
                .setViewName("forward:/docs/index.html");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new ExtensionValidResolver(imageValidator));
    }
}
