package io.hrnugr.sample.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

    @NotBlank(message = "Category name can not be empty")
    private @NotBlank String categoryName;

    @NotBlank(message = "Description can not be empty")
    private @NotBlank String description;

    @NotNull(message = "Image url can not be empty")
    private @NotBlank String imageUrl;
}
