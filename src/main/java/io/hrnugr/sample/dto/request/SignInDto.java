package io.hrnugr.sample.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author harun ugur
 */
@AllArgsConstructor
@Getter
@Setter
public class SignInDto {
    private String email;
    private String password;
}
