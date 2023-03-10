package com.geekster.instagram.dao;

import com.geekster.instagram.model.Security;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityRepo extends JpaRepository<Security,Integer> {
}
