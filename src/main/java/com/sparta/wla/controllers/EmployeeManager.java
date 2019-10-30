package com.sparta.wla.controllers;

import com.sparta.wla.models.CSVReader;
import com.sparta.wla.models.EmployeeDAO;

import java.io.FileNotFoundException;

public class EmployeeManager{

    private CSVReader csvReader = new CSVReader();
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    public void insertEmployeesToDatabase(){
        try{
            csvReader.employeeReader();
            employeeDAO.insertEmployeeQuery(csvReader.getEmployeeMap());
            System.out.println("DONE!");
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }

}
