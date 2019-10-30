package com.sparta.wla.models;

import com.sparta.wla.models.DAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DAOTest {

    private DAO dao;

    @Before
    public void setUp(){
        dao = new DAO();
    }

    @After
    public void tearDown(){

    }

    @Test
    public void testIsConnected(){
        assertTrue(dao.isConnected());
    }
}
