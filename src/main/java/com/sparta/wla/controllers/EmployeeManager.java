package com.sparta.wla.controllers;

import com.sparta.wla.models.CSVReader;
import com.sparta.wla.models.EmployeeDAO;

import java.io.FileNotFoundException;

import org.apache.log4j.*;

public class EmployeeManager{

    private CSVReader csvReader = new CSVReader();
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    private Logger log = Logger.getLogger(EmployeeManager.class);



    public void insertEmployeesToDatabase(String path){
        try{
            csvReader.streamRecordsFromFile(path);
            employeeDAO.insertEmployeeQuery(csvReader.getEmployeeMap());
            System.out.println("DONE!");
        }catch(FileNotFoundException e){
            log.error(e);
        }

    }

}
