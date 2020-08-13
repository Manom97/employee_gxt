package com.mySampleApplication.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mySampleApplication.client.InsertEmp;
import com.mySampleApplication.client.MySampleApplicationService;

import java.sql.*;

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




    public InsertEmp insertEmp(int id, String name, int contact, String address){

        InsertEmp emp= new InsertEmp();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, dbUser, dbpass);

            ps = conn.prepareStatement("INSERT INTO  employees VALUES (?,?,?,?)");
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, contact);
            ps.setString(4,address);
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
            if(conn!=null){
                try {
                    conn.close();
                }catch (SQLException se){
                    se.printStackTrace();
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