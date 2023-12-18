/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testtecnico.persistence;

import com.mycompany.testtecnico.logic.CivilStatus;
import com.mycompany.testtecnico.logic.CourseAndStudent;
import com.mycompany.testtecnico.logic.Course;
import com.mycompany.testtecnico.logic.Section;
import com.mycompany.testtecnico.logic.Student;
import com.mycompany.testtecnico.logic.Department;
import com.mycompany.testtecnico.logic.Servicestaff;
import com.mycompany.testtecnico.logic.Teacher;


import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author Bruno Maximiliano Ojeda Bayon
 */
public class PersistenceController {
    
    public static PersistenceService persisService = new PersistenceService();
    
    
    public static List<Section> objectSections = persisService.sectionC.read();
    public static List<Department> objectDepartments = persisService.departmentC.read();
    public static List<Course> objectCourses = persisService.courseC.read();
    
     
    
    public static List<Teacher> objectTeachers = persisService.teacherC.read();
    public static List<Student> objectStudents = persisService.studentC.read();
    public static List<Servicestaff> objectServiceStaffs = persisService.servicestaffC.read();
    
    public static List<CourseAndStudent> objectCoursesAndStudents = persisService.coursesAndStudentsC.read();

    
    public ArrayList<String> getSectionsName(){
        ArrayList<String> sectionsName = new ArrayList<>();
        for (int index = 0; index < objectSections.size(); index++){
            Section object = objectSections.get(index);
            sectionsName.add(object.getName());
        }
        return sectionsName;
    }
    public ArrayList<String> getDepartmentsName(){
        ArrayList<String> departmentsName = new ArrayList<>();
        for (int index = 0; index < objectDepartments.size(); index++){
            Department object = objectDepartments.get(index);
            departmentsName.add(object.getName());
        }
        return departmentsName;
    }
    
    
    public ArrayList<String> getTeachersName(){
        ArrayList<String> teachersName = new ArrayList<>();
        for (int index = 0; index < objectTeachers.size(); index++){
            Teacher object = objectTeachers.get(index);
            teachersName.add(object.getName()+" "+object.getFullname());
        }
        return teachersName;
    }
    
    public ArrayList<String> getServicestaffsName(){
        ArrayList<String> servicestaffsName = new ArrayList<>();
        for (int index = 0; index < objectServiceStaffs.size(); index++){
            Servicestaff object = objectServiceStaffs.get(index);
            servicestaffsName.add(object.getName()+" "+object.getFullname());
        }
        return servicestaffsName;
    }
    
    public ArrayList<String> getStudentsName(){
        ArrayList<String> studentsName = new ArrayList<>();
        for (int index = 0; index < objectStudents.size(); index++){
            Student object = objectStudents.get(index);
            studentsName.add(object.getName()+" "+object.getFullname());
        }
        return studentsName;
    }
    
    public ArrayList<String> getCoursesName(){
        ArrayList<String> courseName = new ArrayList<>();
        for (int index = 0; index < objectCourses.size(); index++){
            Course object = objectCourses.get(index);
            courseName.add(object.getName());
        }
        return courseName;
    }
    
    
    
    public Department findDepartment(Object departmentName) {
    Department foundDepartment = null;
    System.out.println(departmentName);

    for (Department department : objectDepartments) {
        System.out.println("Buscando departamento: " + department);

        if (department.getName().equals(departmentName)) {
            foundDepartment = department;
            break;
        }
    }

    return foundDepartment;
}
    
    public Section findSection(Object sectionName) {
    Section foundSection = null;
    System.out.println(sectionName);

    for (Section section : objectSections) {
        System.out.println("Buscando Sección: " + section);

        if (section.getName().equals(sectionName)) {
            foundSection = section;
            break;
        }
    }

    return foundSection;
}
    
    public Course findCourse(Object courseName) {
    Course foundCourse = null;
    System.out.println(courseName);

    for (Course course : objectCourses) {
        System.out.println("Buscando Curso: " + course);

        if (course.getName().equals(courseName) || ((course.getName()).substring(4)).equals(courseName)) {
            foundCourse = course;
            break;
        }
    }

    return foundCourse;
}
    
    public Student findStudent(Object studentName) {
    Student foundStudent = null;
    System.out.println(studentName);

    for (Student student : objectStudents) {
        System.out.println("Buscando Estudiante: " + student);

        if (student.getName().equals(studentName) || (student.getName()+" "+student.getFullname()) .equals(studentName)) {
            foundStudent = student;
            break;
        }
    }

    return foundStudent;
}
    
    public Servicestaff findServicestaff(Object servicestaffName) {
    Servicestaff foundServicestaff = null;
    System.out.println(servicestaffName);

    for (Servicestaff servicestaff : objectServiceStaffs) {
        System.out.println("Buscando Personal de Servicio: " + servicestaff);

        if ((servicestaff.getName()+" "+servicestaff.getFullname()).equals(servicestaffName) || servicestaff.getName().equals(servicestaffName)) {
            foundServicestaff = servicestaff;
            break;
        }
    }
    System.out.println("Personal de Servicio elegido: " + foundServicestaff);
    return foundServicestaff;
}
    
    public Teacher findTeacher(Object teacherName) {
    Teacher foundTeacher = null;
    System.out.println(teacherName);

    for (Teacher teacher : objectTeachers) {
        System.out.println("Buscando Personal de Servicio: " + teacher);

        if ((teacher.getName()+" "+teacher.getFullname()).equals(teacherName) || teacher.getName().equals(teacherName)) {
            foundTeacher = teacher;
            break;
        }
    }
    System.out.println("Personal de Servicio elegido: " + foundTeacher);
    return foundTeacher;
}

