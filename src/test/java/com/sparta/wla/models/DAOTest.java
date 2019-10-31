package com.sparta.wla.models;

import com.sparta.wla.models.DAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class DAOTest {

    private DAO dao;
    private static final String PATH = "resources/testFile.csv";
    private static final String WRONG_PATH = "resources/test.csv";

    @Before
    public void setUp(){
        dao = new DAO();
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
        assertTrue(dao.isConnected());
    }
}
