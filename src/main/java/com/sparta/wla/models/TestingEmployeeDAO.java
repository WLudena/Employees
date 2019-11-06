package com.sparta.wla.models;

import java.sql.*;
import java.util.Arrays;
import java.util.Map;

public class TestingEmployeeDAO {

    private final String URL = "jdbc:mysql://localhost/employees?user=root&password=Relojes1!";
    private final String INSERT_QUERY = "INSERT INTO employees VALUES(?,?,?,?,?,?,?,?,?,?)";

    public void addEmpsToDB(Map<Integer, Employee> employees){

        Employee[] arrayOfEmps = employees.values().toArray(new Employee[employees.size()]);
        int employeeCount = arrayOfEmps.length;
        Thread[] threadsArray = new Thread[150];
        int threadCount  = threadsArray.length;
//        int factor = employeeCount/threadCount;
        for(int i = 0; i < threadsArray.length; i++){
            final int j = i;
            Runnable runnable;
            if((employeeCount*(i+1))/threadCount>employeeCount){
                runnable = () -> insert(Arrays.copyOfRange(arrayOfEmps,(employeeCount*j)/threadCount,arrayOfEmps.length));
            }else{
                runnable = () -> insert(Arrays.copyOfRange(arrayOfEmps,(employeeCount*j)/threadCount,(employeeCount*(j+1))/threadCount));
            }
            threadsArray[i] = new Thread(runnable);
            threadsArray[i].start();
        }
    }

    private void insert(Employee[] employees){

        try (Connection connection = DriverManager.getConnection(URL)) {
            for(Employee employee : employees) {
                PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
                statement.setInt(1, employee.getEmpID());
                statement.setString(2, employee.getNamePrefix());
                statement.setString(3, employee.getFirstName());
                statement.setString(4, Character.toString(employee.getMiddleInitial()));
                statement.setString(5, employee.getLastName());
                statement.setString(6, Character.toString(employee.getGender()));
                statement.setString(7, employee.getEmail());
                statement.setDate(8, Date.valueOf(employee.getDateOfBirth()));
                statement.setDate(9, Date.valueOf(employee.getDateOfJoining()));
                statement.setInt(10, employee.getSalary());
                try {
                    statement.executeUpdate();
                } catch (SQLIntegrityConstraintViolationException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
