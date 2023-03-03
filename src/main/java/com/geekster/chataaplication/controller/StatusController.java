package com.geekster.chataaplication.controller;

import com.geekster.chataaplication.model.Status;
import com.geekster.chataaplication.service.StatusService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/status")
public class StatusController {
    @Autowired
    private StatusService statusService;

    @PostMapping(value = "/create")
    public ResponseEntity<String> createStatus(@RequestBody String newStatus){
        Status status=setStatus(newStatus);

        int statusId=statusService.saveStatus(status);
        return new ResponseEntity<>("status saved"+statusId, HttpStatus.CREATED);
    }

    private Status setStatus(String newStatus) {
        JSONObject statusObject=new JSONObject(newStatus);
        Status status=new Status();
        status.setStatusName(statusObject.getString("statusName"));
        status.setStatusDescription(statusObject.getString("statusDescription"));

        return status;
    }
}
