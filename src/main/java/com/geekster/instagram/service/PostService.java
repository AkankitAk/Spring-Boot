package com.geekster.instagram.service;

import com.geekster.instagram.dao.PostRepo;
import com.geekster.instagram.dao.UserRepo;
import com.geekster.instagram.model.Post;
import com.geekster.instagram.model.Security;
import com.geekster.instagram.model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PostService {
    @Autowired
    PostRepo postRepo;
    @Autowired
    UserRepo userRepo;

//    save post which come to controller and return post id
    public int savePost(Post post) {
        Post savePost=postRepo.save(post);
        return savePost.getPostId();
    }

//    get post by use id or post id
    public JSONArray getPost(String loginUserid,String userId, String postId) {
        JSONArray postArray=new JSONArray();
    // if user see only own post
        if(loginUserid != null && userId == null && postId == null){
            List<Post> postList=postRepo.findByLoginId(Integer.parseInt(loginUserid));
            for (Post post:postList){
                JSONObject postObj=setPostData(post);
                postArray.put(postObj);
            }
        }
    // if user see only own post with post id
        else if(loginUserid != null && userId == null && postId != null){
            Post post=postRepo.findByPostId(Integer.parseInt(loginUserid),Integer.parseInt(postId));
            if(post == null){
                JSONObject obj=new JSONObject();
                obj.put("postId","This postId is not present please try another postId");
                postArray.put(obj);
                return postArray;
            }
            JSONObject jsonObject=setPostData(post);
            postArray.put(jsonObject);
        }
    // if user see another user post which has public
        else if(loginUserid != null && userId != null && postId == null){
            User user=userRepo.findSecurityType(Integer.parseInt(userId));
            Security securityId=user.getSecurityId();
            if(securityId.getSecurityId()==2){
                JSONObject obj=new JSONObject();
                obj.put("errorMessage","user is private");
                postArray.put(obj);
                return postArray;
            }
        // if user id and login id same then return all post of login user
            else {
                if (loginUserid.equals(userId)) {
                    List<Post> postList = postRepo.findByLoginId(Integer.parseInt(loginUserid));
                    for (Post post : postList) {
                        JSONObject postObj = setPostData(post);
                        postArray.put(postObj);
                    }
                }
                // if login id and user id is different then return user post
                else {
                    List<Post> postList = postRepo.findByAnotherUserId(Integer.parseInt(userId));
                    if (postList.isEmpty()) {
                        JSONObject obj = new JSONObject();
                        obj.put("userId", "This userId is not present please try another userId");
                        postArray.put(obj);
                        return postArray;
                    }
                    for (Post post : postList) {
                        JSONObject postObj = setPostData(post);
                        postArray.put(postObj);
                    }
                }
            }
        }
    // if user see another user post with post id and which is public
        else {
            Post post=postRepo.findByUserIdWithPostId(Integer.parseInt(postId),Integer.parseInt(userId));
            JSONObject jsonObject=setPostData(post);
            postArray.put(jsonObject);
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
        userJsonObj.put("userName",user.getUserName());

        masterJsonObject.put("user",userJsonObj);
        return masterJsonObject;
    }

//    update post by post id and user send all data jo update karna chahta  hai
    public void updatePost(String loginUserId,String postId, Post updatePost) {

        Post olderPost=postRepo.getPostByPostId(Integer.parseInt(postId),Integer.parseInt(loginUserId));

        if(olderPost!=null){

            updatePost.setPostId(olderPost.getPostId());

            updatePost.setCreatedDate(olderPost.getCreatedDate() );
            Timestamp updateDate=new Timestamp(System.currentTimeMillis());
            updatePost.setUpdateDate(updateDate);
            postRepo.save(updatePost);
        }
    }

    public void deletePostById(String loginUserid, String postId) {
        postRepo.deleteByPostId(Integer.parseInt(loginUserid),Integer.parseInt(postId));
    }
}
