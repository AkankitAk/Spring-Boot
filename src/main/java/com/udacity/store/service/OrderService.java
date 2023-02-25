package com.udacity.store.service;

import com.udacity.store.model.Order;
import com.udacity.store.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public String savePost(Order order) {
        Order savedOrder= orderRepository.save(order);
        return savedOrder.getOrderId();

    }
}
