/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testtecnico.igu.Provisional;

/**
 *
 * @author Bruno Maximiliano Ojeda Bayon
 */
public class PersistenceIdObjects {
    
    private int idSection;
        
    private int idDepartment;
    
    private int idStudent;
    
    private int idTeacher;
    
    private int idServiceStaff;
    
    private int idCourse;
    
    private int idCourseAndStudent;

    public PersistenceIdObjects(int idSection, int idDepartment, int idStudent, int idTeacher, int idServiceStaff,
                                int idCourse, int idCourseAndStudent) {
        this.idSection = idSection;
        this.idDepartment = idDepartment;
        this.idStudent = idStudent;
        this.idTeacher = idTeacher;
        this.idServiceStaff = idServiceStaff;
        this.idCourse = idCourse;
        this.idCourseAndStudent = idCourseAndStudent;
    }

    public PersistenceIdObjects() {
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

    public int getIdStudent() {
        return idStudent;
    }

    public void addIdStudent() {
        this.idStudent++;
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
    
    public int getIdCourse() {
        return idCourse;
    }

    public void addIdCourse() {
        this.idCourse++;
    }
    
    public int getIdCourseAndStudent() {
        return idCourseAndStudent;
    }

    public void addIdCourseAndStudent() {
        this.idCourseAndStudent++;
    }
    
}
