package com.openclassrooms.medilaboNote.controller;

import com.openclassrooms.medilaboNote.dto.PatientNoteDto;
import com.openclassrooms.medilaboNote.model.PatientNote;
import com.openclassrooms.medilaboNote.service.PatientNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientNoteController implements PatientNoteControllerSwagger {

    @Autowired
    private PatientNoteService patientNoteService;

    @Override
    public ResponseEntity<List<PatientNote>> getAllPatient() {
        return ResponseEntity.ok(patientNoteService.getAllPatient());
    }

    @Override
    public ResponseEntity<List<PatientNote>> getAllPatientByPatientId(String id) {
        return ResponseEntity.ok(patientNoteService.getAllPatientByPatientId(id));
    }

    @Override
    public ResponseEntity<PatientNote> createPatientNote(@RequestBody PatientNoteDto patientNote) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientNoteService.createPatientNote(patientNote));
    }

    @Override
    public void deletePatientNote(String id) {
        patientNoteService.deletePatientNote(id);
    }

    @Override
    public ResponseEntity<String> getPatientIdByPatientNoteId(String patientNoteId) {
        return ResponseEntity.ok(patientNoteService.getPatientIdByPatientNoteId(patientNoteId));
    }

}
