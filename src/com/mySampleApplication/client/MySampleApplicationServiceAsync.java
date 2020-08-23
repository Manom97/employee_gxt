package com.mySampleApplication.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mySampleApplication.client.ModelClass.Employee;

import java.util.List;

public interface MySampleApplicationServiceAsync {
    void getStatus(String msg, AsyncCallback<String> async);

    void insertEmp(String name, int contact, String address, AsyncCallback<InsertEmp> async);

    void getEmployees(AsyncCallback<List<Employee>> async);


//    void getEmployees(AsyncCallback<Employee> callback);
}
