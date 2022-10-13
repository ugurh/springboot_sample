package io.hrnugr.ecommerce.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author harun ugur
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

    private Long categoryId;

    @NotNull(message = "Category name can not be empty")
    private String categoryName;

    @NotNull(message = "Description can not be empty")
    private String description;

    @NotNull(message = "Image url can not be empty")
    private String imageUrl;
}
