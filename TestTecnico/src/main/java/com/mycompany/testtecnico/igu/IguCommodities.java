/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testtecnico.igu;

import com.mycompany.testtecnico.logic.CourseAndStudent;
import com.mycompany.testtecnico.persistence.PersistenceController;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextField;

/**
 *
 * @author Bruno Maximiliano Ojeda Bayon
 */
public class IguCommodities {
    
        
    
        public static DefaultListModel cleanList(JList<String> list){
        DefaultListModel model = new DefaultListModel();
        
        list.setModel (model);
        return model;
    }
        
        public DefaultListModel addDate(JList<String> jldate, JTextField txtdate){
            DefaultListModel model = (DefaultListModel) jldate.getModel();
            model.addElement(txtdate.getText());
            
            return model;
        }
        
        public static ArrayList<String> obtainJListElements(javax.swing.JList<String> jList) {
    ArrayList<String> elementos = new ArrayList<>();
    for (int i = 0; i < jList.getModel().getSize(); i++) {
        elementos.add(jList.getModel().getElementAt(i));
    }
    return elementos;
}
    
        public DefaultListModel sectionList(JList<String> jldate) {
            PersistenceController persistenceController = new PersistenceController();
            ArrayList<String> names = persistenceController.getSectionsName();

            DefaultListModel model = (DefaultListModel) jldate.getModel();
            if (names!=null && !names.isEmpty()){
               for (int index = 0; index < names.size(); index++) {
                model.addElement(names.get(index));
                } 
            }
            
            return model;
        }
        public DefaultListModel departmentList(JList<String> jldate) {
            PersistenceController persistenceController = new PersistenceController();
            ArrayList<String> names = persistenceController.getDepartmentsName();

            DefaultListModel model = (DefaultListModel) jldate.getModel();
            if (names!=null && !names.isEmpty()){
               for (int index = 0; index < names.size(); index++) {
                model.addElement(names.get(index));
                } 
            }
            
            return model;
        }
        public DefaultListModel courseList(JList<String> jldate) {
            PersistenceController persistenceController = new PersistenceController();
            ArrayList<String> names = persistenceController.getCoursesName();

            DefaultListModel model = (DefaultListModel) jldate.getModel();
            if (names!=null && !names.isEmpty()){
               for (int index = 0; index < names.size(); index++) {
                model.addElement(names.get(index));
                } 
            }
            
            return model;
        }
        
        public DefaultListModel teacherList(JList<String> jldate) {
            PersistenceController persistenceController = new PersistenceController();
            ArrayList<String> names = persistenceController.getTeachersName();

            DefaultListModel model = (DefaultListModel) jldate.getModel();
            if (names!=null && !names.isEmpty()){
               for (int index = 0; index < names.size(); index++) {
                model.addElement(names.get(index));
                } 
            }
            
            return model;
        }
        
        public DefaultListModel serviceStaffList(JList<String> jldate) {
            PersistenceController persistenceController = new PersistenceController();
            ArrayList<String> names = persistenceController.getServicestaffsName();

            DefaultListModel model = (DefaultListModel) jldate.getModel();
            if (names!=null && !names.isEmpty()){
               for (int index = 0; index < names.size(); index++) {
                model.addElement(names.get(index));
                } 
            }
            
            return model;
        }
        
        public DefaultListModel StudentList(JList<String> jldate) {
            PersistenceController persistenceController = new PersistenceController();
            ArrayList<String> names = persistenceController.getStudentsName();

            DefaultListModel model = (DefaultListModel) jldate.getModel();
            if (names!=null && !names.isEmpty()){
               for (int index = 0; index < names.size(); index++) {
                model.addElement(names.get(index));
                } 
            }
            
            return model;
        }
        
        public DefaultListModel<String> studentCoursesList(JList<String> jldate, Object student) {

            System.out.println("\n\n\n Creando lista de nombres de cursos relacionados con el estudiante.");
    // Llamada al método filterCourseAndStudentByStudent para obtener la lista filtrada.
    PersistenceController persisController = new PersistenceController();
    DefaultListModel<CourseAndStudent> filteredList = persisController.filterCourseAndStudentByStudent(student);

    DefaultListModel courseNamesList = (DefaultListModel) jldate.getModel();

    // Iterar sobre la lista filtrada y agregar los nombres de los cursos a la nueva lista.
    for (int index = 0; index < filteredList.size(); index++) {
        CourseAndStudent courseAndStudent = filteredList.getElementAt(index);
        
        System.out.println(courseAndStudent);

        courseNamesList.addElement(courseAndStudent.getCourse().getName());
    }

    // Devolver la lista de nombres de cursos.
    System.out.println("La lista creada es la siguiente: "+courseNamesList);
    return courseNamesList;
}


}
