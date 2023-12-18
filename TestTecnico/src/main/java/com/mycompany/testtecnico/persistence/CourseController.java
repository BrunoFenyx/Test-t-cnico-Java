package com.mycompany.testtecnico.persistence;

import com.mycompany.testtecnico.logic.Course;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Bruno Maximiliano Ojeda Bayon
 */
public class CourseController {
    
    public void create(Course course){
        
       String name = course.getName();
       
       
        
        DBConection conection = new DBConection();
        
        String consult = ("insert into courses (name) values (?);");
        
        try {
            CallableStatement cs = conection.establishConnection().prepareCall(consult);    
            
            cs.setString(1, name);
            
            cs.execute();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    
    public List<Course> read() {
        List<Course> courseList = new ArrayList<>();
        
        DBConection connection = new DBConection();
        
        String consult = "select id, name from courses";
        
        try (CallableStatement cs = connection.establishConnection().prepareCall(consult);
             ResultSet resultSet = cs.executeQuery()) {
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                
                Course course = new Course(id, name);
                
                courseList.add(course);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        System.out.println(courseList);
        return courseList;
    }
    
    public void update(Course course){
        
       String name = course.getName();
       int id = course.getId();
       
       
        
        DBConection conection = new DBConection();
        
        String consult = ("update courses set courses.name = ? where courses.id=?;");
        
        try {
            CallableStatement cs = conection.establishConnection().prepareCall(consult);    
            
            cs.setString(1, name);
            cs.setInt(2, id);
            
            cs.execute();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    
    public void delete(Course course){
        
       int id = course.getId();
       
       
        
        DBConection conection = new DBConection();
        
        String consult = ("delete from courses where courses.id=?;");
        
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



