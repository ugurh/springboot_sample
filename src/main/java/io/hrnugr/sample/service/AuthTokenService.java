package io.hrnugr.sample.service;

import io.hrnugr.sample.entity.AuthToken;
import io.hrnugr.sample.entity.User;
import io.hrnugr.sample.handler.exceptions.AuthFailException;
import io.hrnugr.sample.handler.exceptions.CustomException;
import io.hrnugr.sample.handler.exceptions.ResourceNotExistException;

public interface AuthTokenService {

    void saveToken(AuthToken authToken);

    AuthToken getToken(User user);

    User getUser(String token) throws CustomException, AuthFailException, ResourceNotExistException;

    void authenticate(String token) throws CustomException, AuthFailException;
}
