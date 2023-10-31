package com.sai.mongostore.repository;

import com.sai.mongostore.model.RegistrationDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RegistrationDetailsRepository  extends MongoRepository<RegistrationDetails, String> {

    @Query(value="{eventId:'?0'}")
    List<RegistrationDetails> findAll(String eventId);
}
