package io.ugurh.ecommerce.service;

import io.ugurh.ecommerce.model.entity.User;

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
}