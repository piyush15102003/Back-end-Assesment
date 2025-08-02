package com.example.piyush.clinic.repository;

import com.example.piyush.clinic.model.Doctor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class DoctorRepository {
    private final Map<Long, Doctor> doctors = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Doctor save(Doctor doctor) {
        if (doctor.getId() == null) {
            doctor.setId(idGenerator.getAndIncrement());
        }
        doctors.put(doctor.getId(), doctor);
        return doctor;
    }

    public Optional<Doctor> findById(Long id) {
        return Optional.ofNullable(doctors.get(id));
    }

    public List<Doctor> findAll() {
        return new ArrayList<>(doctors.values());
    }

    public boolean existsById(Long id) {
        return doctors.containsKey(id);
    }

    public void deleteById(Long id) {
        doctors.remove(id);
    }

    public List<Doctor> findDoctorsWithAvailableSlotsByDate(LocalDate date) {
        return doctors.values().stream()
                .filter(doctor -> doctor.getAvailableSlots().stream()
                        .anyMatch(slot -> slot.toLocalDate().equals(date)))
                .collect(Collectors.toList());
    }

    public synchronized boolean isSlotAvailable(Long doctorId, LocalDateTime slot) {
        Doctor doctor = doctors.get(doctorId);
        return doctor != null && doctor.getAvailableSlots().contains(slot);
    }

    public synchronized void removeSlot(Long doctorId, LocalDateTime slot) {
        Doctor doctor = doctors.get(doctorId);
        if (doctor != null) {
            doctor.getAvailableSlots().remove(slot);
        }
    }
}
