package io.hrnugr.sample.service;

import io.hrnugr.sample.entity.AuthToken;
import io.hrnugr.sample.entity.User;
import io.hrnugr.sample.handler.exceptions.AuthFailException;
import io.hrnugr.sample.handler.exceptions.CustomException;
import io.hrnugr.sample.handler.exceptions.ResourceNotExistException;

/**
 * @author harun ugur
 */
public interface AuthTokenService {

    /**
     * save user auth token
     *
     * @param authToken user auth token
     */
    void saveToken(AuthToken authToken);

    /**
     * fetch auth token by user
     *
     * @param user user
     * @return AuthToken
     */
    AuthToken getToken(User user);

    /**
     * fetch user by user token
     *
     * @param token user token
     * @return User
     * @throws CustomException
     * @throws AuthFailException
     * @throws ResourceNotExistException
     */
    User getUser(String token) throws CustomException, AuthFailException, ResourceNotExistException;

    /**
     * authenticate user token
     *
     * @param token user token
     * @throws CustomException
     * @throws AuthFailException
     */
    void authenticate(String token) throws CustomException, AuthFailException;
}
