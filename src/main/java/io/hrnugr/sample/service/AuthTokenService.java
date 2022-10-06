package io.hrnugr.sample.service;

import io.hrnugr.sample.entity.AuthToken;
import io.hrnugr.sample.entity.User;
import io.hrnugr.sample.exceptions.CustomException;

public interface AuthTokenService {

    void saveToken(AuthToken authToken);

    AuthToken getToken(User user);

    User getUser(String token) throws CustomException;

    void authenticate(String token) throws CustomException;
}
