package com.project.doctor.deo;

import com.project.doctor.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IDoctorRepository extends JpaRepository<Doctor,Integer> {

}
