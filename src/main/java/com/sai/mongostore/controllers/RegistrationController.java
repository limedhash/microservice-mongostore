package com.sai.mongostore.controllers;

import com.sai.commons.exception.NotFoundException;
import com.sai.mongostore.controllers.aop.RestLog;
import com.sai.mongostore.model.EventDetails;
import com.sai.mongostore.model.RegistrationDetails;
import com.sai.mongostore.repository.RegistrationDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    RegistrationDetailsRepository registrationDetailsRepository;

    @GetMapping(value = "/{id}")
    @RestLog(uri = "/api/register")
    public ResponseEntity<RegistrationDetails> getRegistrationByID(@PathVariable("id") String id){
        Optional<RegistrationDetails> result = registrationDetailsRepository.findById(id);
        if(result.isEmpty()){
            throw new NotFoundException("EventId", "No Event found with ID: "+ id);
        }
        return new ResponseEntity<RegistrationDetails>(result.get(), HttpStatus.OK);

    }
}
