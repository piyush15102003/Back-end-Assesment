package com.example.piyush.clinic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    private Long id;
    
    @NotBlank(message = "Patient name is required")
    private String name;
    
    @NotNull(message = "Age is required")
    @Min(value = 0, message = "Age must be non-negative")
    private Integer age;
    
    @NotBlank(message = "Gender is required")
    private String gender;
}
