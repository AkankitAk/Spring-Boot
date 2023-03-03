package com.geekster.chataaplication.dao;

import com.geekster.chataaplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User ,Integer> {
}
