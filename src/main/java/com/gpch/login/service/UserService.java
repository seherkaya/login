package com.gpch.login.service;

import com.gpch.login.model.Role;
import com.gpch.login.model.User;
import com.gpch.login.repository.RoleRepository;
import com.gpch.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;
import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findUserByEmail(String email) {
        return userRepository.findByEmail( email );
    }

    public List<User> findUserByName(String name) {

        List<User> user= userRepository.findByName( name );
        for(int i=0; i<user.size();i++){
            user.get( i ).setPassword( "" );

        }
        return user;
    }

    public List<User> findAllUser() {

        List<User> user= userRepository.findAll();
        for(int i=0; i<user.size();i++){
            user.get( i ).setPassword( "" );
        }

        return user;
    }

    public User saveUser(User user) {

        /*User user= new User();
        user.setName( name );
        user.setLastName( lastName );
        user.setEmail( email );
        user.setPassword( password );
        user.setActive( 1 );*/

        return userRepository.save( user );
    }

    public User updateUser(String name, String lastName, String email, String password, int active) {

        User user = userRepository.findByEmail( email );

        if (user.getEmail() != email) {
            user.setName( name );
            user.setLastName( lastName );
            user.setPassword( password );
            user.setActive( active );

            return userRepository.save( user );
        } else {
            return user;
        }
    }

    public List<User> getByNameUser(String name) {

        List<User> user = userRepository.findByName( name );

        return user;
    }

    public List<User> getAllUser() {

        List<User> user = userRepository.findAllBy();

        return user;
    }

    // kullanıcı kayıt
    //kullanıcı update
    //isme göre kullanıcı listesi getir
    //tüm kullanıcıları listelere

}