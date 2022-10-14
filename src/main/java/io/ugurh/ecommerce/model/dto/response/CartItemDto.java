package io.ugurh.ecommerce.model.dto.response;

import io.ugurh.ecommerce.model.dto.request.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author harun ugur
 */
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CartItemDto {
    private Long id;
    private Integer quantity;
    private ProductDto productDto;
}