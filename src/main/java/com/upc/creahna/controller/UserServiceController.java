package com.upc.creahna.controller;

import com.upc.creahna.exception.UserNotFoundException;
import com.upc.creahna.model.User;
import com.upc.creahna.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserServiceController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        userService.createUser(user);
        return new ResponseEntity<>("User is created successfully", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        boolean  isExist = userService.isUserExist(id);
        if (!isExist){
            throw  new UserNotFoundException();
        }else{
            user.setId(id);
            userService.updateUser(user);
            return new ResponseEntity<>("User is updated successfully", HttpStatus.OK);
        }
    }
    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteUser(@PathVariable("id") int id) {
        boolean  isExist = userService.isUserExist(id);
        if (!isExist){
            throw  new UserNotFoundException();
        }else{
            userService.deleteUser(id);
            return new ResponseEntity<>("User is deleted successfully", HttpStatus.OK);
        }
    }
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getUserById(@PathVariable("id") int id) {
        boolean  isExist = userService.isUserExist(id);
        if (!isExist){
            throw  new UserNotFoundException();
        }else{
            User user = userService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }
    @RequestMapping(value = "/user/{id}", method = RequestMethod.HEAD, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> isUserExist(@PathVariable int id) {
        boolean exists = userService.isUserExist(id);
        return exists ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
