package com.openclassrooms.medilaboNote.controller;

import com.openclassrooms.medilaboNote.dto.PatientNoteDto;
import com.openclassrooms.medilaboNote.model.PatientNote;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//TODO: mettre la doc swagger
@RequestMapping("/api")
public interface PatientNoteControllerSwagger {


    @GetMapping("/note")
    ResponseEntity<List<PatientNote>> getAllPatient();

    @GetMapping("/note/{id}")
    ResponseEntity<List<PatientNote>> getAllPatientById(@PathVariable String id);

    @PostMapping("/note")
    ResponseEntity<PatientNote> createPatientNote(@RequestBody PatientNoteDto patientNote);

}
