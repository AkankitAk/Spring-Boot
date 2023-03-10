package com.geekster.instagram.dao;

import com.geekster.instagram.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepo extends JpaRepository<Status,Integer> {
}
