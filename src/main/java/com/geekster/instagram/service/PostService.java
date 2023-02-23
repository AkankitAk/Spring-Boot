package com.geekster.instagram.service;

import com.geekster.instagram.dao.PostRepo;
import com.geekster.instagram.model.Post;
import com.geekster.instagram.model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Service
public class PostService {
    @Autowired
    PostRepo postRepo;

//    save post which come to controller and return post id
    public int savePost(Post post) {
        Post savePost=postRepo.save(post);
        return savePost.getPostId();
    }

//    get post by use id and post id
    public JSONArray getPost(int userId, String postId) {
        JSONArray postArray=new JSONArray();
//        check if post id is not null and user id is present or not
        if(postId != null && postRepo.findById(userId).isPresent()){
            Post post=postRepo.findById(userId).get();
            JSONObject jsonObject=setPostData(post);
            postArray.put(jsonObject);
        }
        else {
            List<Post> postList=postRepo.findAll();
            for (Post post:postList){
                JSONObject postObj=setPostData(post);
                postArray.put(postObj);
            }
        }
        return postArray;
    }

    private JSONObject setPostData(Post post) {
        JSONObject masterJsonObject=new JSONObject();
        masterJsonObject.put("postId",post.getPostId());
        masterJsonObject.put("postData",post.getPostData());

        User user= post.getUser();

        JSONObject userJsonObj=new JSONObject();

        userJsonObj.put("userid",user.getUserId());
        userJsonObj.put("firstName",user.getFirstName());
        userJsonObj.put("age",user.getAge());

        masterJsonObject.put("user",userJsonObj);
        return masterJsonObject;
    }

//    update post by post id and user send all data jo update karna chahta  hai
    public void updatePost(String postId, Post updatePost) {
        if(postRepo.findById(Integer.parseInt(postId)).isPresent()){

            Post olderPost=postRepo.findById(Integer.parseInt(postId)).get();
            updatePost.setPostId(olderPost.getPostId());

            updatePost.setCreatedDate(olderPost.getCreatedDate() );
            Timestamp updateDate=new Timestamp(System.currentTimeMillis());
            updatePost.setUpdateDate(updateDate);
            postRepo.save(updatePost);
        }
    }
}
