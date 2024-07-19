package com.openclassrooms.medilaboNote.controller;

import com.openclassrooms.medilaboNote.dto.PatientNoteDto;
import com.openclassrooms.medilaboNote.model.PatientNote;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api")
public interface PatientNoteControllerSwagger {


    @GetMapping("/note")
    @Operation(summary = "List PatientNote object", description = "This operation List all PatientNote entities")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = PatientNote.class, type = SchemaType.ARRAY))),
            @APIResponse(responseCode = "404", description = "Entity not found."),
            @APIResponse(responseCode = "401", description = "Unauthorized"),
            @APIResponse(responseCode = "403", description = "Forbidden"),
            @APIResponse(responseCode = "500", description = "Internal server error.")
    })
    ResponseEntity<List<PatientNote>> getAllPatient();

    @GetMapping("/note/{id}")
    @Operation(summary = "Retrieve all PatientNote by Patient Id", description = "This operation retrieves all PatientNote entities for a PatientId given")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = PatientNote.class, type = SchemaType.ARRAY))),
            @APIResponse(responseCode = "404", description = "Entity not found."),
            @APIResponse(responseCode = "401", description = "Unauthorized"),
            @APIResponse(responseCode = "403", description = "Forbidden"),
            @APIResponse(responseCode = "500", description = "Internal server error.")
    })
    ResponseEntity<List<PatientNote>> getAllPatientByPatientId(@Parameter(description = "Identifier of the Patient we want to find his Notes") @PathVariable String id);

    @PostMapping("/note")
    @Operation(summary = "Creates a PatientNote", description = "This operation creates a PatientNote entity")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Created", content = @Content(schema = @Schema(implementation = PatientNote.class))),
            @APIResponse(responseCode = "400", description = "Bad Request."),
            @APIResponse(responseCode = "401", description = "Unauthorized"),
            @APIResponse(responseCode = "403", description = "Forbidden"),
            @APIResponse(responseCode = "500", description = "Internal server error.")
    })
    ResponseEntity<PatientNote> createPatientNote(@RequestBody(content = @Content(schema = @Schema(implementation = PatientNoteDto.class)),required = true, description = "The PatientNote to be created") PatientNoteDto patientNote);
}
