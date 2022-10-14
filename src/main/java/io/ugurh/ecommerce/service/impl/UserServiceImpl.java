package io.ugurh.ecommerce.service.impl;

import io.ugurh.ecommerce.handler.exceptions.AuthFailException;
import io.ugurh.ecommerce.handler.exceptions.CustomException;
import io.ugurh.ecommerce.model.dto.request.SignInDto;
import io.ugurh.ecommerce.model.dto.request.SignupDto;
import io.ugurh.ecommerce.model.dto.response.SignInResponseDto;
import io.ugurh.ecommerce.model.dto.response.SignUpResponseDto;
import io.ugurh.ecommerce.model.entity.AuthToken;
import io.ugurh.ecommerce.model.entity.User;
import io.ugurh.ecommerce.repository.UserRepository;
import io.ugurh.ecommerce.service.AuthTokenService;
import io.ugurh.ecommerce.service.UserService;
import io.ugurh.ecommerce.util.HashUtil;
import io.ugurh.ecommerce.util.Messages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author harun ugur
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthTokenService authTokenService;

    public UserServiceImpl(UserRepository userRepository, AuthTokenService authTokenService) {
        this.userRepository = userRepository;
        this.authTokenService = authTokenService;
    }

    @Override
    public SignUpResponseDto signUp(SignupDto signupDto) throws CustomException {
        try {

            if (Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))) {
                throw new CustomException("User already exists");
            }

            String encryptedPassword = HashUtil.encryptedPassword(signupDto.getPassword());

            User user = User.builder()
                    .firstName(signupDto.getFirstName())
                    .lastName(signupDto.getLastName())
                    .email(signupDto.getEmail())
                    .password(encryptedPassword)
                    .build();

            userRepository.save(user);

            final AuthToken authenticationToken = new AuthToken(user);
            authTokenService.saveToken(authenticationToken);
            return new SignUpResponseDto("success", "user created successfully");
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public SignInResponseDto signIn(SignInDto signInDto) throws CustomException, AuthFailException {
        User user = userRepository.findByEmail(signInDto.getEmail());

        if (Objects.isNull(user)) {
            throw new CustomException(Messages.USER_NOT_PRESENT);
        }

        String encryptedPassword = HashUtil.encryptedPassword(signInDto.getPassword());
        if (!user.getPassword().equals(encryptedPassword)) {
            throw new AuthFailException(Messages.WRONG_AUTH_VALUES);
        }
        AuthToken token = authTokenService.getToken(user);

        if (Objects.isNull(token)) {
            throw new CustomException(Messages.AUTH_TOKEN_NOT_VALID);
        }

        return new SignInResponseDto("success", token.getToken());
    }
}
