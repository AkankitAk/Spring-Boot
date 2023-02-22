package com.project.doctor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_doctor")
public class Doctor {
    @Id
    @Column(name = "doctor_id")
    private Integer doctorId;
    @Column(name = "doctor_name")
    private String doctorName;
    @Column(name = "experience")
    private String experience;
    @Column(name = "specialization_in")
    private String specializationIn;
}






















