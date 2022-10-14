package io.ugurh.ecommerce.controllers;

import io.ugurh.ecommerce.handler.exceptions.AuthFailException;
import io.ugurh.ecommerce.handler.exceptions.CustomException;
import io.ugurh.ecommerce.handler.exceptions.ResourceNotFoundException;
import io.ugurh.ecommerce.model.dto.response.ApiResponseDto;
import io.ugurh.ecommerce.model.entity.User;
import io.ugurh.ecommerce.service.AuthTokenService;
import io.ugurh.ecommerce.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author harun ugur
 * @created 10.10.2022 - 21:01
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final AuthTokenService authTokenService;

    public OrderController(OrderService orderService, AuthTokenService authTokenService) {
        this.orderService = orderService;
        this.authTokenService = authTokenService;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponseDto> placeOrder(@RequestParam("token") String token, @RequestParam("sessionId") String sessionId)
            throws AuthFailException, CustomException, ResourceNotFoundException {
        authTokenService.authenticate(token);
        User user = authTokenService.getUser(token);
        orderService.placeOrder(user, sessionId);
        return new ResponseEntity<>(new ApiResponseDto(true, "Order has been placed"), HttpStatus.CREATED);
    }
}
