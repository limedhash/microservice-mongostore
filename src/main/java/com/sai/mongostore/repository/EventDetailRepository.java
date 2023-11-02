package com.sai.mongostore.repository;

import com.sai.mongostore.model.EventDetails;
import com.sai.mongostore.model.RegistrationDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EventDetailRepository extends MongoRepository<EventDetails, String> {

    @Query(value="{id:'?0'}")
    EventDetails findAllById(String id);

    @Query(value="{name:'?0'}")
    List<EventDetails> findAllByName(String name);
}
