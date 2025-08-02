package com.example.piyush.clinic.repository;

import com.example.piyush.clinic.model.Appointment;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class AppointmentRepository {
    private final Map<Long, Appointment> appointments = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Appointment save(Appointment appointment) {
        if (appointment.getId() == null) {
            appointment.setId(idGenerator.getAndIncrement());
        }
        appointments.put(appointment.getId(), appointment);
        return appointment;
    }

    public Optional<Appointment> findById(Long id) {
        return Optional.ofNullable(appointments.get(id));
    }

    public List<Appointment> findAll() {
        return new ArrayList<>(appointments.values());
    }

    public List<Appointment> findByDoctorId(Long doctorId) {
        return appointments.values().stream()
                .filter(appointment -> appointment.getDoctorId().equals(doctorId))
                .collect(Collectors.toList());
    }

    public List<Appointment> findByPatientId(Long patientId) {
        return appointments.values().stream()
                .filter(appointment -> appointment.getPatientId().equals(patientId))
                .collect(Collectors.toList());
    }

    public boolean existsByDoctorIdAndSlot(Long doctorId, LocalDateTime slot) {
        return appointments.values().stream()
                .anyMatch(appointment -> 
                    appointment.getDoctorId().equals(doctorId) && 
                    appointment.getSlot().equals(slot));
    }

    public boolean existsByPatientIdAndSlot(Long patientId, LocalDateTime slot) {
        return appointments.values().stream()
                .anyMatch(appointment -> 
                    appointment.getPatientId().equals(patientId) && 
                    appointment.getSlot().equals(slot));
    }

    public void deleteById(Long id) {
        appointments.remove(id);
    }
}
