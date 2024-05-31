package com.openclassrooms.medilaboNote.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.medilaboNote.model.PatientNote;
import com.openclassrooms.medilaboNote.repository.PatientNoteRepository;
import com.openclassrooms.medilaboNote.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@AutoConfigureMockMvc
@DataMongoTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PatientNoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private PatientNoteRepository repository;

    @MockBean
    private JwtService jwtService;

    @BeforeEach
    void initMock() {
        when(jwtService.extractUsername(any())).thenReturn("user");
    }

    @Test
    void getAllPatientNotesOk() throws Exception {

        PatientNote patientNote = PatientNote.builder().note("note").patientId("1").patientName("toto").build();
        repository.save(patientNote);

        mockMvc.perform(get("/api/note")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .header("Authorization", "Bearer jwt-test-token")).andDo(print())
                .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$.patientId").value(1))
                .andExpect(jsonPath("$.note").value("note"))
                .andExpect(jsonPath("$.patientName").value("toto"));
    }
}
