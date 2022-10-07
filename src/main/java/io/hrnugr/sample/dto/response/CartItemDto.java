package io.hrnugr.sample.dto.response;

import io.hrnugr.sample.dto.request.ProductDto;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CartItemDto {
    private Long id;
    private Integer quantity;
    private ProductDto productDto;
}