package com.mycompany.testtecnico.logic;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-12-18T09:17:55", comments="EclipseLink-2.7.8.v20201217-rNA")
@StaticMetamodel(Staff.class)
public class Staff_ extends Person_ {

    public static volatile SingularAttribute<Staff, LocalDate> date;
    public static volatile SingularAttribute<Staff, String> dispatchNumber;

}