package com.sparta.wla.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class DAOTest {

    private static final String PATH = "resources/testFile.csv";
    private static final String WRONG_PATH = "resources/test.csv";

    @Before
    public void setUp(){

    }

    @After
    public void tearDown(){

    }

    @Test
    public void testFileExists(){
        File testFile = new File(PATH);
        assertTrue(testFile.isFile());
    }

    @Test
    public void testFileDoesNotExist(){
        File testFile = new File(WRONG_PATH);
        assertFalse(testFile.isFile());
    }

    @Test
    public void testIsConnected(){
        DAO dao = new DAO();
        assertTrue(dao.isConnected());
    }

    @Test
    public void testTime(){
        long start = System.nanoTime();
        new DAO().addEmployeesToDBThreaded();
        long end = System.nanoTime();
        System.out.println(end-start);
    }
}
