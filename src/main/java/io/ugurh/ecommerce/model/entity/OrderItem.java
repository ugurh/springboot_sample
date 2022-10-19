package io.ugurh.ecommerce.model.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@SequenceGenerator(name = "order item sequence", sequenceName = "order_item_seq", allocationSize = 1)
@Entity
@Table(name = "ORDER_ITEMS")
public class OrderItem extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -1556092394676807791L;

    @PositiveOrZero
    @Column(name = "QUANTITY", nullable = false)
    private @NotNull int quantity;

    @NotNull
    @PositiveOrZero
    @Column(name = "PRICE", nullable = false, precision = 18, scale = 2)
    @NumberFormat(pattern = "#,###,###,###.##", style = NumberFormat.Style.CURRENCY)
    private BigDecimal price;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID", nullable = false)
    private Order order;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(getId(), orderItem.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}