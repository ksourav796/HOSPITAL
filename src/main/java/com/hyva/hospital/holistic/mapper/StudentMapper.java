package com.hyva.hospital.holistic.mapper;

import com.hyva.hospital.holistic.entities.Student;
import com.hyva.hospital.holistic.pojo.Studentpojo;

public class StudentMapper {
    public static Student mapPojoToEntity(Studentpojo pojo) {
        Student student = new Student();
        student.setStudentId(pojo.getStudentId());
        student.setStudentName(pojo.getStudentName());
        student.setStudentaddress(pojo.getStudentaddress());
        return student;
    }
}
