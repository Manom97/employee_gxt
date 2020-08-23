package com.mySampleApplication.server;

import com.google.gwt.core.client.GWT;
import com.mySampleApplication.client.ModelClass.Employee;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mySampleApplication.client.InsertEmp;
import com.mySampleApplication.client.MySampleApplicationService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySampleApplicationServiceImpl extends RemoteServiceServlet implements MySampleApplicationService {

    private String dbName = "sampledb";
    private String dbUser = "root";
    private String dbpass = "";
    private String url = "jdbc:mysql://localhost:3306/"+ dbName;



    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs;




    public MySampleApplicationServiceImpl() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, dbUser, dbpass);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public InsertEmp insertEmp(String name, int contact, String address){

        InsertEmp emp= new InsertEmp();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, dbUser, dbpass);

            ps = conn.prepareStatement("INSERT INTO  employees(name, contact, address) VALUES (?,?,?)");

            ps.setString(1, name);
            ps.setInt(2, contact);
            ps.setString(3,address);
            ps.executeUpdate();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if (ps!=null){
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            }


        return emp;
    }




    public String getStatus(String msg) {
        String user = "";
        String query = "SELECT * FROM users ";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
             rs = stmt.executeQuery(query);


            while(rs.next()){
                user = rs.getString(1);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(user != null) {
            return user;
        }else
            return "Failed to connect";
    }

    @Override
    public List<Employee> getEmployees() {

        List<Employee> employees= new ArrayList<Employee>();

        String query = "SELECT * FROM employees ";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
             rs = stmt.executeQuery(query);


            while(rs.next()){
                employees.add(new Employee(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(employees != null) {

            GWT.log("Eployees details retieved");
            return employees;
        }else
            return null;
    }


 //   public Employee getEmployees(){

//        Employee emp = new Employee();
//
//        String query = "select * from employee";
//
//
//        try {
//            PreparedStatement ps = conn.prepareStatement(query);
//            ResultSet rs = ps.executeQuery();
//
//            List<Integer[]> id = new ArrayList<>();
//            List<String[]> name = new ArrayList<>();
//            List<Integer[]> contact = new ArrayList<>();
//
//            while (rs.next()){
//
//
//
//            }
//
//
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

      //  return null;
  //  }

}