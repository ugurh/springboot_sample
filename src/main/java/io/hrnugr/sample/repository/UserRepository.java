package io.hrnugr.sample.repository;


import io.hrnugr.sample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author harun ugur
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * fetch user by email
     *
     * @param email user email name
     * @return User
     */
    User findByEmail(String email);
}
