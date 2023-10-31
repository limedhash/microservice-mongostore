package com.sai.mongostore.controllers;

import com.sai.commons.exception.NotFoundException;
import com.sai.mongostore.model.EventDetails;
import com.sai.mongostore.repository.EventDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @Autowired
    EventDetailRepository eventDetailRepository;

    public ResponseEntity<EventDetails> getEventByID(String id){

        EventDetails ed = eventDetailRepository.findAllById(id);
        if(ObjectUtils.isEmpty(ed)){
            throw new NotFoundException("EventId", "No Event found with ID: "+ id);
        }

        return new ResponseEntity<EventDetails>(ed, HttpStatus.OK);
    }
}
