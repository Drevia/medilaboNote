package com.openclassrooms.medilaboNote.service;

import com.openclassrooms.medilaboNote.dto.PatientNoteDto;
import com.openclassrooms.medilaboNote.model.PatientNote;
import com.openclassrooms.medilaboNote.repository.PatientNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientNoteService {

    @Autowired
    private PatientNoteRepository repository;

    public List<PatientNote> getAllPatient() {
        return repository.findAll();
    }

    public List<PatientNote> getAllPatientByPatientId(String patientId) {
        return repository.findByPatientId(patientId);
    }

    public PatientNote createPatientNote(PatientNoteDto patientNoteDto) {
        PatientNote patientNote = PatientNote.builder().note(patientNoteDto.getNote())
                .patientName(patientNoteDto.getPatientName()).patientId(patientNoteDto.getPatientId()).build();
        return repository.save(patientNote);
    }

    public void deletePatientNote(String patientNoteId) {
        PatientNote patientNote = repository.findById(patientNoteId).orElseThrow(
                () -> new IllegalArgumentException("Patient Note Not Found"));
        repository.delete(patientNote);
    }

    public String getPatientIdByPatientNoteId(String patientNoteId) {
        PatientNote patientNote = repository.findById(patientNoteId).orElseThrow();
        return patientNote.getPatientId();
    }
}
