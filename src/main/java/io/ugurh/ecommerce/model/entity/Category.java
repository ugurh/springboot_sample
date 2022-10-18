package io.ugurh.ecommerce.model.entity;


import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
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
@Where(clause = "deleted=false")
public class Category extends BaseEntity implements Serializable {

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
        return Objects.equals(getId(), category.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
