package com.project.doctor.deo;

import com.project.doctor.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPatientRepository extends JpaRepository<Patient,Integer> {
}
