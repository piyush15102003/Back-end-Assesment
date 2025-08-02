package com.example.piyush.clinic.service;

import com.example.piyush.clinic.exception.*;
import com.example.piyush.clinic.model.Appointment;
import com.example.piyush.clinic.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    public Appointment bookAppointment(Appointment appointment) {
        if (!doctorService.doctorExists(appointment.getDoctorId())) {
            throw new ResourceNotFoundException("Doctor not found");
        }

        if (!patientService.patientExists(appointment.getPatientId())) {
            throw new ResourceNotFoundException("Patient not found");
        }

        if (!doctorService.isSlotAvailable(appointment.getDoctorId(), appointment.getSlot())) {
            throw new SlotUnavailableException("Slot is unavailable");
        }

        if (appointmentRepository.existsByDoctorIdAndSlot(appointment.getDoctorId(), appointment.getSlot())) {
            throw new AppointmentConflictException("Doctor already has an appointment at this slot");
        }

        if (appointmentRepository.existsByPatientIdAndSlot(appointment.getPatientId(), appointment.getSlot())) {
            throw new AppointmentConflictException("Patient already has an appointment at this slot");
        }

        doctorService.removeSlot(appointment.getDoctorId(), appointment.getSlot());

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAppointmentsByDoctorId(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }
}
