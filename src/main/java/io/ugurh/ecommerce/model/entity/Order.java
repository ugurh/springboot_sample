package io.ugurh.ecommerce.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @author harun ugur
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@SequenceGenerator(name = "Order", sequenceName = "order_seq", allocationSize = 1)
@Entity
@Table(name = "ORDERS")
public class Order extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -5162063062835127410L;

    @NotNull
    @Column(name = "SESSION_ID", nullable = false)
    private String sessionId;

    @NotNull
    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "USER_ID", referencedColumnName = "id")
    private User user;

    @NotNull
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("createdDate DESC")
    @ToString.Exclude
    private List<OrderItem> orderItems;

    @PositiveOrZero
    @NotNull
    @Column(name = "TOTAL_PRICE", nullable = false, precision = 18, scale = 2)
    @NumberFormat(pattern = "#,###,###,###.##", style = NumberFormat.Style.CURRENCY)
    private BigDecimal totalPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(getId(), order.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}