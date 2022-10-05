package io.hrnugr.sample.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SignInResponseDto {
    private String status;
    private String token;
}
