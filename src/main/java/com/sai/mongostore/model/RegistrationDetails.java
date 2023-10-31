package com.sai.mongostore.model;

import com.sai.commons.objects.ARegistrationDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document("registrationdetails")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDetails extends ARegistrationDetails {

    @Id
    private String id;
}
