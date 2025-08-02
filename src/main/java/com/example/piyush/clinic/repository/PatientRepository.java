package com.example.piyush.clinic.repository;

import com.example.piyush.clinic.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PatientRepository {
    private final Map<Long, Patient> patients = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Patient save(Patient patient) {
        if (patient.getId() == null) {
            patient.setId(idGenerator.getAndIncrement());
        }
        patients.put(patient.getId(), patient);
        return patient;
    }

    public Optional<Patient> findById(Long id) {
        return Optional.ofNullable(patients.get(id));
    }

    public List<Patient> findAll() {
        return new ArrayList<>(patients.values());
    }

    public boolean existsById(Long id) {
        return patients.containsKey(id);
    }

    public void deleteById(Long id) {
        patients.remove(id);
    }
}
