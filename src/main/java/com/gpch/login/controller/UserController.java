package com.gpch.login.controller;

import com.google.gson.Gson;
import com.gpch.login.model.User;
import com.gpch.login.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping(value = "/liste", method = RequestMethod.GET)
    public String liste() {
        return "liste";
    }


    // return new Gson().toJson( user );
    @ResponseBody //This annotation provides to return String from method
    @RequestMapping(value = "/listeAPI", method = RequestMethod.POST)
    public String listeDondur(@RequestParam(required = false, value = "name") String name) {
        return new Gson().toJson( userServiceImpl.searchAll( name ) );
    }

   /* @RequestMapping(value = "/guncelle", method = RequestMethod.GET)
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
        return new Gson().toJson( userServiceImpl.updateUser( user ));

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
                      @RequestParam(required = false, value = "phone") int active,User user
    ) {

        return new Gson().toJson( userServiceImpl.deleteUser( user ));

    }*/
}
