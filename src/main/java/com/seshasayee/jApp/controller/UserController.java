package com.seshasayee.jApp.controller;

import com.seshasayee.jApp.api.response.WeatherResponse;
import com.seshasayee.jApp.entity.User;
import com.seshasayee.jApp.service.UserService;
import com.seshasayee.jApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private WeatherService weatherService;



    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String userName = authentication.getName();
        User userInDB = userService.findByUserName(userName);
        if(user.getUserName()!= null && !user.getUserName().isEmpty()){

            userService.saveNewUser(userInDB,user);
        }
        else{
            userService.saveUser(userInDB,user);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    @DeleteMapping
    public ResponseEntity<?> deleteByUserName(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String userName = authentication.getName();
        userService.deleteByUserName(userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greetings(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String userName = authentication.getName();
        String city = userService.findByUserName(userName).getCity();
        WeatherResponse weatherResponse = weatherService.getWeather(city);
        String greetings = "";
        if(weatherResponse != null){
            greetings = ", Here is your " +weatherResponse.getLocation() + weatherResponse.getCurrent();
        }
        return new ResponseEntity<>("Hi " + authentication.getName() + greetings,HttpStatus.OK);
    }
}
