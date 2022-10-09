package io.hrnugr.sample.service;

import io.hrnugr.sample.dto.request.SignInDto;
import io.hrnugr.sample.dto.request.SignupDto;
import io.hrnugr.sample.dto.response.SignInResponseDto;
import io.hrnugr.sample.dto.response.SignUpResponseDto;
import io.hrnugr.sample.handler.exceptions.AuthFailException;
import io.hrnugr.sample.handler.exceptions.CustomException;

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
