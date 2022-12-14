package io.ugurh.ecommerce.service;

import io.ugurh.ecommerce.handler.exceptions.AuthFailException;
import io.ugurh.ecommerce.handler.exceptions.CustomException;
import io.ugurh.ecommerce.model.dto.request.SignInDto;
import io.ugurh.ecommerce.model.dto.request.SignupDto;
import io.ugurh.ecommerce.model.dto.response.SignInResponseDto;
import io.ugurh.ecommerce.model.dto.response.SignUpResponseDto;

/**
 * @author harun ugur
 */
public interface UserService {
    /**
     * user sign up to application
     *
     * @param signupDto sign up
     * @return SignUpResponseDto
     * @throws CustomException User already exists
     */
    SignUpResponseDto signUp(SignupDto signupDto) throws CustomException;

    /**
     * user sign in to application
     *
     * @param signInDto sign in
     * @return SignInResponseDto
     * @throws CustomException   User not present
     * @throws AuthFailException Authentication token not valid
     */
    SignInResponseDto signIn(SignInDto signInDto) throws CustomException, AuthFailException;
}
