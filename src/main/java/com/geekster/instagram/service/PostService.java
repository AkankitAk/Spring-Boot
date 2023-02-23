package com.geekster.instagram.service;

import com.geekster.instagram.dao.PostRepo;
import com.geekster.instagram.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    PostRepo postRepo;
    public int savePost(Post post) {
        Post savePost=postRepo.save(post);
        return savePost.getPostId();
    }
}
