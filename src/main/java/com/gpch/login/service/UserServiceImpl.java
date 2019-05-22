package com.gpch.login.service;

import com.gpch.login.model.*;
import com.gpch.login.repository.RoleRepository;
import com.gpch.login.repository.UserRepository;
import com.gpch.login.system.SystemGeneral;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private projectEnum.Roles roles;
    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail( email );
    }

    @Override
    public User findUserByID(int id) {
        return userRepository.findById( id );

    }

    @Override
    public List<User> findUserByName(String name) {

        return userRepository.findByName( name );

    }

    @Override
    public List<User> findAllUser() {

        return userRepository.findAll();

    }

    @Override
    public ApiResponse searchAll(String name, Integer pageNo) {
        ApiResponse response = new ApiResponse();

        List<User> list;
        Page page;
        CustomPageable cs ;
        if (name != null) {
      //      list = userRepository.findByName( name );
            //page= userRepository.findByName( name,PageRequest.of( pageNo,5) );
            cs= new CustomPageable(userRepository.findByName( name,PageRequest.of( pageNo,5) ));
        } else {
      //      list = userRepository.findAll();
            //page= userRepository.findAll( PageRequest.of( pageNo,5) );
            cs= new CustomPageable(userRepository.findAll(PageRequest.of( pageNo,5)));
        }

//        for (int i = 0; i < list.size(); i++) {
//          //  list.get( i ).setPassword( null );
//        }
      /*  for (int i = 0; i < page.getSize(); i++) {
            ((User) page.getContent().get( i )).setPassword( null );

        }*/
        for (int i = 0; i < cs.getList().size(); i++) {
            ((User) cs.getList().get( i )).setPassword( null );

        }
        response.setData( cs );
        response.setSuccessful( true );

        return response;
    }

    @Override
    public ApiResponse login (User user) {
        ApiResponse response = new ApiResponse();
        User userExist =userRepository.findByEmail( user.getEmail() );
        if(userExist!= null)
        {
            if(userExist.getPassword().equalsIgnoreCase(user.getPassword()) ){

                userExist.setActive( 1 );
                response.setData( userExist.getRoles() );
                response.setSuccessful( true );
                response.setMessage( "Login succeed" );
                return response;

            }else {
                response.setSuccessful( false );
                response.setMessage( "User password is not valid or not active" );
                return response;
            }
        }else{

            response.setSuccessful( false );
            response.setMessage( "Email is not valid" );
            return response;
        }
    }

    @Override
    public ApiResponse saveUser(User user, projectEnum.Roles role ) {
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
        kaydedilecek.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        kaydedilecek.setActive( 1);

        Role userRole =  roleRepository.findByRole( role.getRole() );
        kaydedilecek.setRoles( new HashSet<Role>( Arrays.asList( userRole ) ) );

        User kaydedilen = userRepository.save( kaydedilecek );
        response.setSuccessful( true );
        response.setData( kaydedilen );
        return response;
    }

    @Override
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
                user.setPassword( bCryptPasswordEncoder.encode(user.getPassword()));
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

    @Override
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

    @Override
    public List<User> getByNameUser(String name) {

        List<User> user = userRepository.findByName( name );

        return user;
    }

    @Override
    public List<User> getAllUser() {

        List<User> user = userRepository.findAllBy();

        return user;
    }

    @Override
    public ApiResponse pageTest(Integer pageNo) {
        ApiResponse response = new ApiResponse();

        Page page =userRepository.findAllByActive( 1,PageRequest.of( pageNo,2) );

        response.setData( page );
        return response;
    }

    // kullanıcı kayıt
    //kullanıcı update
    //isme göre kullanıcı listesi getir
    //tüm kullanıcıları listelere

}