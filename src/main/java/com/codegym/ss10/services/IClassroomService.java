package com.codegym.ss10.services;

import com.codegym.ss10.models.Classroom;

import java.util.List;

public interface IClassroomService {
    List<Classroom> findAll();
}
