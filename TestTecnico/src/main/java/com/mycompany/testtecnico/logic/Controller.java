/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testtecnico.logic;

import com.mycompany.testtecnico.persistence.PersistenceIdObjects;
import com.mycompany.testtecnico.persistence.PersistenceController;
import static com.mycompany.testtecnico.persistence.PersistenceController.*;
import com.mycompany.testtecnico.persistence.PersistenceService;

import java.time.LocalDate;
import java.util.Iterator;
import javax.swing.DefaultListModel;

/**
 *
 * @author Bruno Maximiliano Ojeda Bayón
 */
public class Controller {

   PersistenceController persisController = new PersistenceController();
   PersistenceService persisService = new PersistenceService();

    
    private static PersistenceIdObjects idObject = new PersistenceIdObjects (0,0,0,0,0,0,0,0);
    
    
    
    
    public void saveSection(String name) {

        Section section = new Section();
        section.setName(name);
        section.setId(idObject.getIdSection());
        idObject.addIdSection();

        persisController.addSectionObject(section);
        System.out.println(persisController.getSectionsList());

        persisService.sectionC.create(section);

    }

    public void deleteSection(Section object){
        System.out.println("metodo Inicializado");
        Iterator<Section> iterator = objectSections.iterator();
    
    while (iterator.hasNext()) {
        System.out.println("bucle Inicializado");
        Section currentObject = iterator.next();
        
        if (currentObject.equals(object)) {
            System.out.println("La siguiente sección ha sido eliminada: "+currentObject);
            
            persisService.sectionC.delete(object);
            iterator.remove();

            }
        }
    System.out.println("metodo finalizado");
    }
    
    public void editSection(Section object, String name){
        
        for (Section section : objectSections) {
        System.out.println("Buscando Sección: " + section);

        if (section.equals(object)) {
            section.setName(name);
            persisService.sectionC.update(section);
            break;
        }
    }
    }
    
    
    
    public void saveDepartment(String name) {

        Department department = new Department();
        department.setName(name);
        department.setId(idObject.getIdDepartment());
        idObject.addIdDepartment();

        persisController.addDepartmentObject(department);
        System.out.println(persisController.getDepartmentsList());
        
        persisService.departmentC.create(department);



    }
    
    public void deleteDepartment(Department object){
        Iterator<Department> iterator = objectDepartments.iterator();
    
    while (iterator.hasNext()) {
        Department currentObject = iterator.next();
        if (currentObject == object) {
            System.out.println("El siguiente departamento ha sido eliminado: "+iterator);
            
            persisService.departmentC.delete(object);
            iterator.remove();
            break;
            }
        }
    }
    
    public void editDepartment(Department object, String name){
        
        for (Department department : objectDepartments) {
        System.out.println("Buscando Departamento: " + department);

        if (department.equals(object)) {
            department.setName(name);
            persisService.departmentC.update(department);
            break;
        }
    }
    }
    
    

    
    
    public void saveTeacher(String name, String fullname, LocalDate date, String dispatch,
                            Object civilStatus, Object department) {
        String status = civilStatus.toString();
        
        Teacher teacher = new Teacher(date, dispatch, name, fullname, status);
        teacher.setName(name);
        teacher.setFullname(fullname);
        teacher.setDate(date);
        teacher.setDispatchNumber(dispatch);
        teacher.setDepartment(persisController.findDepartment(department));
        
        teacher.setId(idObject.getIdTeacher());
        idObject.addIdTeacher();
        idObject.addIdPerson();

        persisController.addTeacherObject(teacher);
        System.out.println(persisController.getTeacherList());
        
        persisService.teacherC.create(teacher);

    }
    
    public void deleteTeacher(Teacher object){
        Iterator<Teacher> iterator = objectTeachers.iterator();
    
    while (iterator.hasNext()) {
        Teacher currentObject = iterator.next();
        if (currentObject == object) {
            System.out.println("El siguiente Profesor ha sido eliminado: "+iterator);
            persisService.teacherC.delete(object);
            iterator.remove();
            break;
            }
        }
    }
    
    public void editTeacher(Teacher object, String civilStatus, String dispatch, Department department){
        
        for (Teacher teacher : objectTeachers) {
        System.out.println("Buscando Profesor: " + teacher);

        if (teacher.equals(object)) {
            teacher.setCivilStatus(civilStatus);
            teacher.setDepartment(department);
            teacher.setDispatchNumber(dispatch);
            
            persisService.teacherC.update(teacher);
            break;
        }
    }
        
    }
    
    
    
    public void saveServiceStaff(String name, String fullname, LocalDate date, String dispatch,
                            Object civilStatus, Object section) {
        
        String status = civilStatus.toString();
        Servicestaff servicestaff = new Servicestaff(date, dispatch, name, fullname, status);
        servicestaff.setName(name);
        servicestaff.setFullname(fullname);
        servicestaff.setDate(date);
        servicestaff.setDispatchNumber(dispatch);
        servicestaff.setSection(persisController.findSection(section));
        
        servicestaff.setId(idObject.getIdServicestaff());
        servicestaff.setId(idObject.getIdServicestaff());
        servicestaff.setId(idObject.getIdServicestaff());
        idObject.addIdServicestaff();
        idObject.addIdStaff();
        idObject.addIdPerson();

        persisController.addServicestaffObject(servicestaff);
        System.out.println(persisController.getServicestaffList());
        
        persisService.servicestaffC.create(servicestaff);

    }
    
