package com.sai.mongostore.repository;

import com.sai.mongostore.model.RegistrationDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RegistrationDetailsRepository  extends MongoRepository<RegistrationDetails, String> {

    @Query(value="{eventId:'?0'}")
    List<RegistrationDetails> findAllByEventId(String eventId);
}
