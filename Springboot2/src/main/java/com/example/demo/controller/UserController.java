package com.example.demo.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@RequestMapping(value = "/users/{username}",method = RequestMethod.GET,consumes="application/json")
    public String getUser(@PathVariable String username, @RequestParam String pwd){
        return "Welcome,"+username;
    }

	@RequestMapping("/hello1")
    public String hello1(){
        return "hello1";
    }
	
	@RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}