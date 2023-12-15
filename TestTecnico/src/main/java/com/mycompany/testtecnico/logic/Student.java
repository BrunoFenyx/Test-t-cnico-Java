/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testtecnico.logic;

import javax.persistence.Entity;

/**
 *
 * @author Bruno Maximiliano Ojeda Bayón
 */

@Entity
public class Student extends Person {

    public Student(String name, String fullname, String civilStatus) {
        super(name, fullname, civilStatus);
    }
    
    @Override
    public String toString() {
        return "Student{" +
                "name='" + getName() + '\'' +
                ", fullname='" + getFullname() + '\'' +
                ", civilStatus='" + getCivilStatus() + '\'' +
                // Puedes agregar más campos de la clase base si es necesario
                '}';
    }

}
