package com.mentors.authToken.service;


import com.mentors.authToken.domain.AuthToken;

public interface AuthWriteService {
    Long saveAuthToken(AuthToken authToken);
}
