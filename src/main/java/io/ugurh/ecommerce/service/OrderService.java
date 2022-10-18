package io.ugurh.ecommerce.service;

import io.ugurh.ecommerce.model.entity.Order;
import io.ugurh.ecommerce.model.entity.User;

import java.util.List;

/**
 * @author harun ugur
 * @created 10.10.2022 - 21:03
 */
public interface OrderService {
    /**
     * save order
     *
     * @param user      user
     * @param sessionId session id
     */
    void placeOrder(User user, String sessionId);

    List<Order> listOrders(User user);

    Order getOrder(Long id, User user);

}
