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
public class Servicestaff extends Staff{
    
    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;
    private int id_staff;
    private int id_servicestaff;

    public Servicestaff(Section section, LocalDate date, String dispatchNumber,
                        String name, String fullname, String civilStatus,
                        int id_staff, int id_servicestaff) {
        super(date, dispatchNumber, name, fullname, civilStatus);
        this.section = section;
        this.id_staff = id_staff;
        this.id_servicestaff = id_servicestaff;
    }

    public Servicestaff(LocalDate date, String dispatchNumber, String name, String fullname, String civilStatus) {
        super(date, dispatchNumber, name, fullname, civilStatus);
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public int getId_staff() {
        return id_staff;
    }

    public void setId_staff(int id_staff) {
        this.id_staff = id_staff;
    }

    public int getId_servicestaff() {
        return id_servicestaff;
    }

    public void setId_servicestaff(int id_servicestaff) {
        this.id_servicestaff = id_servicestaff;
    }
    
    
    
    @Override
    public String toString() {
        return "Servicestaff{" +
                "section=" + section +
                ", date=" + getDate() +
                ", dispatchNumber='" + getDispatchNumber() + '\'' +
                ", name='" + getName() + '\'' +
                ", fullname='" + getFullname() + '\'' +
                ", civilStatus='" + getCivilStatus() + '\'' +
                '}';
    }
    
}
