package io.hrnugr.sample.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "CATEGORIES")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CATEGORY_NAME")
    @NotBlank(message = "Category name can not be empty")
    private @NotBlank String categoryName;

    @Column(name = "DESCRIPTION")
    @NotBlank(message = "Description can not be empty")
    private @NotBlank String description;

    @Column(name = "IMAGE_URL")
    @NotNull(message = "Image url can not be empty")
    private @NotBlank String imageUrl;

}
