package io.hrnugr.sample.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
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
@AllArgsConstructor
@Builder
@Table(name = "PRODUCTS")
@SQLDelete(sql = "UPDATE Product SET deleted = true WHERE id = ? and version = ?")
public class Product extends BaseEntity {

    @Column(name = "NAME")
    @NotNull
    private String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    @ToString.Exclude
    Category category;

    @Column(name = "IMAGE_URL")
    @NotNull
    private String imageUrl;

    @Column(name = "PRICE")
    @NotNull
    private Double price;

    @Column(name = "DESCRIPTION")
    @NotNull
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Product product = (Product) o;
        return getId() != null && Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
