package com.geekster.instagram.controller;

import com.geekster.instagram.dao.PostRepo;
import com.geekster.instagram.dao.UserRepo;
import com.geekster.instagram.model.Post;
import com.geekster.instagram.model.User;
import com.geekster.instagram.service.PostService;
import org.json.HTTP;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
public class PostController {

    @Autowired
    UserRepo userRepo;
    @Autowired
    PostService postService;
    @PostMapping(value = "/post")
    public ResponseEntity<String> savePost(@RequestBody String postRequest){

        Post post=setPost(postRequest);
        int postId=postService.savePost(post);
        return new ResponseEntity<String>("post created , user id -"+postId, HttpStatus.CREATED);
    }

    private Post setPost(String postRequest) {
        JSONObject jsonObject=new JSONObject(postRequest);
        User user=null;

        int userId=jsonObject.getInt("userId");
        if(userRepo.findById(userId).isPresent()){
            user=userRepo.findById(userId).get();
        }
        else {
            return null;
        }
        Post post=new Post();
        post.setUser(user);
        post.setPostData(jsonObject.getString("postData"));
        Timestamp createdTime=new Timestamp(System.currentTimeMillis());
        post.setCreatedDate(createdTime);
        return post;

    }
}
