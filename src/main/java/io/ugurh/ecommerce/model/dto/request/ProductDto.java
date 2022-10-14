package io.ugurh.ecommerce.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author harun ugur
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private Long productId;
    private @NotNull String name;
    private @NotNull String imageUrl;
    private @NotNull BigDecimal price;
    private @NotNull String description;
    private @NotNull Long categoryId;
}
