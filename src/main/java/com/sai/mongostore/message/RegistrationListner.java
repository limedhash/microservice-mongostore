package com.sai.mongostore.message;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sai.commons.payload.EmailMessage;
import com.sai.mongostore.MongoStoreConfig;
import com.sai.mongostore.model.EventDetails;
import com.sai.mongostore.model.RegistrationDetails;
import com.sai.mongostore.repository.EventDetailRepository;
import com.sai.mongostore.repository.RegistrationDetailsRepository;
import jakarta.validation.constraints.Email;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Slf4j
public class RegistrationListner {

    @Autowired
    RegistrationDetailsRepository registrationDetailsRepository;
    EventDetailRepository eventDetailRepository;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    KafkaProducer<String, String> producer;

    @Autowired
    MongoStoreConfig config;

    @KafkaListener(topics = "register")
    public void register(String message){
        try{
            RegistrationDetails details = objectMapper.readValue(message, RegistrationDetails.class);
            if(isValid(details)){
                registrationDetailsRepository.save(details);

                EmailMessage emailMessage = new EmailMessage();
                emailMessage.setFrom(config.getFromEmail());
                emailMessage.setTo(details.getEmail());
                String payload = objectMapper.writeValueAsString(emailMessage);
                ProducerRecord<String, String> producerRecord =
                        new ProducerRecord<>(config.getEmailTopic(), payload);

                producer.send(producerRecord);
            } else {
               log.error("Registration Is Invalid");
            }


        } catch (JsonProcessingException jpe) {
            log.error("Unable to Process Message: ");
            log.debug("Unable to Process Message: "+ message);
        } catch (Exception e){
            log.error("Unexpected exception: "+ e.getMessage());
        }

    }

    public boolean isValid(RegistrationDetails details){
        boolean retVal = true;
        EventDetails ed = eventDetailRepository.findAllById(details.getId());

        if(ObjectUtils.isEmpty(ed)){
            retVal = false;
        } else {
            List<RegistrationDetails> rds =  registrationDetailsRepository.findAllByEventId(details.getEventId());
            if(!ObjectUtils.isEmpty(rds)){
                if( rds.size() != -1 && rds.size() >= ed.getLimit()){
                    retVal = false;
                }
            }
        }

        return retVal;
    }
}
