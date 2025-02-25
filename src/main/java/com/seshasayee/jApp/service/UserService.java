package com.seshasayee.jApp.service;

import com.seshasayee.jApp.entity.JEntry;
import com.seshasayee.jApp.entity.User;
import com.seshasayee.jApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private static final PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
    //private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;


    public boolean saveNewUser(User user){
       try{
           user.setPassword(passwordEncoder.encode(user.getPassword()));
           user.setRoles(Arrays.asList("USER"));
           userRepository.save(user);
           return true;
       }
       catch (Exception e){
           //logger.info("error!!!!!");
           //logger.error("error!!!!!{}",user.getUserName(),2);
           log.error("error!!!!!{}",user.getUserName(),e);
           return false;
       }
    }
    public boolean saveNewUser(User userInDB,User user){
        try{
            userInDB.setUserName(user.getUserName());
            userInDB.setPassword(passwordEncoder.encode(user.getPassword()));
            userInDB.setCity(user.getCity());
            userInDB.setEmail(user.getEmail());
            userInDB.setSentimentAnalysis(user.getSentimentAnalysis());
            userInDB.setRoles(Arrays.asList("USER"));
            userRepository.save(userInDB);
            return true;
        }
        catch (Exception e){
            //logger.info("error!!!!!");
            //logger.error("error!!!!!{}",user.getUserName(),2);
            log.error("error!!!!!{}",user.getUserName(),e);
            return false;
        }
    }
    public void saveUser(User user){
        userRepository.save(user);
    }
    public void saveUser(User userInDB,User user){
        userInDB.setId(userInDB.getId());
        if(user.getUserName() == null){
            userInDB.setUserName(userInDB.getUserName());
        }
        if(user.getPassword() == null){
            userInDB.setPassword(userInDB.getPassword());
        }
        if (user.getCity() == null){
            userInDB.setCity(userInDB.getCity());
        }
        else {
            userInDB.setCity(user.getCity());
        }
        if(user.getEmail() == null){
            userInDB.setEmail(userInDB.getEmail());
        }
        else{
            userInDB.setEmail(user.getEmail());
        }
        if(user.getSentimentAnalysis() == null){
            userInDB.setSentimentAnalysis(userInDB.getSentimentAnalysis());
        }
        else{
            userInDB.setSentimentAnalysis(user.getSentimentAnalysis());
        }
        if (user.getJEntries() == null){
            userInDB.setJEntries(userInDB.getJEntries());
        }
        if (user.getRoles() == null){
            userInDB.setRoles(userInDB.getRoles());
        }
        userRepository.save(userInDB);
    }
    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
    }
    public List<User> getUsersList(){
        return userRepository.findAll();
    }
    public Optional<User> getUserById(ObjectId objectId){
        return userRepository.findById(objectId);
    }
    public void deleteUserById(ObjectId objectId){
        userRepository.deleteById(objectId);
    }
    public void deleteByUserName(String userName){
        userRepository.deleteByUserName(userName);
    }
    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}
