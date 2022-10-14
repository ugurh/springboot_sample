package io.ugurh.ecommerce.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author harun ugur
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupDto {
    private @NotNull String firstName;
    private @NotNull String lastName;
    private @NotNull String email;
    private @NotNull String password;
}
