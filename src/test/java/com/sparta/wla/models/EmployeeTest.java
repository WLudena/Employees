package com.sparta.wla.models;

import org.junit.*;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class EmployeeTest {

    private Employee employee;

    private static final String PATH = "resources/EmployeeRecords.csv";

    @Before
    public void setUp() {

    }

    @After
    public void tearDown(){

    }

    @Test
    public void testEmployeeObjectCreated(){
        employee = new Employee();
        assertNotNull(employee);
    }

    @Test
    public void testEmployeeMapIsPopulated() throws FileNotFoundException {
        CSVReader csvReader = new CSVReader();
        csvReader.employeeReader();
        for(Employee e : csvReader.getEmployeeMap().values()){
            assertNotNull(e);
        }
    }

    @Test
    public void testDuplicatesAreStored() throws FileNotFoundException {
        CSVReader csvReader = new CSVReader();
        csvReader.employeeReader();
        System.out.println(csvReader.getEmployeeMap().size());
        System.out.println(csvReader.getDuplicateEmployeesMap().size());
        assertNotNull(csvReader.getDuplicateEmployeesMap());
    }
}