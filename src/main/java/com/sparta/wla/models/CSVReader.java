package com.sparta.wla.models;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class CSVReader {

    private static final String PATH = "resources/EmployeeRecords.csv";
    private String[] employee;
    private Map<Integer, Employee> employeeDetailsList = new HashMap<>();
    private Employee employeeObject;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

    private Logger log = Logger.getLogger(CSVReader.class.getName());


    public void employeeReader() throws FileNotFoundException {
        BufferedReader csvReader = new BufferedReader(new FileReader(PATH));
        String row;
        int counter = 1;
        try{
            while((row = csvReader.readLine()) != null){
                if(counter == 1){
                    counter++;
                }
                else{
                    employee = row.split(",");
                    addEmployeeToMap(employee);
                }
            }
        }catch (IOException | ParseException e){
            log.error(e);
        }
    }

    public void addEmployeeToMap(String[] employee) throws ParseException {
        employeeObject = new Employee();
        employeeObject.setEmpID(Integer.parseInt(employee[0]));
        employeeObject.setNamePrefix(employee[1]);
        employeeObject.setFirstName(employee[2]);
        employeeObject.setMiddleInitial(employee[3].charAt(0));
        employeeObject.setLastName(employee[4]);
        employeeObject.setGender(employee[5].charAt(0));
        employeeObject.setEmail(employee[6]);
        employeeObject.setDateOfBirth(LocalDate.parse(employee[7],formatter));
        employeeObject.setDateOfJoining(LocalDate.parse(employee[8],formatter));
        employeeObject.setSalary(Integer.parseInt(employee[9]));
        employeeDetailsList.put(employeeObject.getEmpID(),employeeObject);
    }

    public Map<Integer, Employee> getEmployeeMap(){
        return employeeDetailsList;
    }
}
