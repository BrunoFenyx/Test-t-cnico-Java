package com.mycompany.testtecnico.logic;

import com.mycompany.testtecnico.logic.Course;
import com.mycompany.testtecnico.logic.Student;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-12-18T09:17:55", comments="EclipseLink-2.7.8.v20201217-rNA")
@StaticMetamodel(CourseAndStudent.class)
public class CourseAndStudent_ { 

    public static volatile SingularAttribute<CourseAndStudent, Student> student;
    public static volatile SingularAttribute<CourseAndStudent, Course> course;
    public static volatile SingularAttribute<CourseAndStudent, Integer> id;

}