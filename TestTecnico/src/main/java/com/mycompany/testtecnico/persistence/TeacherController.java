/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testtecnico.persistence;

import com.mycompany.testtecnico.logic.Department;
import com.mycompany.testtecnico.logic.Teacher;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * @author Bruno Maximiliano Ojeda Bayon
 */
public class TeacherController {
    public PersistenceController persisController = new PersistenceController();
    

    public void create(Teacher teacher) {

        String name = teacher.getName();
        String fullname = teacher.getFullname();
        String civilStatus = teacher.getCivilStatus();

        LocalDate date = teacher.getDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);

        String dispatch = teacher.getDispatchNumber();
        int department = teacher.getDepartment().getId();

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
                                cs2.setInt(2, 1);
                                cs2.setString(3, dispatch);
                                cs2.setInt(4, person_id);

                                int affectedRows2 = cs2.executeUpdate();

                                if (affectedRows2 > 0) {
                                    // Obtener el ID generado para el personal
                                    try (ResultSet generatedKeys2 = cs2.getGeneratedKeys()) {
                                        if (generatedKeys2.next()) {
                                            int staff_id = generatedKeys2.getInt(1);

                                            // Insertar en la tabla "Teacher" utilizando el ID del personal
                                            String consult3 = "INSERT INTO Teachers (department_id, staff_id) VALUES (?, ?)";
                                            try (PreparedStatement cs3 = conection.establishConnection().prepareStatement(consult3)) {
                                                
                                                
                                                cs3.setInt(1, department); // Reemplaza con el valor real
                                                cs3.setInt(2, staff_id);

                                                int affectedRows3 = cs3.executeUpdate();

                                                if (affectedRows3 > 0) {
                                                    System.out.println("¡Registro exitoso!");
                                                } else {
                                                    System.out.println("No se pudo insertar en la tabla Teacher.");
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
    
        
    public ArrayList<Teacher> read() {
        
    
    ArrayList<Teacher> teacherList = new ArrayList<>();
    Map<Integer, Department> departmentMap = new HashMap<>();

    DBConection connection = new DBConection();

    String consult = "SELECT t.id AS teacher_id, " +
                     "s.id AS staff_id, " +
                     "s.date AS staff_date, s.dispatchNumber AS staff_dispatchNumber, " +
                     "p.id AS person_id, p.name AS person_name, " +
                     "p.fullname AS person_fullname, p.civilStatus AS person_civilStatus, " +
                     "d.id AS department_id, d.name AS department_name " +
                     "FROM teachers t " +
                     "JOIN staff s ON t.staff_id = s.id " +
                     "JOIN person p ON s.person_id = p.id " +
                     "JOIN departments d ON t.department_id = d.id";

    try (Connection conn = connection.establishConnection();
         PreparedStatement ps = conn.prepareStatement(consult);
         ResultSet resultSet = ps.executeQuery()) {

        while (resultSet.next()) {
            int teacherId = resultSet.getInt("teacher_id");
            LocalDate staffDate = resultSet.getDate("staff_date").toLocalDate();
            String staffDispatchNumber = resultSet.getString("staff_dispatchNumber");

            int staffId = resultSet.getInt("staff_id");

            int personId = resultSet.getInt("person_id");
            String personName = resultSet.getString("person_name");
            String personFullname = resultSet.getString("person_fullname");
            String personCivilStatus = resultSet.getString("person_civilStatus");

            int departmentId = resultSet.getInt("department_id");
            String departmentName = resultSet.getString("department_name");

            // Utiliza el mapa para obtener la instancia existente o crear una nueva
            Department department = departmentMap.computeIfAbsent(departmentId, id -> new Department(id, departmentName));
            
            Department trueDepartment = persisController.findDepartment(department.getName());

            Teacher teacher = new Teacher(department, staffDate, staffDispatchNumber,
                                         personName, personFullname, personCivilStatus, staffId, teacherId);
            teacher.setId(personId);
            teacherList.add(teacher);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return teacherList;
}
        

    public void update(Teacher teacher) {
    try {
        DBConection connection = new DBConection();
        int idTeacher = teacher.getId_teacher();
        int idStaff = teacher.getId_staff();
        int idPerson = teacher.getId();

        int departmentId = teacher.getDepartment().getId();
        String civilStatus = teacher.getCivilStatus();
        String dispatch = teacher.getDispatchNumber();

        String consultTeacher = "UPDATE teachers SET department_id = ? WHERE id = ?";
        String consultStaff = "UPDATE staff SET dispatchNumber = ? WHERE id = ?";
        String consultPerson = "UPDATE person SET civilStatus = ? WHERE id = ?";

        try (CallableStatement csTeacher = connection.establishConnection().prepareCall(consultTeacher)) {
            csTeacher.setInt(1, departmentId);
            csTeacher.setInt(2, idTeacher);

            int rowsAffectedTeacher = csTeacher.executeUpdate();
            System.out.println(rowsAffectedTeacher + " Teacher actualizado");

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

   public void delete (Teacher teacher){
       int idTeacher = teacher.getId_teacher();
       int idStaff = teacher.getId_staff();
       int idPerson = teacher.getId();
       
        DBConection conection = new DBConection();
        
        String consultTeacher = ("delete from teachers where id=?;");
        String consultStaff = ("delete from staff where id=?;");
        String consultPerson = ("delete from person where id=?;");
        
        try {
            try (CallableStatement csTeacher = conection.establishConnection().prepareCall(consultTeacher)) {
            csTeacher.setInt(1, idTeacher);
            csTeacher.execute();
            System.out.println("Teacher borrado");
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

