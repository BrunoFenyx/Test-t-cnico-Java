/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testtecnico.logic;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Bruno Maximiliano Ojeda Bayón
 */

@Entity
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Basic
    private String name;
    
    
    public Course(int id, String name) {
        this.id = id;
        this.name = name;

    }

    public Course() {
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
     @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +

                '}';
    }
    
}
