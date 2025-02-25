package com.seshasayee.jApp.service;

import com.seshasayee.jApp.entity.JEntry;
import com.seshasayee.jApp.entity.User;
import com.seshasayee.jApp.repository.JEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JEntryService {
    @Autowired
    private JEntryRepository jEntryRepository;
    @Autowired
    private UserService userService;



    @Transactional
    public void saveEntry(JEntry jEntry, String userName){
        try{
            User user = userService.findByUserName(userName);
            jEntry.setDate(LocalDateTime.now());
            JEntry saved = jEntryRepository.save(jEntry);
            user.getJEntries().add(saved);
            userService.saveUser(user);
        }
        catch (Exception e){
            throw new RuntimeException("An error occurred while saving the entry.",e);
        }

    }
    public void saveEntry(JEntry jEntry){
        jEntryRepository.save(jEntry);
    }
    public List<JEntry> jEntries(){
        return jEntryRepository.findAll();
    }
    public Optional<JEntry> findById(ObjectId id){
        return jEntryRepository.findById(id);
    }
    @Transactional
    public boolean deleteById(ObjectId id,String userName){
        boolean removed = false;
        try{
            User user = userService.findByUserName(userName);
            removed = user.getJEntries().removeIf(x -> x.getId().equals(id));
            if (removed){
                userService.saveUser(user);
                jEntryRepository.deleteById(id);
            }
        }
        catch (Exception e){
            log.error("Error " + e);
            throw new RuntimeException("An error occurred while deleting the entry.",e);
        }
        return removed;
    }

}
