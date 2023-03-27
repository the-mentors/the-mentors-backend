package com.mentors.document;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mentors.api.category.controller.CategoryApiController;
import com.mentors.api.category.usecase.GetAllCategoryUsecase;
import com.mentors.api.user.controller.UserApiController;
import com.mentors.api.user.usecase.EditUserUsecase;
import com.mentors.api.user.usecase.SignInUserUsecase;
import com.mentors.api.user.usecase.SignUpUserUsecase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@AutoConfigureRestDocs
@WebMvcTest({CategoryApiController.class, UserApiController.class})
@ExtendWith(RestDocumentationExtension.class)
@ActiveProfiles("test")
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
    protected SignInUserUsecase signInUserUsecase;


    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }
}
