package com.mySampleApplication.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.mySampleApplication.client.ModelClass.Employee;

import java.util.List;

@RemoteServiceRelativePath("MySampleApplicationService")
public interface MySampleApplicationService extends RemoteService {

    String getStatus(String msg);
    InsertEmp insertEmp(String name, int contact,String address);
    List<Employee> getEmployees();


    public static class App {
        private static MySampleApplicationServiceAsync ourInstance = GWT.create(MySampleApplicationService.class);

        public static synchronized MySampleApplicationServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
