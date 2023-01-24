package com.kumar.restwebservices.restfulwebservices.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.kumar.restwebservices.restfulwebservices.beans.User;
import com.kumar.restwebservices.restfulwebservices.dao.UserDaoService;
import com.kumar.restwebservices.restfulwebservices.exceptions.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private UserDaoService service;

    public UserController(UserDaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> retrieveAllUsers(){
        return  service.findAll();
    }


    // /api/v1/users/1
    @GetMapping("{id}")
    public User retrieveUsers(@PathVariable int id){
        User user = service.findOne(id);

        if(user == null){
            throw  new UserNotFoundException("id:"+id);
        }
        return  user;
    }

    @PostMapping
    public ResponseEntity<User> createuser(@Valid  @RequestBody User user){
        User savedUser = service.save(user);
        // // current urls => /users/{id}, user.getId;
        // return URI
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("{id}")
    public void deleteUsers(@PathVariable int id){
         service.deleteById(id);

//        if(user == null){
//            throw  new UserNotFoundException("id:"+id);
//        }
    }

    // http://localhost:8080/users
    //EntityModel
    //WebMvcLinkBuilder
    @GetMapping("/v2/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
        User user = service.findOne(id);

        if(user == null){
            throw  new UserNotFoundException("id:"+id);
        }
        EntityModel<User> entityModel = EntityModel.of(user);
        // builder link
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));

        return  entityModel;
    }
}
