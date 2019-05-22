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
    public String listeDondur(@RequestParam(required = false, value = "name") String name,
                              @RequestParam( required = true, value = "pageNo") Integer pageNo)
    {if(pageNo== null){pageNo=0;}
        return new Gson().toJson( userServiceImpl.searchAll( name,pageNo ) );
    }



}
