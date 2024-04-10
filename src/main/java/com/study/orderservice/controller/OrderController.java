package com.study.orderservice.controller;

import com.study.basedomains.dto.Order;
import com.study.basedomains.dto.OrderEvent;
import com.study.orderservice.service.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping
    public String placeOrder(@RequestBody Order order) {
        order.setOrderId(UUID.randomUUID().toString());
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setOrder(order);
        orderEvent.setMessage("Order placed successfully");
        orderEvent.setStatus("PLACED");
        orderProducer.sendMessage(orderEvent);
        return "Order placed successfully";
    }
}
