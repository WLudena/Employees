package com.sparta.wla.models;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.Key;
import java.security.spec.ECField;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVReader {

    private static final String PATH = "resources/EmployeeRecordsLarge.csv";
    private String[] employee;
    private List<String> testList;

    private Map<Integer, Employee> employeesDetails;
    private Map<Integer, Employee> duplicateEmployees;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

    private Logger log = Logger.getLogger(CSVReader.class);


    public void streamRecordsFromFile(String path) throws FileNotFoundException {
        employeesDetails = new HashMap<>();
        duplicateEmployees = new HashMap<>();
        try(BufferedReader csvReader = new BufferedReader(new FileReader(path))){
            csvReader.readLine(); // Gets rid of the first line containing headers
            csvReader.lines()
                    .forEach(line -> createEmployeeObject(line));
        }catch(IOException e){
            e.printStackTrace();
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
