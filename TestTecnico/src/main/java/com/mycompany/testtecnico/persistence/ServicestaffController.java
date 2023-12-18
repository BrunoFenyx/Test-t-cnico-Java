/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testtecnico.persistence;


import com.mycompany.testtecnico.logic.Department;
import com.mycompany.testtecnico.logic.Section;
import com.mycompany.testtecnico.logic.Servicestaff;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Bruno Maximiliano Ojeda Bayón
 */
public class ServicestaffController {
    
    PersistenceController persisController = new PersistenceController();
    
    public void create(Servicestaff servicestaff) {

        String name = servicestaff.getName();
        String fullname = servicestaff.getFullname();
        String civilStatus = servicestaff.getCivilStatus();

        LocalDate date = servicestaff.getDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);

        String dispatch = servicestaff.getDispatchNumber();
        int section = servicestaff.getSection().getId();

        DBConection conection = new DBConection();

        String consult1 = "INSERT INTO person (name, fullname, civilStatus, type) VALUES (?, ?, ?, ?)";

        try {
            //--------------------Creación de la fila Persona---------------------//
            // Usar try-with-resources para cerrar automáticamente la conexión
            try (PreparedStatement cs1 = conection.establishConnection().prepareStatement(consult1, Statement.RETURN_GENERATED_KEYS)) {

                cs1.setString(1, name);
                cs1.setString(2, fullname);
                cs1.setString(3, civilStatus);
                cs1.setInt(4, 2);

                int affectedRows = cs1.executeUpdate();

                if (affectedRows > 0) {
                    // Obtener el ID generado para la persona
                    try (ResultSet generatedKeys = cs1.getGeneratedKeys()) {

                        //--------------------Creación de la fila Staff---------------------//
                        if (generatedKeys.next()) {
                            int person_id = generatedKeys.getInt(1);

                            // Insertar en la tabla "staff" utilizando el ID de la persona
                            String consult2 = "INSERT INTO staff (date, type, dispatchNumber, person_id) VALUES (?, ?, ?, ?)";
                            try (PreparedStatement cs2 = conection.establishConnection().prepareStatement(consult2, Statement.RETURN_GENERATED_KEYS)) {
                                cs2.setString(1, formattedDate);
                                cs2.setInt(2, 2);
                                cs2.setString(3, dispatch);
                                cs2.setInt(4, person_id);

                                int affectedRows2 = cs2.executeUpdate();

                                if (affectedRows2 > 0) {
                                    // Obtener el ID generado para el personal
                                    try (ResultSet generatedKeys2 = cs2.getGeneratedKeys()) {
                                        
                            //--------------------Creación de la fila Servicestaff---------------------//
                                        if (generatedKeys2.next()) {
                                            int staff_id = generatedKeys2.getInt(1);

                                            // Insertar en la tabla "Servicestaff" utilizando el ID del personal
                                            String consult3 = "INSERT INTO Servicestaffs (section_id, staff_id) VALUES (?, ?)";
                                            try (PreparedStatement cs3 = conection.establishConnection().prepareStatement(consult3)) {
                                                
                                                
                                                cs3.setInt(1, section);
                                                cs3.setInt(2, staff_id);

                                                int affectedRows3 = cs3.executeUpdate();

                                                if (affectedRows3 > 0) {
                                                    System.out.println("¡Registro exitoso!");
                                                } else {
                                                    System.out.println("No se pudo insertar en la tabla Servicestaffs.");
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    // No se pudo obtener el ID generado para el personal
                                    System.out.println("No se pudo obtener el ID generado para el personal.");
                                }
                            }
                        } else {
                            // No se pudo obtener el ID generado para la persona
                            System.out.println("No se pudo obtener el ID generado para la persona.");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public ArrayList<Servicestaff> read() {
        
        ArrayList<Servicestaff> teacherList = new ArrayList<>();
        Map<Integer, Section> sectionMap = new HashMap<>();

    DBConection connection = new DBConection();

    String consult = "SELECT t.id AS servicestaff_id, " +
                     "s.id AS staff_id, " +
                     "s.date AS staff_date, s.dispatchNumber AS staff_dispatchNumber, " +
                     "p.id AS person_id, p.name AS person_name, " +
                     "p.fullname AS person_fullname, p.civilStatus AS person_civilStatus, " +
                     "d.id AS section_id, d.name AS section_name " +
                     "FROM servicestaffs t " +
                     "JOIN staff s ON t.staff_id = s.id " +
                     "JOIN person p ON s.person_id = p.id " +
                     "JOIN sections d ON t.section_id = d.id";

    try (Connection conn = connection.establishConnection();
         PreparedStatement ps = conn.prepareStatement(consult);
         ResultSet resultSet = ps.executeQuery()) {

        while (resultSet.next()) {
            int servicestaffId = resultSet.getInt("servicestaff_id");
            LocalDate staffDate = resultSet.getDate("staff_date").toLocalDate();
            String staffDispatchNumber = resultSet.getString("staff_dispatchNumber");
            
            int staffId = resultSet.getInt("staff_id");
            
            int personId = resultSet.getInt("person_id");
            String personName = resultSet.getString("person_name");
            String personFullname = resultSet.getString("person_fullname");
            String personCivilStatus = resultSet.getString("person_civilStatus");

            int sectionId = resultSet.getInt("section_id");
            String sectionName = resultSet.getString("section_name");

            Section section = sectionMap.computeIfAbsent(sectionId, id -> new Section(id, sectionName));
            
            Section trueSection = persisController.findSection(section.getName());
            
            Servicestaff servicestaff = new Servicestaff(trueSection, staffDate, staffDispatchNumber,
                                         personName, personFullname, personCivilStatus, staffId, servicestaffId);
            servicestaff.setId(personId);
            teacherList.add(servicestaff);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return teacherList;
    }


    public void update(Servicestaff servicestaff) {
    try {
        DBConection connection = new DBConection();
        int idServicestaff = servicestaff.getId_servicestaff();
        int idStaff = servicestaff.getId_staff();
        int idPerson = servicestaff.getId();

        int SectionId = servicestaff.getSection().getId();
        String civilStatus = servicestaff.getCivilStatus();
        String dispatch = servicestaff.getDispatchNumber();

        String consultServicestaff = "UPDATE servicestaffs SET section_id = ? WHERE id = ?";
        String consultStaff = "UPDATE staff SET dispatchNumber = ? WHERE id = ?";
        String consultPerson = "UPDATE person SET civilStatus = ? WHERE id = ?";

        try (CallableStatement csServicestaff = connection.establishConnection().prepareCall(consultServicestaff)) {
            csServicestaff.setInt(1, SectionId);
            csServicestaff.setInt(2, idServicestaff);

            int rowsAffectedcsServicestaff = csServicestaff.executeUpdate();
            System.out.println(rowsAffectedcsServicestaff + " Servicestaff actualizado");

            try (CallableStatement csStaff = connection.establishConnection().prepareCall(consultStaff)) {
                csStaff.setString(1, dispatch);
                csStaff.setInt(2, idStaff);

                int rowsAffectedStaff = csStaff.executeUpdate();
                System.out.println(rowsAffectedStaff + " Personal actualiazdo");

                try (CallableStatement csPerson = connection.establishConnection().prepareCall(consultPerson)) {
                    csPerson.setString(1, civilStatus);
                    csPerson.setInt(2, idPerson);

                    int rowsAffectedPerson = csPerson.executeUpdate();
                    System.out.println(rowsAffectedPerson + " Persona actualizada");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    
    public void delete (Servicestaff servicestaff){
       int idServicestaff = servicestaff.getId_servicestaff();
       int idStaff = servicestaff.getId_staff();
       int idPerson = servicestaff.getId();
       
        DBConection conection = new DBConection();
        
        String consultServicestaff = ("delete from servicestaffs where id=?;");
        String consultStaff = ("delete from staff where id=?;");
        String consultPerson = ("delete from person where id=?;");
        
        try {
            try (CallableStatement csServicestaff = conection.establishConnection().prepareCall(consultServicestaff)) {
            csServicestaff.setInt(1, idServicestaff);
            csServicestaff.execute();
            System.out.println("Servicestaff borrado");
            }
            try (CallableStatement csStaff = conection.establishConnection().prepareCall(consultStaff)) {
            csStaff.setInt(1, idStaff);
            csStaff.execute();
            System.out.println("Staff borrado");
            }
            try (CallableStatement csPerson = conection.establishConnection().prepareCall(consultPerson)) {
            csPerson.setInt(1, idPerson);
            csPerson.execute();
            System.out.println("Person borrada");
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
    
}
