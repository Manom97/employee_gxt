package com.mySampleApplication.client.ModelClass;

import com.google.gwt.editor.client.Editor.Path;
import com.mySampleApplication.client.ModelClass.Employee;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;


public  interface EmployeeProperties extends PropertyAccess<Employee> {
    @Path("id")
    ModelKeyProvider<Employee> key();

    @Path("name")
    LabelProvider<Employee> nameLabel();


    ValueProvider<Employee, String> name();
    ValueProvider<Employee, String> contact();
    ValueProvider<Employee, String> address();

}
