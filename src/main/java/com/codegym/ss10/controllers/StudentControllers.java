package com.codegym.ss10.controllers;

import com.codegym.ss10.DTO.StudentDTO;
import com.codegym.ss10.models.Classroom;
import com.codegym.ss10.models.Student;
import com.codegym.ss10.services.IClassroomService;
import com.codegym.ss10.services.IStudentService;
import com.codegym.ss10.services.impl.ClassroomService;
import com.codegym.ss10.services.impl.StudentService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentController", value = "/student")
public class StudentControllers extends HttpServlet {

    private static IStudentService studentService = new StudentService();
    private static IClassroomService classroomService = new ClassroomService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                List<Classroom> classrooms = classroomService.findAll();
                req.setAttribute("classrooms",classrooms);
                req.getRequestDispatcher("/student/create.jsp").forward(req, resp);
                break;
            case "edit":
                long id = Long.parseLong(req.getParameter("id"));
                Student student = studentService.findById(id);
                classrooms = classroomService.findAll();
                req.setAttribute("student", student);
                req.setAttribute("classrooms", classrooms);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/student/edit.jsp");
                dispatcher.forward(req, resp);
                break;

            default:
                List<StudentDTO> students = studentService.findAll();
                req.setAttribute("students", students);
                req.getRequestDispatcher("/student/list.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                String name = req.getParameter("name");
                String address = req.getParameter("address");
                Float points = Float.parseFloat(req.getParameter("point"));
                Long idClass = Long.valueOf(req.getParameter("classroom"));
                Student student = new Student(name, address, points,idClass);
                studentService.save(student);
                resp.sendRedirect("/student");
                break;
            case "delete":
                Long id = Long.parseLong(req.getParameter("id"));
                Boolean isDelete = studentService.deleteById(id);
                if (isDelete) {
                    resp.sendRedirect("/student");
                } else {
                    req.setAttribute("message", "Xóa không thành công");
                    List<StudentDTO> students = studentService.findAll();
                    req.setAttribute("students", students);
                    req.getRequestDispatcher("/student/list.jsp").forward(req, resp);
                }
                break;
            case "search":
                String nameStudent = req.getParameter("searchByName");
                List<StudentDTO> studentByName = studentService.findByName(nameStudent);
                if (studentByName == null || studentByName.isEmpty()) {
                    req.setAttribute("message", "Không tìm thấy");
                    List<StudentDTO> students = studentService.findAll();
                    req.setAttribute("students", students);
                    req.getRequestDispatcher("/student/list.jsp").forward(req, resp);
                } else {
                    req.setAttribute("studentByName", studentByName);
                    req.getRequestDispatcher("/student/infor.jsp").forward(req, resp);
                }
                break;
            case "edit":
                id = Long.parseLong(req.getParameter("id"));
                name = req.getParameter("name");
                address = req.getParameter("address");
                Float point = Float.valueOf(req.getParameter("point"));
                student = studentService.findById(id);
                if (student != null) {
                    student.setName(name);
                    student.setAddress(address);
                    student.setPoint(point);
                    studentService.update(id, student);
                    req.setAttribute("student", student);
                    req.setAttribute("message", "Cập nhật thành công");
                    req.getRequestDispatcher("/student/edit.jsp").forward(req, resp);
                } else {
                    req.setAttribute("message", "Sinh viên không tồn tại");
                    req.getRequestDispatcher("/student/edit.jsp").forward(req, resp);
                }
                break;
        }
    }
}
