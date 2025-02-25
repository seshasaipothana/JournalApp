package com.seshasayee.jApp.controller;

import com.seshasayee.jApp.entity.User;
import com.seshasayee.jApp.service.UserDetailsServiceImpl;
import com.seshasayee.jApp.service.UserService;
import com.seshasayee.jApp.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    private JwtUtil jwtUtil;
    @GetMapping("/health-check")
    public String healthCheck(){
        return "OK";
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody User user){
        userService.saveNewUser(user);
    }
    @PostMapping("/login")
    public ResponseEntity<String> logIn(@RequestBody User user){
        try{
           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
            UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(user.getUserName());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<String>(jwt, HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Exception occurred while createAuthenticationToken ", e);
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }


}
