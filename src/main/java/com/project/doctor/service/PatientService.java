package com.project.doctor.service;

import com.project.doctor.deo.IDoctorRepository;
import com.project.doctor.deo.IPatientRepository;
import com.project.doctor.model.Doctor;
import com.project.doctor.model.Patient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    IPatientRepository repository;
    @Autowired
    IDoctorRepository doctorRepository;

    public void savePatient(Patient patient){
        repository.save(patient);
    }
    public Patient setPatient(JSONObject jsonObject){
        Patient patient=new Patient();
        patient.setPatientId(jsonObject.getInt("patientId"));
        patient.setPatientName((jsonObject.getString("patientName")));
        patient.setDiseaseType(jsonObject.getString("diseaseType"));
        patient.setPhoneNumber(jsonObject.getString("phoneNumber"));
        Doctor doctor= doctorRepository.findById(jsonObject.getInt("doctorId")).get();
        patient.setDoctorId(doctor);
        return patient;
    }
    public List<Patient> getPatient(Integer doctorId, Integer patientId){
        List<Patient> patients=new ArrayList<>();

        if(doctorId==null && patientId==null){
            return repository.findAll();
        }
        else if(doctorId==null){
            patients.add(repository.findById(patientId).get());
        }
        else if(patientId==null){
            Doctor doctor=doctorRepository.findById(doctorId).get();
        }

        return patients;
    }
}
