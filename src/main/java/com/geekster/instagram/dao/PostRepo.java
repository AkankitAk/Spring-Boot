package com.geekster.instagram.dao;

import com.geekster.instagram.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post,Integer> {

    @Query(value = "select * from tbl_post where user_id= :loginUserid and status_id=1",nativeQuery = true)
    List<Post> findByLoginId(int loginUserid);

    @Query(value = "select * from tbl_post where user_id= :loginUserid and post_id= :postId and status_id=1",nativeQuery = true)
    Post findByPostId(int loginUserid, int postId);
    @Query(value = "select * from tbl_post where user_id= :userId and post_id= :postId and security_id=1 and status_id=1",nativeQuery = true)
    Post findByUserIdWithPostId(int postId, int userId);
    @Query(value = "select * from tbl_post where user_id= :userId and security_id=1 and status_id=1",nativeQuery = true)
    List<Post> findByAnotherUserId(int userId);


    @Query(value = "select * from tbl_post where post_id= :postId and user_id= :loginUserId",nativeQuery = true)
    Post getPostByPostId(int postId, int loginUserId);

    @Modifying
    @Transactional
    @Query(value = "update tbl_post set status_id=2 where user_id= :loginUserid and post_id= :postId",
            countQuery="select count(*) from tbl_user",nativeQuery = true)
    void deleteByPostId(int loginUserid, int postId);
}
