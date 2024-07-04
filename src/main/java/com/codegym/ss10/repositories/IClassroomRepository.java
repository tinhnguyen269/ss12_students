package com.codegym.ss10.repositories;

import com.codegym.ss10.models.Classroom;

import java.util.List;

public interface IClassroomRepository {
    List<Classroom> findAll();
}
