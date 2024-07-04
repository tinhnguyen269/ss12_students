package com.codegym.ss10.services.impl;

import com.codegym.ss10.models.Classroom;
import com.codegym.ss10.repositories.IClassroomRepository;
import com.codegym.ss10.repositories.impl.ClassroomRepository;
import com.codegym.ss10.services.IClassroomService;

import java.util.List;

public class ClassroomService implements IClassroomService {
    IClassroomRepository classroomRepository = new ClassroomRepository();
    @Override
    public List<Classroom> findAll() {
        return classroomRepository.findAll();
    }
}
