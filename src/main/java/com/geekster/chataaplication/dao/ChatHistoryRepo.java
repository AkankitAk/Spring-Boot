package com.geekster.chataaplication.dao;

import com.geekster.chataaplication.model.ChatHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatHistoryRepo extends JpaRepository<ChatHistory,Integer> {
}
