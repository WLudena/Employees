package com.sparta.wla.models;

import org.junit.*;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;

import static org.junit.Assert.*;

public class EmployeeTest {

    private Employee employee;

    private static final String PATH = "resources/EmployeeRecordsLarge.csv";
    private  CSVReader csvReader;
    private DecimalFormat decimalFormat;

    @Before
    public void setUp() {
        csvReader = new CSVReader();
       decimalFormat =  new DecimalFormat("###,###.###");
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
        csvReader.streamRecordsFromFile(PATH);
        for(Employee e : csvReader.getEmployeeMap().values()){
            assertNotNull(e);
        }
    }

    @Test
    public void testDuplicatesAreStored() throws FileNotFoundException {
        csvReader.streamRecordsFromFile(PATH);
        System.out.println(csvReader.getEmployeeMap().size());
        System.out.println(csvReader.getDuplicateEmployeesMap().size());
        assertNotNull(csvReader.getDuplicateEmployeesMap());
    }

    @Test
    public void testTimingCSVReader() throws FileNotFoundException {
        long startTime = System.nanoTime();
        csvReader.streamRecordsFromFile(PATH);
        long endTime = System.nanoTime();
        System.out.println(decimalFormat.format(endTime-startTime));
    }
}