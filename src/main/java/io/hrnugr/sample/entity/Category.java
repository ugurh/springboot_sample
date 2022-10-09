package io.hrnugr.sample.entity;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @author harun ugur
 */
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "CATEGORIES")
@AllArgsConstructor
@Builder
public class Category extends BaseEntity {

    @Column(name = "CATEGORY_NAME")
    @NotBlank(message = "Category name can not be empty")
    private @NotBlank String categoryName;

    @Column(name = "DESCRIPTION")
    @NotBlank(message = "Description can not be empty")
    private @NotBlank String description;

    @Column(name = "IMAGE_URL")
    @NotNull(message = "Image url can not be empty")
    private @NotBlank String imageUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Category category = (Category) o;
        return getId() != null && Objects.equals(getId(), category.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
