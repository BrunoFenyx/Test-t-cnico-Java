/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testtecnico.igu.Provisional;

import com.mycompany.testtecnico.logic.CivilStatus;
import com.mycompany.testtecnico.logic.Course;
import com.mycompany.testtecnico.logic.CourseAndStudent;
import com.mycompany.testtecnico.logic.Department;
import com.mycompany.testtecnico.logic.Section;
import com.mycompany.testtecnico.logic.Servicestaff;
import com.mycompany.testtecnico.logic.Student;
import com.mycompany.testtecnico.logic.Teacher;
import java.util.ArrayList;
import java.util.List;

/**
 *

 */
public class PersistenceObjects {
    
    private List<Teacher> teacherList = new ArrayList<>();
    private List<Servicestaff> serviceStaffList = new ArrayList<>();
    private List<Student> studentList = new ArrayList<>();
    private List<CivilStatus> civilStatusList = new ArrayList<>();
    private List<Course> courseList = new ArrayList<>();
    private List<CourseAndStudent> courseAndStudentList = new ArrayList<>();
    private List<Department> departmentList = new ArrayList<>();
    private List<Section> sectionList = new ArrayList<>();

    private PersistenceIdObjects ids = new PersistenceIdObjects(1, 1, 1, 1, 1, 1, 1);

    // Constructor sin argumentos
    public PersistenceObjects() {
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    public List<Servicestaff> getServiceStaffList() {
        return serviceStaffList;
    }

    public void setServiceStaffList(List<Servicestaff> serviceStaffList) {
        this.serviceStaffList = serviceStaffList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<CivilStatus> getCivilStatusList() {
        return civilStatusList;
    }

    public void setCivilStatusList(List<CivilStatus> civilStatusList) {
        this.civilStatusList = civilStatusList;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public List<CourseAndStudent> getCourseAndStudentList() {
        return courseAndStudentList;
    }

    public void setCourseAndStudentList(List<CourseAndStudent> courseAndStudentList) {
        this.courseAndStudentList = courseAndStudentList;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public List<Section> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<Section> sectionList) {
        this.sectionList = sectionList;
    }

    public PersistenceIdObjects getIds() {
        return ids;
    }

    public void setIds(PersistenceIdObjects ids) {
        this.ids = ids;
    }
}
