package io.hrnugr.sample.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author harun ugur
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
