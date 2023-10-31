package com.sai.mongostore.message;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sai.mongostore.model.RegistrationDetails;
import com.sai.mongostore.repository.RegistrationDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
public class RegistrationListner {

    @Autowired
    RegistrationDetailsRepository registrationDetailsRepository;
    @Autowired
    ObjectMapper objectMapper;

    @KafkaListener(topics = "register")
    public void register(String message){
        try{
            RegistrationDetails details = objectMapper.readValue(message, RegistrationDetails.class);
            registrationDetailsRepository.save(details);

        } catch (JsonProcessingException jpe) {
            log.error("Unable to Process Message: ");
            log.debug("Unable to Process Message: "+ message);
        } catch (Exception e){
            log.error("Unexpected exception: "+ e.getMessage());
        }

    }
}
