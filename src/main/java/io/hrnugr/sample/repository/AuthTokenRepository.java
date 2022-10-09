package io.hrnugr.sample.repository;

import io.hrnugr.sample.entity.AuthToken;
import io.hrnugr.sample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author harun ugur
 */
public interface AuthTokenRepository extends JpaRepository<AuthToken, Long> {

    /**
     * fetch auth toke by user
     *
     * @param user user
     * @return AuthToken
     */
    AuthToken findAuthTokenByUser(User user);

    /**
     * fetch auth toke by token
     *
     * @param token auth token
     * @return AuthToken
     */
    AuthToken findAuthTokenByToken(String token);
}
