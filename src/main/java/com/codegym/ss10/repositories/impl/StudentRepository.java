package com.codegym.ss10.repositories.impl;

import com.codegym.ss10.DTO.StudentDTO;
import com.codegym.ss10.models.Student;
import com.codegym.ss10.repositories.IStudentRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements IStudentRepository {

    @Override
    public List<StudentDTO> findAll() {
        List<StudentDTO> students = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().
                    prepareStatement("select id,studentlist.name,address,classroom.class_name,point from studentlist join classroom on studentlist.id_class = classroom.id_class");
            ResultSet resultSet = preparedStatement.executeQuery();
            Long id;
            String name;
            String address;
            Float point;
            String nameClass;
            while (resultSet.next()) {
                id = resultSet.getLong("id");
                name = resultSet.getString("name");
                address = resultSet.getString("address");
                point = resultSet.getFloat("point");
                nameClass = resultSet.getString("class_name");
                students.add(new StudentDTO(id, name, address, nameClass, point));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

        @Override
        public void save(Student student) {
            try {
                PreparedStatement preparedStatement = BaseRepository.getConnection().
                        prepareStatement("insert into studentlist(name, address, point,id_Class) value (?, ?, ?,?)");
                preparedStatement.setString(1, student.getName());
                preparedStatement.setString(2, student.getAddress());
                preparedStatement.setFloat(3, student.getPoint());
                preparedStatement.setLong(4,student.getIdClass());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    @Override
    public Boolean deleteById(Long id) {
        boolean isDelete;
        try {
            PreparedStatement statement = BaseRepository.getConnection().prepareStatement("delete from studentlist where id=?;");
            statement.setLong(1, id);
            isDelete = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isDelete;
    }

    public List<StudentDTO> findByName(String nameStudent) {
        List<StudentDTO> students = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().
                    prepareStatement("SELECT id, name, address, point, classroom.class_name FROM studentlist join classroom on studentlist.id_class = classroom.id_class  WHERE name LIKE ?");
            preparedStatement.setString(1, "%" + nameStudent + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                Float point = resultSet.getFloat("point");
                String className = resultSet.getString("class_name");

                students.add(new StudentDTO(id, name, address, className, point));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

//    @Override
//    public boolean editById(Long id, String name, String address) {
//        List<Student> students = new ArrayList<>();
//        Student student = null;
//        try {
//            PreparedStatement preparedStatement = BaseRepository.getConnection()
//                    .prepareStatement("SELECT * FROM student WHERE id=?");
//            preparedStatement.setLong(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                name = resultSet.getString("name");
//                address = resultSet.getString("address");
//                Float point = resultSet.getFloat("point");
//                student = new Student(id, name, address, point);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return student;
//    }

    @Override
    public Student findById(Long id) {
        Student student = null;
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection()
                    .prepareStatement("select * from studentlist WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                Float point = resultSet.getFloat("point");
                Long idClass = Long.valueOf(resultSet.getString("id_class"));
                student = new Student(id, name, address, point,idClass);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }

    @Override
    public void update(Long id, Student student) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection()
                    .prepareStatement("UPDATE studentlist SET name=?, address=?, point=?, id_class=? WHERE id=?");
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getAddress());
            preparedStatement.setFloat(3, student.getPoint());
            preparedStatement.setLong(4,student.getIdClass());
            preparedStatement.setLong(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}




