package com.project.doctor.controller;

import com.project.doctor.model.Doctor;
import com.project.doctor.service.DoctorService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class DoctorController {
    @Autowired
    DoctorService doctorService;
    @PostMapping(value = "/doctor")
    public ResponseEntity<String> saveDoctor(@RequestBody Doctor requestDoctor){
        JSONObject json=new JSONObject(requestDoctor);

        List<String> validationList=validDoctor(json);


        if (validationList.isEmpty()) {
            Doctor doctor=setDoctor(json);
            doctorService.saveDoctor(doctor);
            return new ResponseEntity<>("Doctor Save ", HttpStatus.CREATED);
        }
        else {
            // ArrayList to direct in String Array
            String[] errorName= Arrays.copyOf(validationList.toArray(),validationList.size(),String[].class);
            return new ResponseEntity<>("Please pass these mandatory parameters"+ Arrays.toString(errorName),HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/doctor")
    public List<Doctor> getDoctor(){
        return doctorService.getDoctor();
    }

    @GetMapping(value = "/doctorById")
    public List<Doctor> getDoctorById(@Nullable @RequestParam String doctor){
        return doctorService.getDoctorById((doctor));
    }




    private List<String> validDoctor(JSONObject json) {

        List<String> errorList=new ArrayList<>();


        if (!json.has("doctorId")){
            errorList.add("doctorId");
        }
        if (!json.has("doctorName")){
            errorList.add("doctorName");
        }
        if (!json.has("specializationIn")){
            errorList.add("specializationIn");
        }

        return errorList;
    }
    private Doctor setDoctor(JSONObject json) {
        Doctor doctor=new Doctor();

        int doctorId= (int) json.get("doctorId");
        doctor.setDoctorId(doctorId);

        String doctorName= json.getString("doctorName");
        doctor.setDoctorName(doctorName);

        String specializationIn=json.getString("specializationIn");
        doctor.setSpecializationIn(specializationIn);

        if(json.has("experience")){
            String exp=json.getString("experience");
            String experience=json.getString("experience");
            doctor.setExperience(experience);
        }
        return doctor;
    }

}













