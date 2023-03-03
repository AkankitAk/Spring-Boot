package com.example.todo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;

import javax.persistence.*;

@AllArgsConstructor
@Data
//@Entity(name="todoDB")
@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    @Column(name = "todoTitle")
    private String title;
    private boolean status;
}
