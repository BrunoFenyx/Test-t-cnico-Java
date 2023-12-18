/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testtecnico.persistence;

import com.mycompany.testtecnico.logic.Course;
import com.mycompany.testtecnico.logic.CourseAndStudent;
import com.mycompany.testtecnico.logic.Student;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Bruno Maximiliano Ojeda Bayón
 */
public class CoursesAndStudentsController {
    
    public PersistenceController persisController = new PersistenceController();
    
    public void create(CourseAndStudent courseAndStudent) {
        int id_course = courseAndStudent.getCourse().getId();
        int id_student = courseAndStudent.getStudent().getId();
        
        System.out.println("ID del curso: "+id_course);
        System.out.println("ID del estudiante: "+id_student);

        DBConection connection = new DBConection();

        String consult = "INSERT INTO CoursesAndStudents (student_id, course_id) VALUES (?, ?);";

        try {
            CallableStatement cs = connection.establishConnection().prepareCall(consult);

            cs.setInt(1, id_student);
            cs.setInt(2, id_course);

            cs.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
}
    
    public ArrayList<CourseAndStudent> read() {
    ArrayList<CourseAndStudent> courseAndStudentList = new ArrayList<>();

    DBConection connection = new DBConection();

    String consult = "SELECT cs.id, p.name AS student_name, c.name AS course_name " +
                    "FROM CoursesAndStudents cs " +
                    "INNER JOIN Person p ON cs.student_id = p.id " +
                    "INNER JOIN Courses c ON cs.course_id = c.id";

    try (Connection conn = connection.establishConnection();
         PreparedStatement ps = conn.prepareStatement(consult);
         ResultSet resultSet = ps.executeQuery()) {

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String student_name = resultSet.getString("student_name");
            String course_name = resultSet.getString("course_name");
            
            Student student = persisController.findStudent(student_name);
            Course course = persisController.findCourse(course_name);

            CourseAndStudent courseAndStudent = new CourseAndStudent(id, student, course);
            courseAndStudentList.add(courseAndStudent);
        }
    } catch (SQLException e) {
        e.printStackTrace(); 
    }

    return courseAndStudentList;
}
    
    public void delete(CourseAndStudent courseAndStudent){
        
       int id = courseAndStudent.getId();
       
       
        
        DBConection conection = new DBConection();
        
        String consult = ("delete from coursesAndStudents where coursesAndStudents.id=?;");
        
        try {
            CallableStatement cs = conection.establishConnection().prepareCall(consult);    
            
            cs.setInt(1, id);
            
            cs.execute();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    

    
}
