package com.seshasayee.jApp.service;

import com.seshasayee.jApp.entity.User;
import com.seshasayee.jApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.when;


public class UserDetailsServiceImplV2Tests {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Disabled
    void loadUserByUserNameTest(){
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("ram").password("skmskmkdcmc").roles(new ArrayList<>()).build());
        UserDetails userDetails = userDetailsService.loadUserByUsername("ram");
        Assertions.assertNotNull(userDetails);
    }

}
