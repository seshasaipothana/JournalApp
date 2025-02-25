package com.seshasayee.jApp.controller;

import com.seshasayee.jApp.cache.AppCache;
import com.seshasayee.jApp.entity.User;
import com.seshasayee.jApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired AppCache appCache;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
        List<User> usersList = userService.getUsersList();
        if (usersList != null && !usersList.isEmpty()){
            return new ResponseEntity<>(usersList, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping("/create-admin-user")
    public void createAdminUser(@RequestBody User user){
        userService.saveAdmin(user);
    }

    @GetMapping("clear-app-cache")
    public void clearAppCache(){
         appCache.init();
    }

}
