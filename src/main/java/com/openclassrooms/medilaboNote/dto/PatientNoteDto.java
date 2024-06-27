package com.openclassrooms.medilaboNote.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientNoteDto {

    private String patientId;
    private String patientName;
    private String note;
}
