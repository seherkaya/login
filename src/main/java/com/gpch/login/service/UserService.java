package com.gpch.login.service;


import com.gpch.login.model.ApiResponse;
import com.gpch.login.model.User;
import com.gpch.login.model.projectEnum;

import java.util.List;


public interface UserService {

    User findUserByEmail(String email);

    User findUserByID(int id);

    List<User> findUserByName(String name);

    List<User> findAllUser();

    ApiResponse searchAll(String name,Integer pageNo);

    ApiResponse login (User user);

    ApiResponse saveUser(User user, projectEnum.Roles roles );

    ApiResponse updateUser(User user);

    ApiResponse deleteUser(User user);

    List<User> getByNameUser(String name) ;
    List<User> getAllUser() ;

    ApiResponse pageTest(Integer pageNo);

    // kullanıcı kayıt
    //kullanıcı update
    //isme göre kullanıcı listesi getir
    //tüm kullanıcıları listelere
}
