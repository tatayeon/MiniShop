package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByNickName(String username);

    @Query("select u from User u where u.nickName = :nickName")
    User findByNickNameForLogin(@Param("nickName") String nickName);
}
