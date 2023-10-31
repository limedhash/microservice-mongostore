package com.sai.mongostore.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sai.mongostore.model.EventDetails;
import com.sai.mongostore.model.RegistrationDetails;
import com.sai.mongostore.repository.EventDetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
public class EventListener {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    EventDetailRepository eventDetailRepository;

    @KafkaListener(topics = "createevent")
    public void createEvent(String message){
        try{
            EventDetails event = objectMapper.readValue(message, EventDetails.class);
            eventDetailRepository.save(event);

        } catch (JsonProcessingException jpe) {
            log.error("Unable to Process Message: ");
            log.debug("Unable to Process Message: "+ message);
        } catch (Exception e){
            log.error("Unexpected exception: "+ e.getMessage());
        }
    }
}
