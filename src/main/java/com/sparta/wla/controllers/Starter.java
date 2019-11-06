package com.sparta.wla.controllers;

import com.sparta.wla.models.CSVReader;
import com.sparta.wla.models.DAO;
import com.sparta.wla.models.TestingEmployeeDAO;

/**
 * Hello world!
 *
 */
public class Starter
{

    private static final String PATH = "resources/EmployeeRecords.csv";

    public static void main( String[] args )
    {
//        long start = System.nanoTime();
//        new DAO().addEmployeesToDBThreaded();
//        long end = System.nanoTime();
//        System.out.println(end-start);

        CSVReader csvReader = new CSVReader();
        csvReader.streamRecordsFromFile(PATH);

        new TestingEmployeeDAO().addEmpsToDB(csvReader.getEmployeeMap());

    }
}
