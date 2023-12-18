package com.mycompany.testtecnico.logic;

import com.mycompany.testtecnico.logic.Department;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-12-18T09:17:55", comments="EclipseLink-2.7.8.v20201217-rNA")
@StaticMetamodel(Teacher.class)
public class Teacher_ extends Staff_ {

    public static volatile SingularAttribute<Teacher, Integer> id_teacher;
    public static volatile SingularAttribute<Teacher, Department> department;
    public static volatile SingularAttribute<Teacher, Integer> id_staff;

}