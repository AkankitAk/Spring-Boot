package com.geekster.chataaplication.controller;

import com.geekster.chataaplication.dao.StatusRepo;
import com.geekster.chataaplication.dao.UserRepo;
import com.geekster.chataaplication.model.ChatHistory;
import com.geekster.chataaplication.model.Status;
import com.geekster.chataaplication.model.User;
import com.geekster.chataaplication.service.ChatHistoryService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
@RequestMapping(value = "/api/v1/chat")
public class ChatHistoryController {

    @Autowired
    ChatHistoryService chatHistoryService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    StatusRepo statusRepo;
    @PostMapping(value = "/send")
    public ResponseEntity<String> sendMessage(@RequestBody String requestMessage){

        JSONObject requestObj=new JSONObject(requestMessage);
        JSONObject errorList=validateRequest(requestObj);
        if (errorList.isEmpty()){
            ChatHistory chat=setChatHistory(requestObj);
            int chatId=chatHistoryService.sendMessage(chat);
            return new ResponseEntity<>("Message sent- "+chatId, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(errorList.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    private ChatHistory setChatHistory(JSONObject requestObj) {
        ChatHistory chatHistory=new ChatHistory();

        int senderId=requestObj.getInt("sender");
        int receiverId=requestObj.getInt("receiver");
        String message=requestObj.getString("message");

        User senderObj=userRepo.findById(senderId).get();
        User receiverObj=userRepo.findById(receiverId).get();

        chatHistory.setSender(senderObj);
        chatHistory.setReceiver(receiverObj);
        chatHistory.setMessage(message);

        Status status=statusRepo.findById(1).get();

        chatHistory.setStatusId(status);

        Timestamp createTime=new Timestamp(System.currentTimeMillis());
        chatHistory.setCreatedDate(createTime);
        return chatHistory;
    }

    private JSONObject validateRequest(JSONObject requestObj) {

        JSONObject jsonObj=new JSONObject();
        if(!requestObj.has("sender")){
            jsonObj.put("sender","Missing parameter");
        }
        if (!requestObj.has("receiver")){
            jsonObj.put("receiver","Missing parameter");
        }
        if (requestObj.has("message")){
            String message=requestObj.getString("message");
            if (message.isEmpty() || message.isBlank()){
                jsonObj.put("message","Missing parameter");            }
        }
        else {
            jsonObj.put("sender","Missing parameter");
        }
        return jsonObj;
    }
}
