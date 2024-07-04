package com.codegym.ss10.repositories.impl;

import com.codegym.ss10.models.Classroom;
import com.codegym.ss10.repositories.IClassroomRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassroomRepository implements IClassroomRepository {

    @Override
    public List<Classroom> findAll() {
        List<Classroom> classrooms = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM student.classroom")) {

            ResultSet resultSet = preparedStatement.executeQuery();
            Long id;
            String name;
            while (resultSet.next()) {
                id = resultSet.getLong("id_class");
                name = resultSet.getString("class_name");
                classrooms.add(new Classroom(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return classrooms;
    }
}
