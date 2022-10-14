package io.ugurh.ecommerce.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author harun ugur
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponseDto {
    private String status;
    private String message;
}
