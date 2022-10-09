package io.hrnugr.sample.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author harun ugur
 */
@NoArgsConstructor
@Entity(name = "Save user auth token")
@Table(name = "AUTH_TOKENS")
@Getter
@Setter
@ToString
public class AuthToken extends BaseEntity {

    @Column(name = "TOKEN")
    private String token;
    @NotNull(message = "User can not be empty")
    @ManyToOne(optional = false)
    @JoinColumn(name = "USER_ID")
    private User user;

    public AuthToken(User user) {
        this.user = user;
        this.token = UUID.randomUUID().toString();
    }

}
