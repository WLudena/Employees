package com.sparta.wla.models;

import java.sql.*;
import java.util.Map;

import org.apache.log4j.Logger;


public class EmployeeDAO {

    private final String QUERY = "INSERT INTO employees VALUES(?,?,?,?,?,?,?,?,?,?)";

//    private DAO dao = new DAO();
//    private Logger log = Logger.getLogger(EmployeeDAO.class.getName());
//
//    public void insertEmployeeQuery(Map<Integer, Employee> employees) {
//        try (Connection connection = DriverManager.getConnection(dao.getURL())) {
//            PreparedStatement statement = connection.prepareStatement(QUERY);
//            employees.values().spliterator().forEachRemaining(employee -> insertEmployee(statement,employee));
//        } catch (SQLException ex) {
//            log.error(ex);
//        }
//    }
//
//    private void insertEmployee(PreparedStatement statement, Employee e) {
//        try {
//            statement.setInt(1, e.getEmpID());
//            statement.setString(2, e.getNamePrefix());
//            statement.setString(3, e.getFirstName());
//            statement.setString(4, Character.toString(e.getMiddleInitial()));
//            statement.setString(5, e.getLastName());
//            statement.setString(6, Character.toString(e.getGender()));
//            statement.setString(7, e.getEmail());
//            statement.setDate(8, Date.valueOf(e.getDateOfBirth()));
//            statement.setDate(9, Date.valueOf(e.getDateOfJoining()));
//            statement.setInt(10, e.getSalary());
//            try {
//                statement.executeUpdate();
//            } catch (SQLIntegrityConstraintViolationException ex) {
//                log.error(ex);
//            }
//        } catch (SQLException ex) {
//            log.error(ex);
//        }
//    }
}
