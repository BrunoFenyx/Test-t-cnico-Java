/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testtecnico.logic;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Bruno Maximiliano Ojeda Bayón
 */

@Entity
public class Teacher extends Staff {
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    private int id_staff;
    private int id_teacher;

    public Teacher(Department department, LocalDate date, String dispatchNumber, String name,
            String fullname, String civilStatus, int id_staff, int id_teacher) {
        super(date, dispatchNumber, name, fullname, civilStatus);
        this.department = department;
        this.id_staff = id_staff;
        this.id_teacher = id_teacher;
    }

    public Teacher(LocalDate date, String dispatchNumber, String name, String fullname, String civilStatus) {
        super(date, dispatchNumber, name, fullname, civilStatus);
    }
 

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    
    @Override
    public String toString() {
        return "Teacher{" +
                "department=" + department +
                ", date=" + getDate() +
                ", dispatchNumber='" + getDispatchNumber() + '\'' +
                ", name='" + getName() + '\'' +
                ", fullname='" + getFullname() + '\'' +
                ", civilStatus=" + getCivilStatus() +
                '}';
    }

    public int getId_staff() {
        return id_staff;
    }

    public void setId_staff(int id_staff) {
        this.id_staff = id_staff;
    }

    public int getId_teacher() {
        return id_teacher;
    }

    public void setId_teacher(int id_teacher) {
        this.id_teacher = id_teacher;
    }

}
