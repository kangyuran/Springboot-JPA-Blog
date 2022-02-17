package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

// DAO
// 자동으로 bean등록이 된다. 
// @Repositoty 생략이 가능하다. 
public interface UserRepository extends JpaRepository<User, Integer>{

    // select * from user where username = 1?;
    Optional<User> findByUsername(String username);
//    // 방법1.
//    // JPA Naming 쿼리 (전략)
//    // Select * from user WHERE username = ? AND password = ?;
//    User findByUsernameAndPassword(String username, String password);

//    // 방법2
//    @Query(value="Select * from user WHERE username = ? AND password = ?", nativeQuery = true)
//    User login(String username, String password);
}
