package com.sparta.wla.models;

import org.apache.log4j.Logger;

import java.sql.*;

public class DAO {

    private final String URL = "jdbc:mysql://localhost/employees?user=root&password=Relojes1!";
    private final String INSERT_QUERY = "INSERT INTO employees VALUES(?,?,?,?,?,?,?,?,?,?)";
    private final String TRUNCATE_QUERY = "TRUNCATE TABLE employees";

    private static final String PATH = "resources/EmployeeRecordsLarge.csv";

    private Employee[] employees;

    private CSVReader csvReader = new CSVReader();

    private volatile Integer index = 0;

    private Logger log = Logger.getLogger(DAO.class.getName());

    public String getURL() {
        return URL;
    }

    Runnable runnableObject = () -> insertEmployeeThreaded(employees);

    private void insertEmployeeThreaded(Employee[] employees) {
        int i = 0;
        try (Connection connection = DriverManager.getConnection(URL)) {
            synchronized (this) {
                i = index;
                index++;
            }
            while (i < employees.length) {
                PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
                statement.setInt(1, employees[i].getEmpID());
                statement.setString(2, employees[i].getNamePrefix());
                statement.setString(3, employees[i].getFirstName());
                statement.setString(4, Character.toString(employees[i].getMiddleInitial()));
                statement.setString(5, employees[i].getLastName());
                statement.setString(6, Character.toString(employees[i].getGender()));
                statement.setString(7, employees[i].getEmail());
                statement.setDate(8, Date.valueOf(employees[i].getDateOfBirth()));
                statement.setDate(9, Date.valueOf(employees[i].getDateOfJoining()));
                statement.setInt(10, employees[i].getSalary());
                try {
                    statement.executeUpdate();
                } catch (SQLIntegrityConstraintViolationException ex) {
                    log.error(ex);
                }
                synchronized (this) {
                    i = index;
                    index++;
                }
            }
        } catch (SQLException e) {
            log.error(e);
        }
    }

    public void addEmployeesToDBThreaded() {
        truncateTableQuery();
        csvReader.streamRecordsFromFile(PATH);
        employees = csvReader.getEmployeeMap().values().toArray(new Employee[csvReader.getEmployeeMap().size()]);
        Thread[] threads = new Thread[150];
        for (int i = 0; i < 150; i++) {
            Thread newThread = new Thread(runnableObject);
            threads[i] = newThread;
            newThread.start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                log.error(e);
            }
        }
    }

    public void truncateTableQuery() {
        try (Connection connection = DriverManager.getConnection(getURL())) {
            PreparedStatement statement = connection.prepareStatement(TRUNCATE_QUERY);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e);
        }
    }

    public boolean isConnected() {
        try (Connection connection = DriverManager.getConnection(URL)) {
            if (connection.isValid(1000)) {
                return true;
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return false;
    }
}
