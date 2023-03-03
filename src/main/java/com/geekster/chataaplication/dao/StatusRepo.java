package com.geekster.chataaplication.dao;

import com.geekster.chataaplication.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepo extends JpaRepository<Status ,Integer> {
}
