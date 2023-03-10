package com.geekster.instagram.service;

import com.geekster.instagram.dao.SecurityRepo;
import com.geekster.instagram.model.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    @Autowired
    SecurityRepo securityRepo;

    public int saveStatus(Security securityObj) {
        Security security=securityRepo.save(securityObj);
        return security.getSecurityId();
    }
}
