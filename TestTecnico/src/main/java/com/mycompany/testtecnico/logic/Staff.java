/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testtecnico.logic;

import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Bruno Maximiliano Ojeda Bayón
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Staff extends Person {
    @Temporal(TemporalType.DATE)
    private LocalDate date;
    @Basic
    private String dispatchNumber;

    public Staff(LocalDate date, String dispatchNumber, String name, String fullname, String civilStatus) {
        super(name, fullname, civilStatus);
        this.date = date;
        this.dispatchNumber = dispatchNumber;
    }

    public Staff(String name, String fullname, String civilStatus) {
        super(name, fullname, civilStatus);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDispatchNumber() {
        return dispatchNumber;
    }

    public void setDispatchNumber(String dispatchNumber) {
        this.dispatchNumber = dispatchNumber;
    }
    
    
}
