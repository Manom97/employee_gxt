package com.mySampleApplication.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MySampleApplicationServiceAsync {
    void getStatus(String msg, AsyncCallback<String> async);

    void insertEmp(int id, String name, int contact, String address, AsyncCallback<InsertEmp> async);


//    void getEmployees(AsyncCallback<Employee> callback);
}
