package io.hrnugr.ecommerce.service;

import io.hrnugr.ecommerce.handler.exceptions.AuthFailException;
import io.hrnugr.ecommerce.handler.exceptions.CustomException;
import io.hrnugr.ecommerce.handler.exceptions.ResourceNotFoundException;
import io.hrnugr.ecommerce.model.entity.AuthToken;
import io.hrnugr.ecommerce.model.entity.User;

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
     * @throws ResourceNotFoundException
     */
    User getUser(String token) throws CustomException, AuthFailException, ResourceNotFoundException;

    /**
     * authenticate user token
     *
     * @param token user token
     * @throws CustomException
     * @throws AuthFailException
     */
    void authenticate(String token) throws CustomException, AuthFailException;
}
