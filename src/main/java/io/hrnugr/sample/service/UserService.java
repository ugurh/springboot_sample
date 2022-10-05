package io.hrnugr.sample.service;

import io.hrnugr.sample.dto.request.SignInDto;
import io.hrnugr.sample.dto.request.SignupDto;
import io.hrnugr.sample.dto.response.SignInResponseDto;
import io.hrnugr.sample.dto.response.SignUpResponseDto;
import io.hrnugr.sample.exceptions.AuthFailException;
import io.hrnugr.sample.exceptions.CustomException;

public interface UserService {
    SignUpResponseDto signUp(SignupDto signupDto) throws CustomException;

    SignInResponseDto signIn(SignInDto signInDto) throws CustomException, AuthFailException;
}
