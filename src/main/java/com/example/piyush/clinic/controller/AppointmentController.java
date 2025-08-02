package com.example.piyush.clinic.controller;

import com.example.piyush.clinic.model.Appointment;
import com.example.piyush.clinic.service.AppointmentService;
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
@RequestMapping("/appointments")
@Tag(name = "Appointment Management", description = "APIs for managing appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    @Operation(summary = "Book an appointment", description = "Book an appointment between a patient and a doctor for a specific time slot")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Appointment booked successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Doctor or Patient not found"),
            @ApiResponse(responseCode = "409", description = "Slot conflict or double booking")
    })
    public ResponseEntity<Appointment> bookAppointment(@Valid @RequestBody Appointment appointment) {
        Appointment bookedAppointment = appointmentService.bookAppointment(appointment);
        return new ResponseEntity<>(bookedAppointment, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all appointments", description = "Retrieve all appointments")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all appointments")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/doctor/{doctorId}")
    @Operation(summary = "Get appointments by doctor", description = "Retrieve all appointments for a specific doctor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved appointments"),
            @ApiResponse(responseCode = "404", description = "Doctor not found")
    })
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctor(@PathVariable Long doctorId) {
        List<Appointment> appointments = appointmentService.getAppointmentsByDoctorId(doctorId);
        return ResponseEntity.ok(appointments);
    }
}
