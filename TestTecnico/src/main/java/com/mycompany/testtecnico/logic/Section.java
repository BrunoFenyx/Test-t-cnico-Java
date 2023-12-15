/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testtecnico.logic;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Bruno Maximiliano Ojeda Bayón
 */
@Entity
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Basic
    private String name;
    @OneToMany(mappedBy = "servicestaff", cascade = CascadeType.ALL)
    private List<Servicestaff> servicestaff;

    public Section(int id, String name, List<Servicestaff> servicestaff) {
        this.id = id;
        this.name = name;
        this.servicestaff = servicestaff;
    }

    public Section() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Servicestaff> getServicestaff() {
        return servicestaff;
    }

    public void setServicestaff(List<Servicestaff> servicestaff) {
        this.servicestaff = servicestaff;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", servicestaff=" + servicestaff +
                '}';
    }
}
