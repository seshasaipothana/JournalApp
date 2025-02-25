package com.seshasayee.jApp.service;

import com.seshasayee.jApp.entity.User;
import com.seshasayee.jApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UserDetailsServiceImplTests {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @MockBean
    UserRepository userRepository;

    @Test
    @Disabled
    void loadUserByUserNameTest(){
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("ram").password("skmskmkdcmc").roles(new ArrayList<>()).build());
        UserDetails userDetails = userDetailsService.loadUserByUsername("ram");
        Assertions.assertNotNull(userDetails);
    }

}
