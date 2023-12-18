/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.testtecnico;

import com.mycompany.testtecnico.igu.StartPanel;
import com.mycompany.testtecnico.persistence.DBConection;


/**
 *
 * @author Bruno Maximiliano Ojeda Bayón
 */
public class TestTecnico {
    


    public static void main(String[] args) {
        
        StartPanel screen = new StartPanel();
        screen.setVisible(true);
        screen.setLocationRelativeTo(null);
        
        
        DBConection conector = new DBConection();
        

        conector.verifyTables();
        
//        Department literatura = new Department(1, "Literatura");
//        
//        Teacher teacher = new Teacher(literatura, LocalDate.now(), "030", "Manuel", "Belgrano", "Soltero", 1, 1);
//        teacher.setId(1);
//        
//        DepartmentController dController = new DepartmentController();
//        dController.create(literatura);
//        
//        TeacherController tController = new TeacherController();
//        tController.create(teacher);
    }
}
