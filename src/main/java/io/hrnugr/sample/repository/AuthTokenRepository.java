package io.hrnugr.sample.repository;

import io.hrnugr.sample.model.AuthToken;
import io.hrnugr.sample.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthTokenRepository extends JpaRepository<AuthToken,Long> {

    AuthToken findAuthTokenByUser(User user);
    AuthToken findAuthTokenByToken(String token);
}
