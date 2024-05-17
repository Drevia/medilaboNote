package com.openclassrooms.medilaboNote.controller;

import com.openclassrooms.medilaboNote.model.PatientNote;
import com.openclassrooms.medilaboNote.service.PatientNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MedilaboNoteController {

    @Autowired
    private PatientNoteService patientNoteService;

    @GetMapping("/note")
    public ResponseEntity<List<PatientNote>> getAllPatient() {
        return ResponseEntity.ok(patientNoteService.getAllPatient());
    }

    @GetMapping("/note/{id}")
    public ResponseEntity<List<PatientNote>> getAllPatientById(@PathVariable String id) {
        return ResponseEntity.ok(patientNoteService.getAllPatientByPatientId(id));
    }

    @PostMapping("/note")
    public ResponseEntity<List<PatientNote>> createPatientNote(@RequestBody PatientNote patientNote) {
        return null;
    }

}
