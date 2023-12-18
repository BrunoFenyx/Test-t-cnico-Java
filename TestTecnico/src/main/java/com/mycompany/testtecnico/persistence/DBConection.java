/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testtecnico.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Bruno Maximilinano Ojeda Bayón
 */
public class DBConection {

    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    private static final String DATABASE_NAME = "test_tecnico";
    private static final String IP = "localhost";
    private static final String PORT = "3306";
    private static final String TIME_ZONE = "America/Argentina/Buenos_Aires";
    private static final String URL = "jdbc:mysql://" + IP + ":" + PORT + "/" + DATABASE_NAME + "?serverTimezone=" + TIME_ZONE;

    public static Connection establishConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar:");
            System.out.println( e.getMessage());
//        } finally {
//            
//            try {
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace(); 
//            }
        }
        return connection;
    }
    
    public static void verifyTables(){
        
        Connection connection = null;
        Statement statement = null;
        try {
            // Establecer la conexión a la base de datos
            connection = establishConnection();
            // Crear un objeto Statement
            statement = connection.createStatement();

            // Verificar tabla Courses:
            if (!isTableExists(statement, "Courses")) {
                
                String createTableQuery = "CREATE TABLE Courses ("
                        + "id INT AUTO_INCREMENT PRIMARY KEY,"
                        + "name VARCHAR(255)"
                        + ")";
                statement.executeUpdate(createTableQuery);

                System.out.println("La tabla Courses ha sido creada exitosamente.");
            } 
            else {
                System.out.println("La tabla Courses ya existe.");
            }
            
            //Verificar tabla Sections
            if (!isTableExists(statement, "Sections")) {
                
                String createTableQuery = "CREATE TABLE Sections ("
                        + "id INT AUTO_INCREMENT PRIMARY KEY,"
                        + "name VARCHAR(255)"
                        + ")";
                statement.executeUpdate(createTableQuery);

                System.out.println("La tabla Sections ha sido creada exitosamente.");
            } 
            else {
                System.out.println("La tabla Sections ya existe.");
            }
            
            // Verificar tabla Departments:
            if (!isTableExists(statement, "Departments")) {
                
                String createTableQuery = "CREATE TABLE Departments ("
                        + "id INT AUTO_INCREMENT PRIMARY KEY,"
                        + "name VARCHAR(255)"
                        + ")";
                statement.executeUpdate(createTableQuery);

                System.out.println("La tabla Departments ha sido creada exitosamente.");
            } 
            else {
                System.out.println("La tabla Departments ya existe.");
            }
            
            // Verificar tabla Person:
            if (!isTableExists(statement, "Person")) {
                
                String createTableQuery = "CREATE TABLE Person ("
                        + "id INT AUTO_INCREMENT PRIMARY KEY,"
                        + "name VARCHAR(255),"
                        + "type INT,"
                        + "fullname VARCHAR(255),"
                        + "civilStatus VARCHAR(255)"
                        + ")";
                statement.executeUpdate(createTableQuery);

                System.out.println("La tabla Person ha sido creada exitosamente.");
            } 
            else {
                System.out.println("La tabla Person ya existe.");
            }
            

            // Verificar tabla Students:
//            if (!isTableExists(statement, "Students")) {
//                
//                String createTableQuery = "CREATE TABLE Students ("
//                        + "id INT AUTO_INCREMENT PRIMARY KEY,"
//                        + "person_id INT,"
//                        + "FOREIGN KEY (person_id) REFERENCES Person(type)"
//                        + ")";
//                statement.executeUpdate(createTableQuery);
//
//                System.out.println("La tabla Students ha sido creada exitosamente.");
//            } 
//            else {
//                System.out.println("La tabla Students ya existe.");
//            }
            
            // Verificar tabla Staff:
            if (!isTableExists(statement, "Staff")) {
                
                String createTableQuery = "CREATE TABLE Staff ("
                        + "id INT AUTO_INCREMENT PRIMARY KEY,"
                        + "date DATE,"
                        + "type INT,"
                        + "dispatchNumber VARCHAR(50),"
                        + "person_id INT,"
                        + "FOREIGN KEY (person_id) REFERENCES Person(id) ON DELETE CASCADE"
                        + ")";
                statement.executeUpdate(createTableQuery);

                System.out.println("La tabla Staff ha sido creada exitosamente.");
            } 
            else {
                System.out.println("La tabla Staff ya existe.");
            }
            
            // Verificar tabla Teachers:
            if (!isTableExists(statement, "Teachers")) {
                
                String createTableQuery = "CREATE TABLE Teachers ("
                        + "id INT AUTO_INCREMENT PRIMARY KEY,"
                        + "department_id INT,"
                        + "staff_id INT,"
                        + "FOREIGN KEY (department_id) REFERENCES Departments(id),"
                        + "FOREIGN KEY (staff_id) REFERENCES Staff(id) ON DELETE CASCADE"
                        + ")";
                statement.executeUpdate(createTableQuery);

                System.out.println("La tabla Teachers ha sido creada exitosamente.");
            } 
            else {
                System.out.println("La tabla Teachers ya existe.");
            }
            
            // Verificar tabla Servicestaffs:
            if (!isTableExists(statement, "Servicestaffs")) {
                
                String createTableQuery = "CREATE TABLE Servicestaffs ("
                        + "id INT AUTO_INCREMENT PRIMARY KEY,"
                        + "section_id INT,"
                        + "staff_id INT,"
                        + "FOREIGN KEY (section_id) REFERENCES Sections(id),"
                        + "FOREIGN KEY (staff_id) REFERENCES Staff(id)"
                        + ")";
                statement.executeUpdate(createTableQuery);

                System.out.println("La tabla Servicestaffs ha sido creada exitosamente.");
            } 
            else {
                System.out.println("La tabla Servicestaffs ya existe.");
            }
            
            // Verificar tabla CourseAndStudent:
            if (!isTableExists(statement, "CoursesAndStudents")) {
                
                String createTableQuery = "CREATE TABLE CoursesAndStudents ("
                        + "id INT AUTO_INCREMENT PRIMARY KEY,"
                        + "student_id INT,"
                        + "course_id INT,"
                        + "FOREIGN KEY (student_id) REFERENCES person(id) ON DELETE CASCADE,"
                        + "FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE"
                        + ")";
                statement.executeUpdate(createTableQuery);

                System.out.println("La tabla CoursesAndStudents ha sido creada exitosamente.");
            } 
            else {
                System.out.println("La tabla CoursesAndStudents ya existe.");
            }
            
        } 
        catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    public static boolean isTableExists(Statement statement, String tableName) throws SQLException {
        // Verificar si la tabla ya existe en la base de datos
        String checkTableQuery = "SHOW TABLES LIKE '" + tableName + "'";
        return statement.executeQuery(checkTableQuery).next();
    }

}
