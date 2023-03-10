package com.geekster.instagram.dao;

import com.geekster.instagram.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    @Query(value = "select * from tbl_user where user_name= :userName and status_id=1",nativeQuery = true)
    public List<User> findByUserName(String userName);

    @Modifying
    @Transactional
    @Query(value = "update tbl_user set status_id=2 where user_id= :userId",
            countQuery="select count(*) from tbl_user",nativeQuery = true)
    void deleteUserById(int userId);

    @Query(value = "select * from tbl_user where user_id= :userId and status_id=1",nativeQuery = true)
    public List<User> findByUserId(int userId);

    @Query(value = "select * from tbl_user where status_id=1",nativeQuery = true)
    public List<User> findAll();

    @Query(value = "select * from tbl_user where user_id= :userId and status_id=1",nativeQuery = true)
    User findSecurityType(int userId);


}
