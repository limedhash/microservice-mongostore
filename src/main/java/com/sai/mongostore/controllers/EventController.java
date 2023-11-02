package com.sai.mongostore.controllers;

import com.sai.commons.exception.NotFoundException;
import com.sai.commons.objects.AEventDetails;
import com.sai.mongostore.controllers.aop.RestLog;
import com.sai.mongostore.model.EventDetails;
import com.sai.mongostore.repository.EventDetailRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventDetailRepository eventDetailRepository;

    @GetMapping(value = "/{id}")
    @RestLog(uri = "/api/event")
    public ResponseEntity<EventDetails> getEventByID(@PathVariable("id") String id){
        EventDetails ed = eventDetailRepository.findAllById(id);
        if(ObjectUtils.isEmpty(ed)){
            throw new NotFoundException("EventId", "No Event found with ID: "+ id);
        }
        return new ResponseEntity<EventDetails>(ed, HttpStatus.OK);
    }

    @PostMapping
    @RestLog(uri = "/api/event/create")
    public ResponseEntity<Void> create(@RequestBody @Validated AEventDetails details){
        EventDetails eventDetails = new EventDetails();
        BeanUtils.copyProperties(details, eventDetails);
        eventDetailRepository.save(eventDetails);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    @RestLog(uri = "/api/event/delete")
    public ResponseEntity<Void> delete(@PathVariable("id") String id){
        eventDetailRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/search")
    @RestLog(uri = "/api/event/search")
    public ResponseEntity<List<EventDetails>> search(@RequestParam("searchkey") String name){
        List<EventDetails> ed = eventDetailRepository.findAllByName(name);

        return new ResponseEntity<List<EventDetails>>(ed, HttpStatus.OK);
    }

}
