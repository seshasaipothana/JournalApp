package com.seshasayee.jApp.scheduler;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
public class UserSchedulerTests {
    @Autowired
    private UserScheduler userScheduler;


    @Test
    public void testFetchUserAndSaSendMail(){
        userScheduler.fetchUsersAndSendSaMail();
    }
}
