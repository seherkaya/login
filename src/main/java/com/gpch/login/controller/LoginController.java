package com.gpch.login.controller;

import javax.validation.Valid;

import com.google.gson.Gson;
import com.gpch.login.model.ApiResponse;
import com.gpch.login.model.User;
import com.gpch.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName( "login" );
        return modelAndView;
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject( "user", user );
        modelAndView.setViewName( "registration" );
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail( user.getEmail() );
        if (userExists != null) {
            bindingResult
                    .rejectValue( "email", "error.user",
                            "There is already a user registered with the email provided" );
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName( "registration" );
        } else {
            // userService.saveUser(user);
            modelAndView.addObject( "successMessage", "User has been registered successfully" );
            modelAndView.addObject( "user", new User() );
            modelAndView.setViewName( "registration" );

        }
        return modelAndView;
    }

    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail( auth.getName() );
        modelAndView.addObject( "userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")" );
        modelAndView.addObject( "adminMessage", "Content Available Only for Users with Admin Role" );
        modelAndView.setViewName( "admin/home" );
        return modelAndView;
    }

    @RequestMapping(value = "/kayit", method = RequestMethod.GET)
    public String kullancici() {
        return "kayit";
    }

    // return new Gson().toJson( user );
    @ResponseBody //This annotation provides to return String from method
    @RequestMapping(value = "/yeniKullaniciAPI", method = RequestMethod.POST)
    public String kullaniciOlustur(@RequestBody User user) {
        return new Gson().toJson( userService.saveUser( user ) );
    }


    @RequestMapping(value = "/liste", method = RequestMethod.GET)
    public String liste() {
        return "liste";
    }


    // return new Gson().toJson( user );
    @ResponseBody //This annotation provides to return String from method
    @RequestMapping(value = "/listeAPI", method = RequestMethod.POST)
    public String listeDondur(@RequestParam(required = false, value = "name") String name) {
        return new Gson().toJson( userService.searchAll( name ) );
    }

    @RequestMapping(value = "/guncelle", method = RequestMethod.GET)
    public String guncelle() {
        return "guncelle";
    }

    // return new Gson().toJson( user );
    @ResponseBody //This annotation provides to return String from method
    @RequestMapping(value = "/guncelleAPI", method = RequestMethod.POST)

    public String guncelle(@RequestParam(value = "id") int id,
                           @RequestParam(required = false, value = "name") String name,
                           @RequestParam(required = false, value = "lastName") String lastName,
                           @RequestParam(required = false, value = "email") String email,
                           @RequestParam(required = false, value = "password") String password,
                           @RequestParam(required = false, value = "phone") String phone,
                           @RequestParam(required = false, value = "phone") int active,
                           @RequestBody User user

    ) {
        int a=5;
            return new Gson().toJson( userService.updateUser( user ));

    }

    @RequestMapping(value = "/sil", method = RequestMethod.GET)
    public String sil() {
        return "sil";
    }

    // return new Gson().toJson( user );
    @ResponseBody //This annotation provides to return String from method
    @RequestMapping(value = "/silAPI", method = RequestMethod.POST)
    public String sil(@RequestParam(value = "id") int id,
                      @RequestParam(required = false, value = "name") String name,
                      @RequestParam(required = false, value = "lastName") String lastName,
                      @RequestParam(required = false, value = "email") String email,
                      @RequestParam(required = false, value = "password") String password,
                      @RequestParam(required = false, value = "phone") String phone,
                      @RequestParam(required = false, value = "phone") int active
    ) {
        User user = userService.findUserByID( id );
        if (user != null) {
            boolean i = userService.deleteUser( id );
            if (i == true) {
                ApiResponse apiResponse = new ApiResponse();
                apiResponse.setSuccessful( true );
                return new Gson().toJson( apiResponse );
            } else {
                ApiResponse apiResponse = new ApiResponse();
                apiResponse.setSuccessful( false );
                return new Gson().toJson( apiResponse );

            }
        } else {
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setSuccessful( false );
            return new Gson().toJson( apiResponse );

        }


    }

}




