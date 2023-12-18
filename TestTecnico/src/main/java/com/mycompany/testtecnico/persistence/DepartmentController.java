/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testtecnico.persistence;

import com.mycompany.testtecnico.logic.Department;
import com.mycompany.testtecnico.logic.Teacher;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno Maximiliano Ojeda Bayón
 */
public class DepartmentController {
    
    public void create(Department department){
        
       String name = department.getName();
       
       
        
        DBConection conection = new DBConection();
        
        String consult = ("insert into departments (name) values (?);");
        
        try {
            CallableStatement cs = conection.establishConnection().prepareCall(consult);    
            
            cs.setString(1, name);
            
            cs.execute();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    
    public ArrayList<Department> read() {
    ArrayList<Department> departmentList = new ArrayList<>();

    DBConection connection = new DBConection();

    String consult = "SELECT id, name FROM departments";

    try (Connection conn = connection.establishConnection();
         PreparedStatement ps = conn.prepareStatement(consult);
         ResultSet resultSet = ps.executeQuery()) {

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");

            Department department = new Department(id, name);
            departmentList.add(department);
        }
    } catch (SQLException e) {
        e.printStackTrace(); 
    }

    return departmentList;
}
    
    public void update(Department department){
        
       String name = department.getName();
       int id = department.getId();
       
       
        
        DBConection conection = new DBConection();
        
        String consult = ("update departments set departments.name = ? where departments.id=?;");
        
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

    public void delete(Department department){
        
       int id = department.getId();
       
        DBConection conection = new DBConection();
        
        String consult = ("delete from departments where departments.id=?;");
        
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
