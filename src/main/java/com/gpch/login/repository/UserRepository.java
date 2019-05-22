package com.gpch.login.repository;

import com.gpch.login.model.CustomPageable;
import com.gpch.login.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findById(int id);
    User getOne(Long id);

    List<User> findAllByName(String name);
    List<User>  findByName (String name);
    List<User>  findAllBy ();

    Page findAll(Pageable pageable);
    Page findAllByActive(int active,Pageable pageable);
    Page findByName (String name, Pageable pageable);

}