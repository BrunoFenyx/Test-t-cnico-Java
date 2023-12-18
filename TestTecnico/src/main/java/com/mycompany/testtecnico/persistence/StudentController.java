/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testtecnico.persistence;

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
public class StudentController {
    
    public void create(Student student){
        
       
       String name = student.getName();
       String fullname = student.getFullname();
       String CivilStatus = student.getCivilStatus();
       
        
        DBConection conection = new DBConection();
        
        String consult = ("insert into person (name, fullname, civilStatus, type) values (?,?,?,?);");
        
        try {
            CallableStatement cs = conection.establishConnection().prepareCall(consult);    
            
            cs.setString(1, name);
            cs.setString(2, fullname);
            cs.setString(3, CivilStatus);
            cs.setInt(4, 1);
            
            cs.execute();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    
    public ArrayList<Student> read() {
    ArrayList<Student> studentList = new ArrayList<>();

    DBConection connection = new DBConection();

    String consult = "SELECT id, name, fullname, civilStatus FROM Person";

    try (Connection conn = connection.establishConnection();
         PreparedStatement ps = conn.prepareStatement(consult);
         ResultSet resultSet = ps.executeQuery()) {

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String fullname = resultSet.getString("fullname");
            String civilStatus = resultSet.getString("civilStatus");

            Student student = new Student( name, fullname, civilStatus);
            student.setId(id);
            studentList.add(student);
        }
    } catch (SQLException e) {
        e.printStackTrace(); 
    }

    return studentList;
}

    public void update(Student student){
        
       String civilStatus = student.getCivilStatus();
       int id = student.getId();
       
       
        
        DBConection conection = new DBConection();
        
        String consult = ("update person set person.civilStatus = ? where person.id=?;");
        
        try {
            CallableStatement cs = conection.establishConnection().prepareCall(consult);

            cs.setString(1, civilStatus);
            cs.setInt(2, id);

            cs.execute();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void delete(Student student){
        
       int id = student.getId();
       
        DBConection conection = new DBConection();
        
        String consult = ("delete from person where person.id=?;");
        
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
