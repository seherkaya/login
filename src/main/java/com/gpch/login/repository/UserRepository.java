package com.gpch.login.repository;

import com.gpch.login.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User>  findByName (String name);
    List<User>  findAllBy ();

}