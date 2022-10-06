package io.hrnugr.sample.repository;

import io.hrnugr.sample.entity.AuthToken;
import io.hrnugr.sample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthTokenRepository extends JpaRepository<AuthToken, Long> {

    AuthToken findAuthTokenByUser(User user);

    AuthToken findAuthTokenByToken(String token);
}
