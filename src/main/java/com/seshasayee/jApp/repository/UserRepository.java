package com.seshasayee.jApp.repository;

import com.seshasayee.jApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends MongoRepository <User, ObjectId>{

    User findByUserName(String userName);
    void deleteByUserName(String userName);

}
