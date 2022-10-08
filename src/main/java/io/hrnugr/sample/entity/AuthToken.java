package io.hrnugr.sample.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "AUTH_TOKENS")
@NoArgsConstructor
@Getter
@Setter
public class AuthToken extends BaseEntity {

    @Column(name = "TOKEN")
    private String token;
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "USER_ID")
    private User user;

    public AuthToken(User user) {
        this.user = user;
        this.token = UUID.randomUUID().toString();
    }

}
