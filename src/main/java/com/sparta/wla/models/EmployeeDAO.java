package com.sparta.wla.models;

import java.sql.*;
import java.util.Map;

import org.apache.log4j.Logger;


public class EmployeeDAO {

    private final String QUERY = "INSERT INTO employees VALUES(?,?,?,?,?,?,?,?,?,?)";

    private DAO dao = new DAO();
    private Logger log = Logger.getLogger(EmployeeDAO.class);

    public void insertEmployeeQuery(Map<Integer, Employee> employees) {
        try (Connection connection = DriverManager.getConnection(dao.getURL())) {
            PreparedStatement statement = connection.prepareStatement(QUERY);
            employees.values().parallelStream().forEach(employee -> insertEmployee(statement,employee));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertEmployee(PreparedStatement statement, Employee employee) {
        try{
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
                log.error(ex);
            }
        }catch(SQLException e){
            log.error(e);
        }
    }
}
