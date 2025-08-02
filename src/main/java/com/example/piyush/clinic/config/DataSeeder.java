package com.example.piyush.clinic.config;

import com.example.piyush.clinic.model.Doctor;
import com.example.piyush.clinic.model.Patient;
import com.example.piyush.clinic.service.DoctorService;
import com.example.piyush.clinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Override
    public void run(String... args) throws Exception {
        // Seed doctors
        seedDoctors();
        
        // Seed patients
        seedPatients();
    }

    private void seedDoctors() {
        LocalDateTime now = LocalDateTime.now();
        
        Doctor doctor1 = new Doctor();
        doctor1.setName("Dr. John Smith");
        doctor1.setSpecialization("Cardiology");
        doctor1.setAvailableSlots(Arrays.asList(
                now.plusDays(1).withHour(9).withMinute(0).withSecond(0).withNano(0),
                now.plusDays(1).withHour(10).withMinute(0).withSecond(0).withNano(0),
                now.plusDays(1).withHour(11).withMinute(0).withSecond(0).withNano(0),
                now.plusDays(2).withHour(9).withMinute(0).withSecond(0).withNano(0),
                now.plusDays(2).withHour(10).withMinute(0).withSecond(0).withNano(0)
        ));
        doctorService.addDoctor(doctor1);

        Doctor doctor2 = new Doctor();
        doctor2.setName("Dr. Sarah Johnson");
        doctor2.setSpecialization("Pediatrics");
        doctor2.setAvailableSlots(Arrays.asList(
                now.plusDays(1).withHour(14).withMinute(0).withSecond(0).withNano(0),
                now.plusDays(1).withHour(15).withMinute(0).withSecond(0).withNano(0),
                now.plusDays(1).withHour(16).withMinute(0).withSecond(0).withNano(0),
                now.plusDays(3).withHour(9).withMinute(0).withSecond(0).withNano(0),
                now.plusDays(3).withHour(10).withMinute(0).withSecond(0).withNano(0)
        ));
        doctorService.addDoctor(doctor2);

        Doctor doctor3 = new Doctor();
        doctor3.setName("Dr. Michael Brown");
        doctor3.setSpecialization("Orthopedics");
        doctor3.setAvailableSlots(Arrays.asList(
                now.plusDays(2).withHour(14).withMinute(0).withSecond(0).withNano(0),
                now.plusDays(2).withHour(15).withMinute(0).withSecond(0).withNano(0),
                now.plusDays(4).withHour(9).withMinute(0).withSecond(0).withNano(0),
                now.plusDays(4).withHour(10).withMinute(0).withSecond(0).withNano(0),
                now.plusDays(4).withHour(11).withMinute(0).withSecond(0).withNano(0)
        ));
        doctorService.addDoctor(doctor3);
    }

    private void seedPatients() {
        Patient patient1 = new Patient();
        patient1.setName("Alice Williams");
        patient1.setAge(30);
        patient1.setGender("Female");
        patientService.registerPatient(patient1);

        Patient patient2 = new Patient();
        patient2.setName("Bob Davis");
        patient2.setAge(45);
        patient2.setGender("Male");
        patientService.registerPatient(patient2);

        Patient patient3 = new Patient();
        patient3.setName("Carol Miller");
        patient3.setAge(25);
        patient3.setGender("Female");
        patientService.registerPatient(patient3);

        Patient patient4 = new Patient();
        patient4.setName("David Wilson");
        patient4.setAge(35);
        patient4.setGender("Male");
        patientService.registerPatient(patient4);
    }
}
