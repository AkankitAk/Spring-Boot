package com.example.todo.service;

import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


@Service
public class TodoService implements ITodoService{

    private static List<Todo> todos=new ArrayList<>(); // In-memory database

    @Autowired
    TodoRepository todoRepository;
    @Override
    public List<Todo> findAll() { // Business Logic
        return todoRepository.findAll();
    }
    @Override
    public Todo findById(int id) { // business LOgic
        return todoRepository.findById(id).get();
    }
    @Override
    public void addTodo(Todo todo) {
        todoRepository.save(todo);
    }
    @Override
    public void deleteTodo(int id) {
        todoRepository.deleteById(id);
    }
    @Override
    public void updateTodo(int id,Todo newTodo) {
        //step 1: find todo to be update
        //Step 2: update todo

        Todo todo=todoRepository.findById(id).get();
        todo.setId(newTodo.getId());
        todo.setTitle(newTodo.getTitle());
        todo.setStatus(newTodo.isStatus());
        todoRepository.save(todo);
    }
}
