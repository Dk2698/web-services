package com.kumar.restwebservices.restfulwebservices.controllers;

import com.kumar.restwebservices.restfulwebservices.beans.HelloWorldBean;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {

    @RequestMapping(method = RequestMethod.GET,path = "/hello-world")
    public  String helloWorld(){
        return "Hello World";
    }

    @GetMapping(path = "/hello-world1")
    public  String helloWorld1(){
        return "Hello World 1";
    }
    // return json mapping with bean
    @GetMapping(path = "/hello-world-bean")
    public  HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("hello world");
    }

    // path parameters
    // /users/{id}/todos/{id} => /users/2/todos/200
    @GetMapping(path = "/hello-world/path-variable/{name}")
    public  HelloWorldBean helloWorldPathVariable(@PathVariable String name){
        return new HelloWorldBean(
                String.format( "hello world, %s ", name));
    }
}
