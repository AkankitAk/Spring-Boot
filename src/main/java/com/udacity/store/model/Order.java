package com.udacity.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")

public class Order {
//TODO: Add the details of the order class
        @Id
        @Column(name = "order_id")
        private String orderId;
        @Column(name = "order_date")
        private Timestamp orderDate;
        @JoinColumn(name = "product")
        @OneToOne
        private Product product;
        @JoinColumn(name = "user")
        @ManyToOne
        private User user;


}
