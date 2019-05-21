package com.gpch.login.repository;

import com.gpch.login.model.Role;
import com.gpch.login.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findById(int id);
    User getOne(Long id);

    List<User> findAllByName(String name);
    List<User>  findByName (String name);
    List<User>  findAllBy ();
    Page findAllByRolesAndActive(Set<Role> roles, Boolean active, Pageable pageable);

    Page findAllByRoles(Set<Role> roles, Pageable pageable);

    Page findAllByActive(int active,Pageable pageable);

}