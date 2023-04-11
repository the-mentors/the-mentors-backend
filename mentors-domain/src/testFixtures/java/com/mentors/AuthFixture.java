package com.mentors;

import com.mentors.user.authToken.domain.AuthToken;

public class AuthFixture {

    public static final Long key = 1L;
    public static final Long unSavedKey = 0L;
    public static final String accessToken = "bbbbbbbbbbbbb.aaaaaaaaaaaa.cccccccccccccc";
    public static final String refreshToken = "aaaaaaaaaaaa.bbbbbbbbbbbbb.cccccccccccccc";
    public static AuthToken toAuthToken(){
        return new AuthToken(accessToken,refreshToken);
    }
}
