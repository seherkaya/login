package com.gpch.login.controller;

import com.google.gson.Gson;
import com.gpch.login.model.Role;
import com.gpch.login.model.User;
import com.gpch.login.model.projectEnum;
import com.gpch.login.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/")
public class LoginController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping(value = "/giris", method = RequestMethod.GET)
    public String giris() {
        return "giris";
    }
    @ResponseBody
    @RequestMapping(value = "/girisAPI", method = RequestMethod.POST)
    public String giris(@RequestBody User user)
    {
        return new Gson().toJson( userServiceImpl.login( user));
    }

    @RequestMapping(value = "/kayit", method = RequestMethod.GET)
    public String kullancici() {
        return "kayit";
    }

    // return new Gson().toJson( user );
    @ResponseBody //This annotation provides to return String from method
    @RequestMapping(value = "/yeniKullaniciAPI", method = RequestMethod.POST)
    public String kullaniciOlustur(@RequestBody User user ) {

            return new Gson().toJson( userServiceImpl.saveUser( user, projectEnum.Roles.USER ) );
    }

}




