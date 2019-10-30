package com.sparta.wla.models;

import java.sql.*;
import org.apache.log4j.Logger;

public class DAO {

    private final String QUERY = "INSERT INTO employees VALUES(?,?,?,?,?,?,?,?,?,?)";
    private final String URL = "jdbc:mysql://localhost/employees?user=root&password=Relojes1!";

    private Logger log = Logger.getLogger(DAO.class.getName());

    public void runQuery(String id){
        try(Connection connection = DriverManager.getConnection(URL)){
            PreparedStatement statement = connection.prepareStatement(QUERY);
            statement.setString(1,id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                System.out.println("Actor name: " + resultSet.getString(1) + " " + resultSet.getString("last_name"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public String getURL(){
        return URL;
    }

    public boolean isConnected(){
        try(Connection connection = DriverManager.getConnection(URL)){
            if(connection.isValid(1000)){
                return true;
            }
        }catch(SQLException e){
            log.error(e);
        }
        return false;
    }
}
