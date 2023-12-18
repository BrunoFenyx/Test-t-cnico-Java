/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testtecnico.persistence;

import static com.mycompany.testtecnico.persistence.DBConection.establishConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Bruno Maximiliano Ojeda Bayon
 */
public class PersistenceIdObjects {
    
    Connection conection = establishConnection();
    
    private int idSection;
        
    private int idDepartment;
    
    private int idCourse;
    
    private int idPerson;
    
    private int idStaff;
    
    private int idTeacher;
    
    private int idServiceStaff;
    
    private int idCourseAndStudent;

    public PersistenceIdObjects(int idSection, int idDepartment, int idCourse, 
                                int idPerson, int idStaff,
                                int idTeacher, int idServiceStaff,
                                int idCourseAndStudent) {
        this.idSection = idSection;
        this.idDepartment = idDepartment;
        this.idTeacher = idTeacher;
        this.idServiceStaff = idServiceStaff;
        this.idCourse = idCourse;
        this.idCourseAndStudent = idCourseAndStudent;
        this.idPerson = idPerson;
        this.idStaff = idStaff;
        
        updateIds();
       System.out.println("IDs Generados: "+this);
    }

    public PersistenceIdObjects() {
    }
    
    public void updateIds() {
    try (Connection connection = establishConnection()) {

        // Obtener el último ID de cada tabla
        this.idPerson = (getLastId("person", connection));
        this.idStaff = (getLastId("staff", connection));
        this.idTeacher = (getLastId("teachers", connection));
        this.idServiceStaff = (getLastId("servicestaffs", connection));
        this.idCourse = (getLastId("courses", connection));
        this.idDepartment = (getLastId("departments", connection));
        this.idSection = (getLastId("sections", connection));
        this.idCourseAndStudent = (getLastId("coursesandstudents", connection));

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
    public int getIdSection() {
        return idSection;
    }

    public void addIdSection() {
        this.idSection++;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void addIdDepartment() {
        this.idDepartment++;
    }
    
    public int getIdCourse() {
        return idCourse;
    }

    public void addIdCourse() {
        this.idCourse++;
    }
    
    public int getIdPerson() {
        return idPerson;
    }

    public void addIdPerson() {
        this.idPerson++;
    }
    
    public int getIdStaff() {
        return idStaff;
    }

    public void addIdStaff() {
        this.idStaff++;
    }
    

    public int getIdTeacher() {
        return idTeacher;
    }

    public void addIdTeacher() {
        this.idTeacher++;
    }

    public int getIdServicestaff() {
        return idServiceStaff;
    }

    public void addIdServicestaff() {
        this.idServiceStaff++;
    }
    
    public int getIdCourseAndStudent() {
        return idCourseAndStudent;
    }

    public void addIdCourseAndStudent() {
        this.idCourseAndStudent++;
    }
    
     private int getLastId(String tableName, Connection connection) throws SQLException {
        String query = "SELECT MAX(id) FROM " + tableName;
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt(1) + 1;
            } else {
                // Si la tabla está vacía, empezar desde 1
                return 1;
            }
        }
    }
     
     @Override
public String toString() {
    return "PersistenceIdObjects{" +
            "idSection=" + idSection +
            ", idDepartment=" + idDepartment +
            ", idTeacher=" + idTeacher +
            ", idServiceStaff=" + idServiceStaff +
            ", idCourse=" + idCourse +
            ", idCourseAndStudent=" + idCourseAndStudent +
            ", idPerson=" + idPerson +
            ", idStaff=" + idStaff +
            '}';
}
    
}
