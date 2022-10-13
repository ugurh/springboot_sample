package io.hrnugr.ecommerce.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author harunu ugur
 */
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CartDto {
    List<CartItemDto> cartItems;
    private BigDecimal totalPrice;
}
