package com.mycompany.testtecnico.logic;

import com.mycompany.testtecnico.logic.CourseAndStudent;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-12-15T14:15:55", comments="EclipseLink-2.7.8.v20201217-rNA")
@StaticMetamodel(Course.class)
public class Course_ { 

    public static volatile SingularAttribute<Course, String> name;
    public static volatile SingularAttribute<Course, Integer> id;
    public static volatile ListAttribute<Course, CourseAndStudent> relation;

}