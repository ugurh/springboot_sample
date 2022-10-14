package io.ugurh.ecommerce.repository;

import io.ugurh.ecommerce.model.entity.AuthToken;
import io.ugurh.ecommerce.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author harun ugur
 */
public interface AuthTokenRepository extends JpaRepository<AuthToken, Long> {
    Optional<AuthToken> findByUser(User user);

    /**
     * fetch auth toke by user
     *
     * @param user user
     * @return AuthToken
     */
    //AuthToken findByUser(User user);

    /**
     * fetch auth toke by token
     *
     * @param token auth token
     * @return AuthToken
     */
    AuthToken findAuthTokenByToken(String token);
}
