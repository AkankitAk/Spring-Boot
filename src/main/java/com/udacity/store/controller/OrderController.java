package com.udacity.store.controller;

import com.udacity.store.model.Order;
import com.udacity.store.model.User;
import com.udacity.store.repository.OrderRepository;
import com.udacity.store.repository.UserRepository;
import com.udacity.store.service.OrderService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
//@RequestMapping("orders/")
//@CrossOrigin(origins = "http://localhost:4200")


public class OrderController {
// TODO: Use the mapping submit to add an API endpoint to fetch the products from the OrderRepository

    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderService orderService;

    @PostMapping(value = "/oder")
    public ResponseEntity<String> saveOrder(@RequestBody String order) {

        Order __order = setOrder(order);
        String orderId = orderService.savePost(__order);
        return new ResponseEntity<String>(String.valueOf(orderId), HttpStatus.CREATED);
    }

    private Order setOrder(String oderRequest) {
        JSONObject jsonObject = new JSONObject(oderRequest);
        User user = null;
        int userId = jsonObject.getInt("userId");
        if(userRepository.findById(String.valueOf(userId)).isPresent()) {
            user = userRepository.findById(String.valueOf(userId)).get();
        } else {
            return null;
        }
        Order order = new Order();
        order.setUserId(user);
        Timestamp createdTime = new Timestamp(System.currentTimeMillis());
        order.setOrderDate(createdTime);
        return order;


    }
}
