package io.ugurh.ecommerce.service.impl;

import io.ugurh.ecommerce.handler.exceptions.ResourceNotFoundException;
import io.ugurh.ecommerce.mapper.impl.ProductMapper;
import io.ugurh.ecommerce.model.entity.Order;
import io.ugurh.ecommerce.model.entity.User;
import io.ugurh.ecommerce.repository.OrderItemRepository;
import io.ugurh.ecommerce.repository.OrderRepository;
import io.ugurh.ecommerce.service.CartService;
import io.ugurh.ecommerce.service.OrderService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author harun ugur
 * @created 10.10.2022 - 21:03
 */
@Service
@Transactional(rollbackOn = Exception.class)
public class OrderServiceImpl implements OrderService {

    private final CartService cartService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductMapper productMapper;

    public OrderServiceImpl(CartService cartService, OrderRepository orderRepository, OrderItemRepository orderItemRepository, ProductMapper productMapper) {
        this.cartService = cartService;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productMapper = productMapper;
    }

    @Override
    public void placeOrder(User user, String sessionId) {
        /*
        CartDto cartDto = cartService.listCartItems(user);

        List<CartItemDto> cartItemDtoList = cartDto.getCartItems();

        // create the order and save it
        Order newOrder = new Order();
        newOrder.setSessionId(sessionId);
        newOrder.setUser(user);
        newOrder.setTotalPrice(cartDto.getTotalPrice());
        orderRepository.save(newOrder);

        for (CartItemDto cartItemDto : cartItemDtoList) {
            // create orderItem and save each one
            Product newProduct = productMapper.toEntity(cartItemDto.getProductDto());
            OrderItem orderItem = new OrderItem();
            orderItem.setPrice(cartItemDto.getProductDto().getPrice());
            orderItem.setQuantity(cartItemDto.getQuantity());
            orderItem.setProduct(newProduct);
            orderItem.setOrder(newOrder);
            // add to order item list
            orderItemRepository.save(orderItem);
        }

         */
    }

    @Override
    public List<Order> listOrders(User user) {
        return orderRepository.findAllByUserOrderByCreatedDateDesc(user);
    }

    @Override
    public Order getOrder(Long orderId, User user) throws ResourceNotFoundException {

        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isEmpty()) {
            throw new ResourceNotFoundException("order id is not valid");
        }

        Order order = optionalOrder.get();

        if (order.getUser() != user) {
            throw new ResourceNotFoundException("order does not belong to user");
        }

        return order;
    }
}
