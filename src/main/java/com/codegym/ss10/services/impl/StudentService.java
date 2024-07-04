package com.codegym.ss10.services.impl;

import com.codegym.ss10.DTO.StudentDTO;
import com.codegym.ss10.models.Student;
import com.codegym.ss10.repositories.IStudentRepository;
import com.codegym.ss10.repositories.impl.StudentRepository;
import com.codegym.ss10.services.IStudentService;

import java.util.List;

public class StudentService implements IStudentService {

    private static IStudentRepository studentRepository = new StudentRepository();

    @Override
    public List<StudentDTO> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Boolean deleteById(Long id) {
        return studentRepository.deleteById(id);
    }

    @Override
    public List<StudentDTO> findByName(String nameStudent) {
        return studentRepository.findByName(nameStudent);
    }

//    @Override
//    public boolean editById(Long id, String name, String address) {
//        return studentRepository.editById(id,name,address);
//    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public void update(Long id, Student student) {
        studentRepository.update(id,student);
    }
}

