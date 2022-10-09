package io.hrnugr.sample.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

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
    private @NotNull Double price;
    private @NotNull String description;
    private @NotNull Long categoryId;
}
