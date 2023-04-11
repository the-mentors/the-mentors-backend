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

public class DocumentationFixture {

    public static final List<Category> CATEGORIES_FIXTURE = CategoryFixture.toSortCategories();
    public static final AuthToken AUTHTOKEN_FIXTURE= AuthFixture.toAuthToken();
    public static final String EMAIL = "user1@email.com";
    public static final List<String> ROLE = List.of("USER");



    public static UserContext USERCONTEST_FIXTURE = new UserContext(UserFixture.toDomainWithRoleAndUserId(),changeStringToAuthorityByRole(UserFixture.toDomainWithRole().role()));

    private static ArrayList<Authority> changeStringToAuthorityByRole(ArrayList<String> roles) {
        ArrayList<Authority> authorities = new ArrayList<>();
        roles.forEach(role->authorities.add(new Authority(Enum.valueOf(Role.class, role))));
        return authorities;
    }


}
