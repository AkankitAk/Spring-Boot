package com.geekster.chataaplication.service;

import com.geekster.chataaplication.dao.ChatHistoryRepo;
import com.geekster.chataaplication.model.ChatHistory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatHistoryService {

    @Autowired
    ChatHistoryRepo chatHistoryRepo;
    public int sendMessage(ChatHistory chat) {
        ChatHistory chatHistory=chatHistoryRepo.save(chat);
        return chatHistory.getChatId();
    }

    public JSONObject getChatByUserId(int senderId) {
        List<ChatHistory> chatList=chatHistoryRepo.getChatByUserId(senderId);

        JSONObject response=new JSONObject();

        if (!chatList.isEmpty()){
            response.put("senderId",chatList.get(0).getSender().getUserId());
            response.put("senderName",chatList.get(0).getSender().getFirstName());
        }

        JSONArray receivers=new JSONArray();

        for (ChatHistory chat:chatList){
            JSONObject receiverObj=new JSONObject();

            receiverObj.put("receiverId",chat.getReceiver().getUserId());
            receiverObj.put("receiverName",chat.getReceiver().getFirstName());

            receiverObj.put("receiverMessage",chat.getMessage());
            receivers.put(receiverObj);
        }
        response.put("receivers",receivers);
        return response;
    }

    public JSONObject getConversion(int senderId,int receiverId) {
        JSONObject response=new JSONObject();
        List<ChatHistory> chatList=chatHistoryRepo.getConversion(senderId,receiverId);

        JSONArray conversions=new JSONArray();

        for (ChatHistory chats:chatList){

            JSONObject messageObj=new JSONObject();
             messageObj.put("timestamp",chats.getCreatedDate());
             messageObj.put("senderName",chats.getSender().getFirstName());
             messageObj.put("message",chats.getMessage());

             conversions.put(messageObj);
        }
        response.put("conversions",conversions);
        return response;
     }
}
