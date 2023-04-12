package com.mentors.document;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mentors.api.category.controller.CategoryApiController;
import com.mentors.api.category.usecase.GetAllCategoryUsecase;
import com.mentors.api.user.controller.UserApiController;
import com.mentors.api.user.usecase.EditUserUsecase;
import com.mentors.api.user.usecase.SignUpUserUsecase;
import com.mentors.global.auth.jwt.JwtTokenProvider;
import com.mentors.global.config.SecurityConfig;
import com.mentors.user.auth.UserContextService;
import com.mentors.user.authToken.service.AuthService;
import com.mentors.user.user.service.UserReadService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.crypto.SecretKey;

@AutoConfigureRestDocs
@WebMvcTest({CategoryApiController.class, UserApiController.class})
@ExtendWith(RestDocumentationExtension.class)
@ActiveProfiles("test")
@Import(SecurityConfig.class)
@MockBean(SignUpUserUsecase.class)
public class Documentation {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected GetAllCategoryUsecase getAllCategoryUsecase;

    @MockBean
    protected SignUpUserUsecase signUpUserUsecase;

    @MockBean
    protected EditUserUsecase editUserUsecase;

    @MockBean
    protected JwtTokenProvider jwtCreator;

    @MockBean
    protected UserContextService userContextService;

    @MockBean
    protected PasswordEncoder passwordEncoder;

    @MockBean
    protected AuthService authService;

    @MockBean
    protected UserReadService userReadService;
    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .apply(springSecurity())
                .build();
    }
}
