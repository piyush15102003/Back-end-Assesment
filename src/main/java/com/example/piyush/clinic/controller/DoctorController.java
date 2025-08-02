package com.example.piyush.clinic.controller;

import com.example.piyush.clinic.model.Doctor;
import com.example.piyush.clinic.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/doctors")
@Tag(name = "Doctor Management", description = "APIs for managing doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping
    @Operation(summary = "Add a new doctor", description = "Add a new doctor with name, specialization, and available slots")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Doctor created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<Doctor> addDoctor(@Valid @RequestBody Doctor doctor) {
        Doctor savedDoctor = doctorService.addDoctor(doctor);
        return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all doctors", description = "Retrieve a list of all registered doctors")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all doctors")
    public ResponseEntity<List<Doctor>> getAllDoctors(
            @Parameter(description = "Filter by available slots on specific date (YYYY-MM-DD)")
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        
        List<Doctor> doctors;
        if (date != null) {
            doctors = doctorService.getDoctorsWithAvailableSlotsByDate(date);
        } else {
            doctors = doctorService.getAllDoctors();
        }
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get doctor by ID", description = "Retrieve doctor details by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doctor found"),
            @ApiResponse(responseCode = "404", description = "Doctor not found")
    })
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorService.getDoctorById(id);
        return ResponseEntity.ok(doctor);
    }
}
