package com.seshasayee.jApp.repository;

import com.seshasayee.jApp.entity.ConfigJournalAppEntity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface ConfigJournalAppRepository extends MongoRepository <ConfigJournalAppEntity, ObjectId>{
}
