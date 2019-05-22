package com.gpch.login.controller;

import com.google.gson.Gson;
import com.gpch.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String listeDondur(@RequestParam(required = false, value = "page") Integer page) {
        page =0 ;
        return new Gson().toJson( userService.pageTest( page ) );
    }

}
