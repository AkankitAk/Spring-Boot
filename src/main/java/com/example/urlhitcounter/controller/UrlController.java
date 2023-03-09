package com.example.urlhitcounter.controller;

import com.example.urlhitcounter.model.Visit;
import com.example.urlhitcounter.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/visitors-count")
public class UrlController {
    @Autowired
    private UrlService urlService;
    @GetMapping("/username/{username}/count")
    public Visit getCount(@PathVariable String username){
        return urlService.getVisits(username);
    }

}
