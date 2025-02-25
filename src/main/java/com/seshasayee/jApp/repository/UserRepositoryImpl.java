package com.seshasayee.jApp.repository;

import com.seshasayee.jApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    //use import org.springframework.data.mongodb.core.query.Query;
    public List<User> getUserForSA(){
        Query query = new Query();
//        query.addCriteria(Criteria.where("userName").is("ajay"));
//        query.addCriteria(Criteria.where("field").ne("value"));
//        query.addCriteria(Criteria.where("age").gte(22));
//        query.addCriteria(Criteria.where("email").exists(true));
//        query.addCriteria(Criteria.where("email").ne(null).ne(""));

//        Criteria criteria = new Criteria();
//        query.addCriteria(criteria.andOperator(
//                Criteria.where("email").exists(true),
//                Criteria.where("sentimentAnalysis").is(true)
//        ));
        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$"));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
//        query.addCriteria(Criteria.where("userName").nin("virat","rohit"));
//        query.addCriteria(Criteria.where("userName").in("shreyas","gill"));
//        query.addCriteria(Criteria.where("roles").in("USER","ADMIN"));

        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }

}
