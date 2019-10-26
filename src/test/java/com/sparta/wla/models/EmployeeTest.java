package com.sparta.wla.models;

import com.sparta.wla.controllers.EmployeeManager;
import org.junit.*;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class EmployeeTest {

    private EmployeeManager employeeManager;
    private Employee employee;
    private static final String PATH = "resources/testFile.csv";

    @Before
    public void setUp() throws FileNotFoundException {
        employeeManager = new EmployeeManager();
        employeeManager.employeeReader();
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
    public void testFileExists(){
        File testFile = new File(PATH);
        assertTrue(testFile.isFile());
    }

    @Test
    public void testObjectIsPopulated() throws FileNotFoundException {
        for(Employee e : employeeManager.getEmployeeList()){
            System.out.println(e.getFirstName());
        };
        assertNotNull(employeeManager.getEmployeeList());
    }

    @Test
    public void testDates(){
        for(Employee e : employeeManager.getEmployeeList()){
            System.out.println(e.getDateOfBirth());
        }
    }

}