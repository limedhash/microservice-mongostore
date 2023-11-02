package com.sai.mongostore.model;

import com.sai.commons.objects.AEventDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

@Document("eventdetails")
@Getter
@Setter
public class EventDetails extends AEventDetails {
    @Id
    private String id;



}
