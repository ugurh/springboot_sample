package io.hrnugr.sample.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CartDto {
    List<CartItemDto> cartItems;
    private BigDecimal totalPrice;
}
