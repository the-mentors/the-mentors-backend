package com.mentors.authToken.service;


import com.mentors.authToken.domain.AuthToken;

public interface AuthTokenWriteService {
    String saveAuthToken(AuthToken authToken);
}
