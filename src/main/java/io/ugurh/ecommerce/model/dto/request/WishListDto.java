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
public class WishListDto {
    private @NotNull Long userId;
    private @NotNull Long productId;
}
