package com.geekster.chataaplication.service;

import com.geekster.chataaplication.dao.StatusRepo;
import com.geekster.chataaplication.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {
    @Autowired
    private StatusRepo statusRepo;
    public int saveStatus(Status status) {
        Status statusObj=statusRepo.save(status);
        return statusObj.getStatusId();
    }
}
