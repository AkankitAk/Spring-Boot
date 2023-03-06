package com.geekster.chataaplication.dao;

import com.geekster.chataaplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepo extends JpaRepository<User ,Integer> {

    @Query(value = "select * from tbl_user where username= :userName and status_id= 1",nativeQuery = true)
    public List<User> findByUsername(String userName);

    @Query(value = "select * from tbl_user where user_id = :userId and status_id=1",nativeQuery = true)
    public List<User> getUserByUserId(int userId);

    @Query(value = "select * from tbl_user where status_id=1",nativeQuery = true)
    public List<User> getAllUser();

    @Modifying
    @Transactional
    @Query(value = "update tbl_user set status_id=2 where user_id= :userId",countQuery = "SELECT count(*) FROM tbl_user",nativeQuery = true)
    void deleteUserByUserId(int userId);


}
