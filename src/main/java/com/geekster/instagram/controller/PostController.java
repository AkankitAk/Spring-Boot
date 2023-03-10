package com.geekster.instagram.controller;

import com.geekster.instagram.dao.SecurityRepo;
import com.geekster.instagram.dao.StatusRepo;
import com.geekster.instagram.dao.UserRepo;
import com.geekster.instagram.model.Post;
import com.geekster.instagram.model.Security;
import com.geekster.instagram.model.Status;
import com.geekster.instagram.model.User;
import com.geekster.instagram.service.PostService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
public class PostController {

    @Autowired
    UserRepo userRepo;
    @Autowired
    PostService postService;
    @Autowired
    StatusRepo statusRepo;
    @Autowired
    SecurityRepo securityRepo;

//    create a new post with lazy loading

    @PostMapping(value = "/createPost")
    public ResponseEntity<String> savePost(@RequestBody String postRequest){
        JSONObject postRequestObj=new JSONObject(postRequest);
        JSONObject errorList=validatePost(postRequestObj);
        if (errorList.isEmpty()){
            Post post=setPost(postRequestObj);
            int postId=postService.savePost(post);
            return new ResponseEntity<String>("post created post id - "+postId, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<String>(errorList.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    private JSONObject validatePost(JSONObject postRequestObj) {
        JSONObject error=new JSONObject();
        if (!postRequestObj.has("userId")){
            error.put("userId","Missing parameter");
        }
        if (postRequestObj.has("postData")){
            String data=postRequestObj.getString("postData");
            if (data.isEmpty() || data.isBlank()){
                error.put("postData","please pass the post data");
            }

        }
        else {
            error.put("postData","Missing parameter");
        }
        if(postRequestObj.has("securityType")){
            String securityType=postRequestObj.getString("securityType");
            if (Integer.parseInt(securityType)>2  || securityType.isEmpty() || securityType.isBlank()){
                error.put("securityType","please pass 1 or 2. 1 for public and 2 for private");
            }
        }
        else {
            error.put("securityType","Missing parameter");
        }
        return error;
    }

    //    set post by user lazy loading
    private Post setPost(JSONObject postRequest) {

        User user=null;

        String userId=postRequest.getString("userId");
//        check if user is present or not if not present thn direct return null and if present then create a post
        if(userRepo.findById(Integer.valueOf(userId)).isPresent()){
            user=userRepo.findById(Integer.valueOf(userId)).get();
        }
        else {
            return null;
        }
        Post post=new Post();
        post.setUser(user);
        post.setPostData(postRequest.getString("postData"));
        Timestamp createdTime=new Timestamp(System.currentTimeMillis());
        post.setCreatedDate(createdTime);
        Status status=statusRepo.findById(1).get();
        post.setStatusId(status);

        String securityTpe=postRequest.getString("securityType");
        Security security=securityRepo.findById(Integer.valueOf(securityTpe)).get();
        post.setSecurityId(security);
        return post;
    }


//    get post by post id and user id if we not pass post id then all post get by user id
    @GetMapping(value = "/getPost")
    public ResponseEntity<String> getPost(@RequestParam String loginUSerId,@Nullable @RequestParam String userId,@Nullable @RequestParam String postId){
        JSONArray postArray=postService.getPost(loginUSerId,userId,postId);
        return new ResponseEntity<>(postArray.toString(),HttpStatus.OK);
    }

//    update post by post id
    @PutMapping(value = "/updatePost")
    public ResponseEntity<String> updatePost(@RequestParam String loginUserId, @RequestParam String postId,@RequestBody String requestPost){
        JSONObject jsonObject=new JSONObject(requestPost);
        Post post = setPost(jsonObject);
        postService.updatePost(loginUserId,postId,post);
        return new ResponseEntity<>("Post updates",HttpStatus.OK);
    }

//  Delete post by post id

    @DeleteMapping(value = "/deletePost")
    private ResponseEntity<String> deletePost(@RequestParam String loginUserid,@RequestParam String postId){
        postService.deletePostById(loginUserid,postId);
        return new ResponseEntity<>("delete post successfully user id- "+loginUserid,HttpStatus.NO_CONTENT);
    }
}






