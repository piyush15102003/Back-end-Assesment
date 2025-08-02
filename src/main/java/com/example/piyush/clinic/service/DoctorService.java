package com.example.piyush.clinic.service;

import com.example.piyush.clinic.exception.ResourceNotFoundException;
import com.example.piyush.clinic.model.Doctor;
import com.example.piyush.clinic.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
    }

    public List<Doctor> getDoctorsWithAvailableSlotsByDate(LocalDate date) {
        return doctorRepository.findDoctorsWithAvailableSlotsByDate(date);
    }

    public boolean isSlotAvailable(Long doctorId, LocalDateTime slot) {
        return doctorRepository.isSlotAvailable(doctorId, slot);
    }

    public void removeSlot(Long doctorId, LocalDateTime slot) {
        doctorRepository.removeSlot(doctorId, slot);
    }

    public boolean doctorExists(Long doctorId) {
        return doctorRepository.existsById(doctorId);
    }
}
