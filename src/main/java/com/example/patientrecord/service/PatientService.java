package com.example.patientrecord.service;

import com.example.patientrecord.exception.UserNotFoundException;
import com.example.patientrecord.model.Patient;
import com.example.patientrecord.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    private final PatientRepo patientRepo;

    @Autowired
    public PatientService(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    public Patient addPatient(Patient patient){
        String shortId = UUID.randomUUID().toString().replace("-","").substring(0,13).toUpperCase();
        patient.setPatientNo(shortId);
        return patientRepo.save(patient);
    }

    public List<Patient> findAllPatients(){
        return patientRepo.findAll();
    }

    public Patient updatePatient(Patient patient){
        return patientRepo.save(patient);
    }

    public Patient findPatientById(Long id){
        return patientRepo.findPatientById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public void deletePatient(Long id){
        patientRepo.deletePatientById(id);
    }

}
