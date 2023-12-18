/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testtecnico.persistence;


import com.mycompany.testtecnico.logic.Section;
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
public class SectionController {
    
    public void create(Section section){
        
       String name = section.getName();
       
       
        
        DBConection conection = new DBConection();
        
        String consult = ("insert into sections (name) values (?);");
        
        try {
            CallableStatement cs = conection.establishConnection().prepareCall(consult);    
            
            cs.setString(1, name);
            
            cs.execute();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    
    public ArrayList<Section> read() {
    ArrayList<Section> sectionList = new ArrayList<>();

    DBConection connection = new DBConection();

    String consult = "SELECT id, name FROM sections";

    try (Connection conn = connection.establishConnection();
         PreparedStatement ps = conn.prepareStatement(consult);
         ResultSet resultSet = ps.executeQuery()) {

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");

            Section department = new Section(id, name);
            sectionList.add(department);
        }
    } catch (SQLException e) {
        e.printStackTrace(); 
    }
        return sectionList;
}
    
    public void update(Section section){
        
       String name = section.getName();
       int id = section.getId();
       
       
        
        DBConection conection = new DBConection();
        
        String consult = ("update sections set sections.name = ? where sections.id=?;");
        
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
    
    public void delete(Section section){
        
       int id = section.getId();
       
       
        
        DBConection conection = new DBConection();
        
        String consult = ("delete from sections where sections.id=?;");
        
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
