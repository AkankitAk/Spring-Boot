package com.udacity.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Cart {
    private String productId;
    private Integer productCount;
    private User userId;

}
