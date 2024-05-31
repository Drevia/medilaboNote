package com.openclassrooms.medilaboNote.service;

import com.openclassrooms.medilaboNote.dto.PatientNoteDto;
import com.openclassrooms.medilaboNote.model.PatientNote;
import com.openclassrooms.medilaboNote.repository.PatientNoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PatientNoteServiceTest {

    @Mock
    PatientNoteRepository patientNoteRepository;

    @InjectMocks
    PatientNoteService patientNoteService;

    @Test
    void getAllPatientNoteOk() {
        PatientNote patientNote = PatientNote.builder().patientName("toto").build();
        PatientNote patientNote2 = PatientNote.builder().patientName("titi").build();
        List<PatientNote> patientNoteList = List.of(patientNote, patientNote2);
        when(patientNoteRepository.findAll()).thenReturn(patientNoteList);

        List<PatientNote> result = assertDoesNotThrow(() -> patientNoteService.getAllPatient());

        assertEquals(2, result.size());

    }

    @Test
    void getAllPatientNoteByPatientIdOk() {
        PatientNote patientNote = PatientNote.builder().patientId("1").patientName("toto").build();
        PatientNote patientNote2 = PatientNote.builder().patientId("2").patientName("toto").build();

        List<PatientNote> patientNoteList = List.of(patientNote, patientNote2);
        when(patientNoteRepository.findByPatientId(anyString())).thenReturn(patientNoteList);

        List<PatientNote> result = assertDoesNotThrow(() -> patientNoteService.getAllPatientByPatientId("1"));

        assertEquals(2, result.size());
    }

    @Test
    void createPatientNoteOk() {
        PatientNote patientNote = PatientNote.builder().patientId("1").patientName("toto").note("it's a note").build();

        when(patientNoteRepository.save(any())).thenReturn(patientNote);

        PatientNote result = assertDoesNotThrow(() -> patientNoteService.createPatientNote(new PatientNoteDto()));

        assertEquals(patientNote.getPatientId(), result.getPatientId());
        assertEquals(patientNote.getPatientName(), result.getPatientName());
        assertEquals(patientNote.getNote(), result.getNote());
    }
}
