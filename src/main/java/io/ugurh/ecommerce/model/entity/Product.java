package io.ugurh.ecommerce.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
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
//@SQLDelete(sql = "UPDATE Product SET deleted = true WHERE id = ? and version = ?")
public class Product extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -1556092394676807791L;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    Category category;
    @Column(name = "NAME")
    @NotNull
    private String name;
    @Column(name = "image_url")
    @NotNull
    private String imageUrl;

    @PositiveOrZero
    @Column(name = "PRICE", precision = 18, scale = 2)
    @NumberFormat(pattern = "#,###,###,###.##", style = NumberFormat.Style.CURRENCY)
    @NotNull
    private BigDecimal price;

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
        return Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
