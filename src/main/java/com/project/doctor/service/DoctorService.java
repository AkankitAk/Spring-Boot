package com.project.doctor.service;

import com.project.doctor.deo.IDoctorRepository;
import com.project.doctor.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {
    @Autowired
    IDoctorRepository doctorRepository;

    public Doctor saveDoctor(Doctor doctor) {
        String doctorName = doctor.getDoctorName();
        doctorName = "Dr." + doctorName;
        doctor.setDoctorName(doctorName);
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getDoctor() {
        return doctorRepository.findAll();
    }

    public List<Doctor> getDoctorById(String doctorId) {
        List<Doctor> doctorList;
        if (null != doctorId) {
            doctorList = new ArrayList<>();
            doctorList.add(doctorRepository.findById(Integer.valueOf(doctorId)).get());
        } else {
            doctorList = doctorRepository.findAll();
        }
        return doctorList;
    }
}
