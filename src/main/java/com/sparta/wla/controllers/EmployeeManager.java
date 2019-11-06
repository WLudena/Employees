package com.sparta.wla.controllers;

import com.sparta.wla.models.CSVReader;
import com.sparta.wla.models.EmployeeDAO;

import org.apache.log4j.*;

public class EmployeeManager{

    private CSVReader csvReader = new CSVReader();
    private EmployeeDAO employeeDAO = new EmployeeDAO();
    private static final String PATH = "resources/EmployeeRecordsLarge.csv";

    private Logger log = Logger.getLogger(EmployeeManager.class);


//    public void insertEmployeesToDatabase(){
//        csvReader.streamRecordsFromFile(PATH);
//        employeeDAO.insertEmployeeQuery(csvReader.getEmployeeMap());
//        System.out.println("DONE!");
//
//    }

}
