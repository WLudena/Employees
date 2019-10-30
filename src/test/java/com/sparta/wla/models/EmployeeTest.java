package com.sparta.wla.models;

import com.sparta.wla.controllers.EmployeeManager;
import org.junit.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class EmployeeTest {

    private EmployeeManager employeeManager;
    private Employee employee;

    private static final String PATH = "resources/testFile.csv";

//    @Before
//    public void setUp() throws FileNotFoundException {
//
//    }
//
//    @After
//    public void tearDown(){
//
//    }
//
//    @Test
//    public void testEmployeeObjectCreated(){
//        employee = new Employee();
//        assertNotNull(employee);
//    }
//
//    @Test
//    public void testFileExists(){
//        File testFile = new File(PATH);
//        assertTrue(testFile.isFile());
//    }
//
//    @Test
//    public void testObjectsArePopulated() {
//        for(Employee e : employeeManager.getEmployeeMap().values()){
//            assertNotNull(e);
//        }
//    }
//
//    @Test
//    public void testPopulatingDatabase(){
//        employeeManager.insertEmployeesToDatabase();
//    }
}