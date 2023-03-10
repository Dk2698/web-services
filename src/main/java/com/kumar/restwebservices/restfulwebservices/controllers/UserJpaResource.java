package com.kumar.restwebservices.restfulwebservices.controllers;

import com.kumar.restwebservices.restfulwebservices.beans.User;
import com.kumar.restwebservices.restfulwebservices.exceptions.UserNotFoundException;
import com.kumar.restwebservices.restfulwebservices.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/api/jpa/users")
public class UserJpaResource {
    private UserRepository repository;
    public UserJpaResource(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<User> retrieveAllUsers(){
        return  repository.findAll();
    }

    @GetMapping("{id}")
    public EntityModel<User> retrieveUsers(@PathVariable int id){
        Optional<User> user = repository.findById(id);

        if(user.isEmpty()){
            throw  new UserNotFoundException("id:"+id);
        }
        EntityModel<User> entityModel = EntityModel.of(user.get());
        // builder link
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));

        return  entityModel;
    }

    @DeleteMapping("{id}")
    public void deleteUsers(@PathVariable int id) {
        repository.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = repository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
