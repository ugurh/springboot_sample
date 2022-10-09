package io.hrnugr.sample.entity;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author harun ugur
 */

@Entity
@Table(name = "WISHLIST")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
public class WishList extends BaseEntity {

    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "USER_ID")
    @ToString.Exclude
    private User user;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    @ToString.Exclude
    private Product product;

    public WishList(User user, Product product) {
        this.user = user;
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        WishList wishList = (WishList) o;
        return getId() != null && Objects.equals(getId(), wishList.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

