package io.ugurh.ecommerce.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author harun ugur
 */
@AllArgsConstructor
@Getter
@Setter
public class SignInDto {
    private @NotNull String email;
    private @NotNull String password;
}
