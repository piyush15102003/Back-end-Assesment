package com.example.piyush.clinic.controller;

import com.example.piyush.clinic.model.Patient;
import com.example.piyush.clinic.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patient Management", description = "APIs for managing patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    @Operation(summary = "Register a new patient", description = "Register a new patient with name, age, and gender")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Patient registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<Patient> registerPatient(@Valid @RequestBody Patient patient) {
        Patient savedPatient = patientService.registerPatient(patient);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all patients", description = "Retrieve a list of all registered patients")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all patients")
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }
}
