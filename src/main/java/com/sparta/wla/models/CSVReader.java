package com.sparta.wla.models;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


public class CSVReader {

    private Map<Integer, Employee> employeesDetails;
    private Map<Integer, Employee> duplicateEmployees;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

    private Logger log = Logger.getLogger(CSVReader.class);


    public void streamRecordsFromFile(String path) {
        employeesDetails = new HashMap<>();
        duplicateEmployees = new HashMap<>();
        try(BufferedReader csvReader = new BufferedReader(new FileReader(path))){
            csvReader.readLine(); // Gets rid of the first line containing headers
            csvReader.lines()
                    .forEach(line -> createEmployeeObject(line));
        }catch(IOException e){
            log.error(e);
        }
    }

    public Map<Integer, Employee> getEmployeeMap() {
        return employeesDetails;
    }

    public Map<Integer, Employee> getDuplicateEmployeesMap() {
        return duplicateEmployees;
    }

    private void addEmployeeToMap(Employee employee){
        if(employeesDetails.containsKey(employee.getEmpID())){
            duplicateEmployees.put(employee.getEmpID(), employee);
        }else{
            employeesDetails.put(employee.getEmpID(), employee);
        }
    }

    private void createEmployeeObject(String line){
        Employee employeeObject = new Employee();
        String[] employeeDetails = line.split(",");
        employeeObject.setEmpID(Integer.parseInt(employeeDetails[0]));
        employeeObject.setNamePrefix(employeeDetails[1]);
        employeeObject.setFirstName(employeeDetails[2]);
        employeeObject.setMiddleInitial(employeeDetails[3].charAt(0));
        employeeObject.setLastName(employeeDetails[4]);
        employeeObject.setGender(employeeDetails[5].charAt(0));
        employeeObject.setEmail(employeeDetails[6]);
        employeeObject.setDateOfBirth(LocalDate.parse(employeeDetails[7], formatter));
        employeeObject.setDateOfJoining(LocalDate.parse(employeeDetails[8], formatter));
        employeeObject.setSalary(Integer.parseInt(employeeDetails[9]));

        addEmployeeToMap(employeeObject);
    }
}
