package com.seshasayee.jApp.repository;

import com.seshasayee.jApp.entity.JEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface JEntryRepository extends MongoRepository <JEntry, ObjectId>{
}
