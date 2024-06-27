package com.openclassrooms.medilaboNote.repository;

import com.openclassrooms.medilaboNote.model.PatientNote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientNoteRepository extends MongoRepository<PatientNote, String> {

    List<PatientNote> findByPatientId(String patientId);

    List<PatientNote> findByPatientName(String patientName);
}
