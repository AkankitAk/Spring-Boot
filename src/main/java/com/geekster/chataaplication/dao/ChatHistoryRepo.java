package com.geekster.chataaplication.dao;

import com.geekster.chataaplication.model.ChatHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatHistoryRepo extends JpaRepository<ChatHistory,Integer> {

    @Query(value = "select * from tbl_chat_history where sender_user_id= :senderId and status_id=1",nativeQuery = true)
    List<ChatHistory> getChatByUserId(int senderId);

    @Query(value = "select * from tbl_chat_history where (sender_user_id= :senderId and receiver_user_id= :receiverId)or " +
            "(sender_user_id= :receiverId and receiver_user_id= :senderId) and status_id=1 order by created_date",nativeQuery = true)
    List<ChatHistory> getConversion(int senderId, int receiverId);
}
