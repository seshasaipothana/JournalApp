package com.seshasayee.jApp.service;


import com.seshasayee.jApp.entity.User;
import com.seshasayee.jApp.repository.UserRepository;
import org.junit.jupiter.api.*;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;


    @Test
    public void testFindByUserName(){
        assertNotNull(userRepository.findByUserName("suresh"));
        assertNotNull(userRepository.findByUserName("ajay"));


    }
    @Test
    @Disabled
    public void testJournalsByUserName(){
        User user = userRepository.findByUserName("ajay");
        assertTrue(!user.getJEntries().isEmpty());

    }

    @Disabled
    @ParameterizedTest
    @CsvSource({"1,2,3","2,3,5"})
    public void test(int a , int b ,int expected ){
        assertEquals(expected,a+b);

    }
    @Disabled
    @ParameterizedTest
    @CsvSource({
                    "ram",
                    "suresh"
            })
    public void testFindByUserName1(String name){
        assertNotNull(userRepository.findByUserName(name),"failed for : " + name);
    }


    @ParameterizedTest
    @Disabled
    @ValueSource(strings = {
            "ram",
            "suresh"
    })
    public void testFindByUserName2(String name){
        assertNotNull(userRepository.findByUserName(name),"failed for : " + name);
    }
    //Before running each test code run this code/If you want to do something before
    // running/testing each code like setting something
//    @BeforeEach void setUp(){
//
//    }
//    //Before running all test codes say like 1000 or more run this code/If you want to do something before
//    // running/testing all codes like setting something
//    @BeforeAll
//    void setUp1(){
//
//    }
//    //After running each test code run this code/If you want to do something After
//    // running/testing each code like setting something
//    @AfterEach
//    void setUp2(){
//
//    }
//    //After running all test codes say like 1000 or more run this code/If you want to do something After
//    // running/testing all codes like setting something
//    @BeforeAll
//    void setUp3(){
//
//    }


    @ParameterizedTest
    @Disabled
    @ValueSource(ints = {
            1,2,3,4,5
    })
    public void testX(int num){
        assertTrue( num % 2 == 1);
    }

    @ParameterizedTest
    @Disabled
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user){
        assertTrue(userService.saveNewUser(user));
    }

}
