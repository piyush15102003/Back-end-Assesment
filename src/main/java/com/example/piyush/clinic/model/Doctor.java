package com.example.piyush.clinic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    private Long id;
    
    @NotBlank(message = "Doctor name is required")
    private String name;
    
    @NotBlank(message = "Specialization is required")
    private String specialization;
    
    @NotNull(message = "Available slots are required")
    private List<LocalDateTime> availableSlots;
}
