package io.ugurh.ecommerce.service.impl;

import io.ugurh.ecommerce.handler.exceptions.AuthFailException;
import io.ugurh.ecommerce.handler.exceptions.ResourceNotFoundException;
import io.ugurh.ecommerce.model.entity.AuthToken;
import io.ugurh.ecommerce.model.entity.User;
import io.ugurh.ecommerce.repository.AuthTokenRepository;
import io.ugurh.ecommerce.service.AuthTokenService;
import io.ugurh.ecommerce.util.Messages;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author harun ugur
 */
@Service
public class AuthServiceImpl implements AuthTokenService {

    private final AuthTokenRepository authTokenRepository;

    public AuthServiceImpl(AuthTokenRepository authTokenRepository) {
        this.authTokenRepository = authTokenRepository;
    }

    @Override
    public void saveToken(AuthToken authToken) {
        authTokenRepository.save(authToken);
    }

    @Override
    public AuthToken getToken(User user) {
        return authTokenRepository.findByUser(user).orElse(null);
    }

    @Override
    public User getUser(String token) throws AuthFailException, ResourceNotFoundException {
        AuthToken authToken = authTokenRepository.findAuthTokenByToken(token);

        if (Objects.isNull(authToken)) {
            throw new AuthFailException(Messages.AUTH_TOKEN_NOT_PRESENT);
        }

        if (Objects.isNull(authToken.getUser())) {
            throw new ResourceNotFoundException(Messages.AUTH_USER_NOT_PRESENT);
        }

        return authToken.getUser();
    }

    @Override
    public void authenticate(String token) throws AuthFailException {

        if (Objects.isNull(token)) {
            throw new AuthFailException(Messages.EMPTY_AUTH_TOKEN);
        }

        AuthToken authToken = authTokenRepository.findAuthTokenByToken(token);

        if (Objects.isNull(authToken)) {
            throw new AuthFailException(Messages.AUTH_TOKEN_NOT_PRESENT);
        }

    }
}