    public void deleteServicestaff(Servicestaff object){
        Iterator<Servicestaff> iterator = objectServiceStaffs.iterator();
    
    while (iterator.hasNext()) {
        Servicestaff currentObject = iterator.next();
        if (currentObject == object) {
            System.out.println("El siguiente Personal de servicio ha sido eliminado: "+iterator);
            persisService.servicestaffC.delete(object);
            iterator.remove();
            break;
            }
        }
    }
    
    public void editServicestaff(Servicestaff object, String civilStatus, String dispatch, Section section){
        
        for (Servicestaff servicestaff : objectServiceStaffs) {
        System.out.println("Buscando Personal de servicio: " + servicestaff);

        if (servicestaff.equals(object)) {
            servicestaff.setCivilStatus(civilStatus);
            servicestaff.setSection(section);
            servicestaff.setDispatchNumber(dispatch);
            
            persisService.servicestaffC.update(object);
            break;
        }
    }
        
    }
    
    
    
    public void saveStudent(String name, String fullname, Object civilStatus) {

        String status = civilStatus.toString();
        Student student = new Student(name, fullname, status);
        student.setName(name);
        student.setFullname(fullname);
        
        student.setId(idObject.getIdPerson());
        idObject.addIdPerson();

        persisController.addStudentObject(student);
        System.out.println(persisController.getServicestaffList());
        
        persisService.studentC.create(student);

    }
    
    public void deleteStudent(Student object){
        Iterator<Student> iterator = objectStudents.iterator();
    
    while (iterator.hasNext()) {
        Student currentObject = iterator.next();
        if (currentObject == object) {
            System.out.println("El siguiente Estudiante ha sido eliminado: "+ currentObject.getName()+ " "
                    + currentObject.getFullname() +"\nY con el, todas sus relaciones");
            
            Object studentName = currentObject.getName();

            // Obtener la lista de relaciones
            DefaultListModel<CourseAndStudent> listRelations = persisController.filterCourseAndStudentByStudent(studentName);

            // Eliminar todas las relaciones de la lista CoursesAndStudents
            for (int i = 0; i < listRelations.size(); i++) {
                CourseAndStudent relation = listRelations.getElementAt(i);
                deleteCourseAndStudent(relation);
            }

            persisService.studentC.delete(object);
            iterator.remove();
            break;
        }
    }
}
            
    public void editStudent(Student object, String civilStatus){
        
        for (Student student : objectStudents) {
        System.out.println("Buscando Estudiante: " + student);

        if (student.equals(object)) {
            student.setCivilStatus(civilStatus);
            persisService.studentC.update(student);
            break;
        }
    }
        
    }
    
        
    
    public void saveCourse(Object year, String name) {

        String trueYear = year.toString();
        Course course = new Course();
        
        course.setName(trueYear+" "+name);
        course.setId(idObject.getIdCourse());
        idObject.addIdCourse();

        persisController.addCourseObject(course);
        System.out.println(persisController.getCourseList());
        
        persisService.courseC.create(course);



    }
    
    public void deleteCourse(Course object) {
    Iterator<Course> iterator = objectCourses.iterator();

    while (iterator.hasNext()) {
        Course currentObject = iterator.next();
        if (currentObject == object) {
            System.out.println("El siguiente Curso ha sido eliminado: " + currentObject.getName() +
                    "\nY con él, todas sus relaciones.");

            // Obtener el nombre del curso a eliminar
            Object courseName = currentObject.getName();

            // Obtener la lista de relaciones
            DefaultListModel<CourseAndStudent> listRelations = persisController.filterCourseAndStudentByCourse(courseName);

            // Eliminar todas las relaciones de la lista CoursesAndStudents
            for (int i = 0; i < listRelations.size(); i++) {
                CourseAndStudent relation = listRelations.getElementAt(i);
                deleteCourseAndStudent(relation);
            }

            persisService.courseC.delete(object);
            iterator.remove();
            break;
        }
    }
}
    
    public void editCourse(Course object, String name){
        
        for (Course course : objectCourses) {
        System.out.println("Buscando Curso: " + course);

        if (course.equals(object)) {
            course.setName(name);
            persisService.courseC.update(course);
            break;
        }
    }
    }
    
    
    
    public void saveCourseAndStudent(String name, Object course) {
        
        System.out.println("Guardando relación en la capa logic.");
        
        CourseAndStudent courseAndStudent = new CourseAndStudent();
        courseAndStudent.setStudent(persisController.findStudent(name));
        courseAndStudent.setCourse(persisController.findCourse(course));
        
        
        
        Course trueCourse = persisController.findCourse(course);
        
        
        courseAndStudent.setId(idObject.getIdCourseAndStudent());
        idObject.addIdCourseAndStudent();
        
        System.out.println(courseAndStudent);

        persisController.addCourseAndStudentObject(courseAndStudent);
        System.out.println(persisController.getCourseAndStudentList());
        
        System.out.println("Guardando relación en la capa persistence.");
        persisService.coursesAndStudentsC.create(courseAndStudent);
        System.out.println(courseAndStudent);
        
    }
    
    public void deleteCourseAndStudent(CourseAndStudent object){
        Iterator<CourseAndStudent> iterator = objectCoursesAndStudents.iterator();
    
    while (iterator.hasNext()) {
        CourseAndStudent currentObject = iterator.next();
        if (currentObject == object) {
            System.out.println("La siguiente Relación ha sido eliminada: "+currentObject);
            
            persisService.coursesAndStudentsC.delete(object);
            iterator.remove();
            break;
            }
        }
    }
    

}
