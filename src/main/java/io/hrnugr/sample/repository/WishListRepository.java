package io.hrnugr.sample.repository;

import io.hrnugr.sample.entity.User;
import io.hrnugr.sample.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {

    List<WishList> findWishListByUser(User user);
}
