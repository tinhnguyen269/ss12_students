package com.codegym.ss10.services;

import com.codegym.ss10.DTO.StudentDTO;
import com.codegym.ss10.models.Student;

import java.util.List;

public interface IStudentService {
    List<StudentDTO> findAll();

    void save(Student student);

    Boolean deleteById(Long id);

    List<StudentDTO> findByName(String nameStudent);

//    boolean editById(Long id, String name, String address);

    Student findById(Long id);

    void update(Long id, Student student);
}
