package com.openclassrooms.medilaboNote.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.medilaboNote.dto.PatientNoteDto;
import com.openclassrooms.medilaboNote.model.PatientNote;
import com.openclassrooms.medilaboNote.repository.PatientNoteRepository;
import com.openclassrooms.medilaboNote.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PatientNoteControllerTest {

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
        repository.deleteAll();
    }

    @Test
    void getAllPatientNotesOk() throws Exception {

        PatientNote patientNote = PatientNote.builder().note("note").patientId("1").patientName("toto").build();
        PatientNote patientNote2 = PatientNote.builder().note("note2").patientId("2").patientName("toto2").build();
        repository.save(patientNote);
        repository.save(patientNote2);

        mockMvc.perform(get("/api/note")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .header("Authorization", "Bearer jwt-test-token")).andDo(print())
                .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].patientId").value(1))
                .andExpect(jsonPath("$[0].note").value("note"))
                .andExpect(jsonPath("$[0].patientName").value("toto"));
    }

    @Test
    void getAllPatientNotesByPatientIdOk() throws Exception {

        PatientNote patientNote = PatientNote.builder().note("note").patientId("1").patientName("toto").build();
        repository.save(patientNote);

        mockMvc.perform(get("/api/note/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .header("Authorization", "Bearer jwt-test-token")).andDo(print())
                .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].patientId").value(1))
                .andExpect(jsonPath("$[0].note").value("note"))
                .andExpect(jsonPath("$[0].patientName").value("toto"));
    }

    @Test
    void CreatePatientNotesOk() throws Exception {

        PatientNoteDto patientNoteDto = new PatientNoteDto();
        patientNoteDto.setNote("note");
        patientNoteDto.setPatientId("id");
        patientNoteDto.setPatientName("name");

        String json = mapper.writeValueAsString(patientNoteDto);

        mockMvc.perform(post("/api/note")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(csrf())
                        .header("Authorization", "Bearer jwt-test-token")).andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}


