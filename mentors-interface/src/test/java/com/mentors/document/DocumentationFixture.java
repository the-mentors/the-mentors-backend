package com.mentors.document;

import com.mentors.AuthFixture;
import com.mentors.CategoryFixture;
import com.mentors.UserFixture;
import com.mentors.authority.Authority;
import com.mentors.category.domain.Category;
import com.mentors.user.Role;
import com.mentors.user.auth.UserContext;
import com.mentors.user.authToken.domain.AuthToken;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DocumentationFixture {

    public static final List<Category> CATEGORIES_FIXTURE = CategoryFixture.toSortCategories();
    public static final AuthToken AUTHTOKEN_FIXTURE = AuthFixture.toAuthToken();
    public static final String EMAIL = "user1@email.com";
    public static final List<String> ROLE = List.of("USER");


    public static UserContext USERCONTEST_FIXTURE = new UserContext(UserFixture.toDomainWithRoleAndUserId(),
            convertStringToAuthorityByRole(UserFixture.toDomainWithRole().role()));

    private static List<Authority> convertStringToAuthorityByRole(List<String> roles) {
        return roles.stream()
                .map(role -> new Authority(Role.findByName(role)))
                .collect(Collectors.toList());
    }


}
