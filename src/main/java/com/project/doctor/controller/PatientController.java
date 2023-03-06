package com.project.doctor.controller;

import com.project.doctor.model.Patient;
import com.project.doctor.service.PatientService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {

    @Autowired
    PatientService patientService;
    @PostMapping("/patient")
    public String savePatient(@RequestBody String patient){
        JSONObject jsonObject=new JSONObject(patient);
        Patient patient1= patientService.setPatient(jsonObject);
        patientService.savePatient(patient1);
        return "saved";
    }
    @GetMapping("/patient")
    public List<Patient> getPatients(@Nullable @RequestParam Integer  doctorId,
                                     @Nullable @RequestParam Integer patientId){
        return patientService.getPatient(doctorId,patientId);
    }
}
