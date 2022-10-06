package io.hrnugr.sample.service.impl;

import io.hrnugr.sample.entity.AuthToken;
import io.hrnugr.sample.entity.User;
import io.hrnugr.sample.exceptions.CustomException;
import io.hrnugr.sample.repository.AuthTokenRepository;
import io.hrnugr.sample.service.AuthTokenService;
import io.hrnugr.sample.util.Messages;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthServiceImp implements AuthTokenService {

    private final AuthTokenRepository authTokenRepository;

    public AuthServiceImp(AuthTokenRepository authTokenRepository) {
        this.authTokenRepository = authTokenRepository;
    }

    @Override
    public void saveToken(AuthToken authToken) {
        authTokenRepository.save(authToken);
    }

    @Override
    public AuthToken getToken(User user) {
        return authTokenRepository.findAuthTokenByUser(user);
    }

    @Override
    public User getUser(String token) throws CustomException {
        AuthToken authToken = authTokenRepository.findAuthTokenByToken(token);

        if (Objects.isNull(authToken))
            throw new CustomException(Messages.AUTH_TOKEN_NOT_PRESENT);

        if (Objects.isNull(authToken.getUser()))
            throw new CustomException(Messages.AUTH_USER_NOT_PRESENT);

        return authToken.getUser();
    }

    @Override
    public void authenticate(String token) throws CustomException {

        if (Objects.isNull(token))
            throw new CustomException(Messages.EMPTY_AUTH_TOKEN);

        AuthToken authToken = authTokenRepository.findAuthTokenByToken(token);

        if (Objects.isNull(authToken))
            throw new CustomException(Messages.AUTH_TOKEN_NOT_PRESENT);

    }
}
