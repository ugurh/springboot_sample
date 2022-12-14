package io.ugurh.ecommerce.model.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author harun ugur
 */
@AllArgsConstructor
@Builder
@Getter
@Setter
public class AddToCartDto {
    private Long id;
    private @NotNull Long productId;
    private @NotNull Integer quantity;
}
