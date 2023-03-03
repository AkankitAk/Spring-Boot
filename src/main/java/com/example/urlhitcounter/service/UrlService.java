package com.example.urlhitcounter.service;

import com.example.urlhitcounter.model.Visit;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UrlService {
    static Map<String,Integer> map=new HashMap<>();
    public Visit getVisits(String username){
        int count=0;
        if(map.containsKey(username)){
             count=map.get(username);
            map.put(username,++count);
        }
        else {
            map.put(username,1);
        }
        Visit visit=new Visit(username,count);
        return visit;
    }
}
