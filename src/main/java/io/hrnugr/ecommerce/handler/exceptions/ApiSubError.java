package io.hrnugr.ecommerce.handler.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author harun ugur
 * @project sample
 * @created 13.10.2022 - 22:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiSubError {
    private String field;
    private String message;
}
