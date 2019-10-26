package com.sparta.wla.controllers;

import com.sparta.wla.models.Employee;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManager{

    private static final String PATH = "resources/testFile.csv";
    private String[] employee;
    private List<Employee> employeeDetailsList = new ArrayList<>();
    private String[] csvHeaders;
    private Employee employeeObject;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");



    public void employeeReader() throws FileNotFoundException {
        BufferedReader csvReader = new BufferedReader(new FileReader(PATH));
        String row;
        int counter = 1;
        try{
            while((row = csvReader.readLine()) != null){
                if(counter == 1){
                    csvHeaders = row.split(",");
                   counter++;
                }
                else{
                    employee = row.split(",");
                    addEmployeeToList(employee);
                }
            }
        }catch (IOException | ParseException e){
            e.printStackTrace();
        }
    }

    public List<Employee> getEmployeeList(){
        return employeeDetailsList;
    }

    public void addEmployeeToList(String[] employee) throws ParseException {
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
            employeeDetailsList.add(employeeObject);
    }
}
