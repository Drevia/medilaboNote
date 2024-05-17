package com.openclassrooms.medilaboNote.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "patientNote")
@Getter
@Setter
public class PatientNote {

    @Id
    private String id;
    private String patientId;
    private String patientName;
    private String note;

}
