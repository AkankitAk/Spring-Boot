package com.geekster.instagram.controller;

import com.geekster.instagram.model.Security;
import com.geekster.instagram.service.SecurityService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/security")
public class SecurityController  {
    @Autowired
    SecurityService securityService;
    @PostMapping(value = "/create")
    public ResponseEntity<String> createSecurity(@RequestBody String newSecurity){
        Security securityObj =requestSecurity(newSecurity);
        int securityId=securityService.saveStatus(securityObj);
        return new ResponseEntity<>("Security created security id- "+securityId, HttpStatus.CREATED);
    }

    private Security requestSecurity(String newSecurity) {
        JSONObject jsonObject=new JSONObject(newSecurity);
        Security obj=new Security();
        obj.setSecurityType(jsonObject.getString("securityType"));
        obj.setSecurityDescription(jsonObject.getString("securityDescription"));
        return obj;
    }
}
