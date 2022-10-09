package io.hrnugr.sample.controllers;

import io.hrnugr.sample.dto.request.SignInDto;
import io.hrnugr.sample.dto.request.SignupDto;
import io.hrnugr.sample.dto.response.SignInResponseDto;
import io.hrnugr.sample.dto.response.SignUpResponseDto;
import io.hrnugr.sample.handler.exceptions.AuthFailException;
import io.hrnugr.sample.handler.exceptions.CustomException;
import io.hrnugr.sample.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author harun ugur
 */
@RequestMapping("user")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignupDto signupDto) throws CustomException {
        return new ResponseEntity<>(userService.signUp(signupDto), HttpStatus.OK);
    }

    @PostMapping("/signIn")
    public ResponseEntity<SignInResponseDto> signIn(@RequestBody SignInDto signInDto) throws CustomException, AuthFailException {
        return new ResponseEntity<>(userService.signIn(signInDto), HttpStatus.OK);
    }
}
