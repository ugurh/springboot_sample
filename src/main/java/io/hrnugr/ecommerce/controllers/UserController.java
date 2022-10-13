package io.hrnugr.ecommerce.controllers;

import io.hrnugr.ecommerce.handler.exceptions.AuthFailException;
import io.hrnugr.ecommerce.handler.exceptions.CustomException;
import io.hrnugr.ecommerce.model.dto.request.SignInDto;
import io.hrnugr.ecommerce.model.dto.request.SignupDto;
import io.hrnugr.ecommerce.model.dto.response.SignInResponseDto;
import io.hrnugr.ecommerce.model.dto.response.SignUpResponseDto;
import io.hrnugr.ecommerce.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody @Valid SignupDto signupDto) throws CustomException {
        return new ResponseEntity<>(userService.signUp(signupDto), HttpStatus.OK);
    }

    @PostMapping("/signIn")
    public ResponseEntity<SignInResponseDto> signIn(@RequestBody @Valid SignInDto signInDto) throws CustomException, AuthFailException {
        return new ResponseEntity<>(userService.signIn(signInDto), HttpStatus.OK);
    }
}