    public CourseAndStudent findCourseAndStudent(Object courseAndStudentName) {
    CourseAndStudent foundCourseAndStudent = null;
    System.out.println(courseAndStudentName);

    for (CourseAndStudent courseAndStudent : objectCoursesAndStudents) {
        System.out.println("Buscando Relacion entre curso y estudiante: " + courseAndStudent);

        if ((courseAndStudent.getCourse().getName()).equals(courseAndStudentName) || (courseAndStudent.getStudent()).getName().equals(courseAndStudentName)) {
            foundCourseAndStudent = courseAndStudent;
            break;
        }
    }
    System.out.println("Relacion elegida: " + foundCourseAndStudent);
    return foundCourseAndStudent;
}
    
    
        
    public DefaultListModel<CourseAndStudent> filterStudentsBy(Object course) {
        
        Course trueCourse = findCourse(course);
        
        DefaultListModel<CourseAndStudent> filteredList = new DefaultListModel<>();
        for (CourseAndStudent index : objectCoursesAndStudents) {
            System.out.println("Obteniendo courseAndStudent: "+index.getCourse() + "Comparando Curso: "+trueCourse.getName());
            if (index.getCourse().equals(trueCourse)) {
                filteredList.addElement(index);
            }
        }
        System.out.println(filteredList);
        return filteredList;
    }
    
    public DefaultListModel<Teacher> filterTeachersBy(Object department) {
        
        Department trueDepartment = findDepartment (department);
        
        DefaultListModel<Teacher> filteredList = new DefaultListModel<>();
        for (Teacher index : objectTeachers) {
            System.out.println("Obteniendo profesores: "+index.getName() + "Comparando departamento: "+trueDepartment.getName());
            if (index.getDepartment().equals(trueDepartment)) {
                filteredList.addElement(index);
            }
        }
        System.out.println(filteredList);
        return filteredList;
    }
    
    public DefaultListModel<Servicestaff> filterServicestaffBy(Object Section) {
        
        Section trueSection = findSection(Section);
        
        DefaultListModel<Servicestaff> filteredList = new DefaultListModel<>();
        for (Servicestaff index : objectServiceStaffs) {
            System.out.println("Obteniendo servicestaff: "+index.getName() + "\n"+index.getSection() + "Comparando sección: "+trueSection.getName());
            if (index.getSection().equals(trueSection)) {
                filteredList.addElement(index);
            }
            else{
                System.out.println("no son iguales");
            }
        }
        System.out.println(filteredList);
        return filteredList;
    }
    
    public DefaultListModel<CourseAndStudent> filterCourseAndStudentByCourse(Object courseAndStudent) {
        
        CourseAndStudent truecourseAndStudent = findCourseAndStudent(courseAndStudent);
        
        DefaultListModel<CourseAndStudent> filteredList = new DefaultListModel<>();
        for (CourseAndStudent index : objectCoursesAndStudents) {
            System.out.println("Obteniendo relación: "+index.getCourse() + "Comparando Relación: "+truecourseAndStudent.getCourse());
            if (index.getCourse().equals(truecourseAndStudent.getCourse())) {
                filteredList.addElement(index);
            }
        }
        System.out.println(filteredList);
        return filteredList;
    }
    
    public DefaultListModel<CourseAndStudent> filterCourseAndStudentByStudent(Object courseAndStudent) {
        
        CourseAndStudent truecourseAndStudent = findCourseAndStudent(courseAndStudent);
        
        DefaultListModel<CourseAndStudent> filteredList = new DefaultListModel<>();
        for (CourseAndStudent index : objectCoursesAndStudents) {
            System.out.println("Obteniendo relación: "+index.getStudent() + "Comparando Relación: "+truecourseAndStudent.getStudent());
            if (index.getStudent().equals(truecourseAndStudent.getStudent())) {
                filteredList.addElement(index);
            }
        }
        System.out.println(filteredList);
        return filteredList;
    }
    
    
    
    public void addSectionObject(Section section){
        objectSections.add(section);
    }
    
    public List<Section> getSectionsList() {
        return objectSections;
    }
    
    public void addCourseObject(Course course){
        objectCourses.add(course);
    }
    
    public List<Course> getCourseList() {
        return objectCourses;
    }
    
    
    public void addDepartmentObject(Department department){
        objectDepartments.add(department);
    }

    public List<Department> getDepartmentsList() {
        return objectDepartments;
    }
    
    public void addTeacherObject(Teacher teacher){
        objectTeachers.add(teacher);
    }
        
    public List<Teacher> getTeacherList() {
        return objectTeachers;
    }
    
    public void addServicestaffObject(Servicestaff servicestaff){
        objectServiceStaffs.add(servicestaff);
    }
    
    public List<Servicestaff> getServicestaffList() {
        return objectServiceStaffs;
    }
    
    public void addStudentObject(Student student){
        objectStudents.add(student);
    }
    
    public List<Student> getStudentList() {
        return objectStudents;
    }
    
    public void addCourseAndStudentObject(CourseAndStudent courseAndStudent){
        objectCoursesAndStudents.add(courseAndStudent);
    }
    
    public List<CourseAndStudent> getCourseAndStudentList() {
        return objectCoursesAndStudents;
    }
}
