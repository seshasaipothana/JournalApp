package com.seshasayee.jApp.controller;

import com.seshasayee.jApp.entity.JEntry;
import com.seshasayee.jApp.entity.User;
import com.seshasayee.jApp.service.JEntryService;
import com.seshasayee.jApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JEntryControllerV2 {

    @Autowired
    private JEntryService jEntryService;
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesOfUser(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JEntry> journalEntries = user.getJEntries();
        if(journalEntries != null && !journalEntries.isEmpty()){
            return new ResponseEntity<>(journalEntries,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<JEntry> getEntryById(@PathVariable ObjectId id){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JEntry> collect = user.getJEntries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if (!collect.isEmpty()){
            Optional<JEntry> jEntry= jEntryService.findById(id);
            if (jEntry.isPresent()){
                return new ResponseEntity<JEntry>(jEntry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<JEntry>(HttpStatus.NOT_FOUND);
        //return jEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping
    public ResponseEntity<JEntry> createEntry(@RequestBody JEntry entry) {
        try{
            SecurityContext securityContext = SecurityContextHolder.getContext();
            Authentication authentication = securityContext.getAuthentication();
            String userName = authentication.getName();
            jEntryService.saveEntry(entry,userName);
            return new ResponseEntity<>(entry,HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity< >(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId id){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String userName = authentication.getName();
        boolean removed = jEntryService.deleteById(id, userName);
        if (removed) {
            return new ResponseEntity<JEntry>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateByID(@PathVariable ObjectId id,@RequestBody JEntry newEntry){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JEntry> collect = user.getJEntries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if (!collect.isEmpty()){
            Optional<JEntry> jEntry= jEntryService.findById(id);
            if (jEntry.isPresent()){
                JEntry oldEntry = jEntry.get();
                oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("")? newEntry.getTitle() : oldEntry.getTitle());
                oldEntry.setContent(newEntry.getContent() != null && !newEntry.getTitle().equals("")? newEntry.getContent() : oldEntry.getContent());
                jEntryService.saveEntry(oldEntry);
                return new ResponseEntity<>(oldEntry,HttpStatus.OK);
            }
        }

        JEntry jEntryOld = jEntryService.findById(id).orElse(null);
        if(jEntryOld != null){

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}