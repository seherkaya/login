package com.gpch.login.service;

import com.gpch.login.model.ApiResponse;
import com.gpch.login.model.User;
import com.gpch.login.repository.RoleRepository;
import com.gpch.login.repository.UserRepository;
import com.gpch.login.system.SystemGeneral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findUserByEmail(String email) {
        User user =userRepository.findByEmail( email );
        user.setPassword( "" );
        return user ;
    }

    public User findUserByID(int id) {
        User user = userRepository.findById( id );
        user.setPassword("");
        return user;
    }

    public List<User> findUserByName(String name) {

        List<User> user = userRepository.findByName( name );
        for (int i = 0; i < user.size(); i++) {
            user.get( i ).setPassword( "" );

        }
        return user;
    }

    public List<User> findAllUser() {

        List<User> user = userRepository.findAll();
        for (int i = 0; i < user.size(); i++) {
            user.get( i ).setPassword( "" );
        }

        return user;
    }

    public ApiResponse searchAll(String name) {
        ApiResponse response = new ApiResponse();

        List<User> list;
        if (name != null) {
            list = userRepository.findByName( name );
        } else {
            list = userRepository.findAll();
        }

        List<User> user = userRepository.findAll();
        for (int i = 0; i < user.size(); i++) {
            user.get( i ).setPassword( null );
        }

        response.setData( list );
        response.setSuccessful( true );

        return response;
    }

    public ApiResponse login (User user) {
        ApiResponse response = new ApiResponse();
        User userExist =userRepository.findByEmail( user.getEmail() );
        if(userExist!= null)
        {
            if(userExist.getPassword().equalsIgnoreCase(user.getPassword()  )){
                response.setSuccessful( true );
                response.setMessage( "User deleted" );
                return response;

            }else {
                response.setSuccessful( false );
                response.setMessage( "User password is not valid" );
                return response;
            }
        }else{

            response.setSuccessful( false );
            response.setMessage( "Email is not valid" );
            return response;
        }
    }

    public ApiResponse saveUser(User user) {
        ApiResponse response = new ApiResponse();

        String email = user.getEmail();

        if (!SystemGeneral.validateEmail( email )) {
            response.setSuccessful( false );
            response.setMessage( "Email is not valid" );
            return response;
        }

        User existingUser = userRepository.findByEmail( email );
        if (existingUser != null) {
            response.setSuccessful( false );
            response.setMessage( "User exists" );
            return response;
        }

        User kaydedilecek = new User();
        kaydedilecek.setName( user.getName() );
        kaydedilecek.setLastName( user.getLastName());
        kaydedilecek.setEmail( user.getEmail() );
        kaydedilecek.setPhone( user.getPhone() );
        kaydedilecek.setPassword( user.getPassword() );
        kaydedilecek.setActive( user.getActive() );

        User kaydedilen = userRepository.save( kaydedilecek );
        response.setSuccessful( true );
        response.setData( kaydedilen );
        return response;
    }

    public ApiResponse updateUser(User user) {
        ApiResponse response = new ApiResponse();

        if (userRepository.findById( user.getId() ) != null) {

            if (user.getName() != null)
                user.setName( user.getName() );
            if (user.getLastName() != null)
                user.setLastName( user.getLastName() );
            if (user.getEmail() != null){
                if (!SystemGeneral.validateEmail( user.getEmail() )) {
                    response.setSuccessful( false );
                    response.setMessage( "Email is not valid" );
                    return response;
                }

                User existingUser = userRepository.findByEmail( user.getEmail() );
                if (existingUser != null) {
                    response.setSuccessful( false );
                    response.setMessage( "User exists" );
                    return response;
                }
            }
                user.setEmail( user.getEmail() );
            if (user.getPassword() != null)
                user.setPassword( user.getPassword() );
            if (user.getPhone() != null)
                user.setPhone( user.getPhone() );
            if (user.getActive() != 1 || user.getActive() != 0)
                user.setActive( user.getActive() );
            User kaydedilen = userRepository.save( user );
            response.setSuccessful( true );
            response.setData( kaydedilen );
            return response;
        } else {
            response.setSuccessful( false );
            response.setMessage( "This User not founded" );

            return response;
        }
    }

    public ApiResponse deleteUser(User user) {
        ApiResponse response = new ApiResponse();
        User silinecek = userRepository.findById( user.getId() );

        if (silinecek != null) {

            userRepository.delete( silinecek );
            response.setSuccessful( true );
            response.setMessage( "User deleted" );
            return response;
        } else {
            response.setSuccessful( false );
            response.setMessage( "User exists" );
            return response;
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