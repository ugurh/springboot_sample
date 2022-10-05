package io.hrnugr.sample.service;

import io.hrnugr.sample.exceptions.CustomException;
import io.hrnugr.sample.model.AuthToken;
import io.hrnugr.sample.model.User;

public interface AuthTokenService {

    void saveToken(AuthToken authToken) ;

    AuthToken getToken(User user);

    User getUser(String token) throws CustomException;

    void authenticate(String token);
}
