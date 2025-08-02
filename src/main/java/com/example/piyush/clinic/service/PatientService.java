package com.example.piyush.clinic.service;

import com.example.piyush.clinic.exception.ResourceNotFoundException;
import com.example.piyush.clinic.model.Patient;
import com.example.piyush.clinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient registerPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
    }

    public boolean patientExists(Long patientId) {
        return patientRepository.existsById(patientId);
    }
}
