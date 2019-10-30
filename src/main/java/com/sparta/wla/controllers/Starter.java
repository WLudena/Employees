package com.sparta.wla.controllers;

/**
 * Hello world!
 *
 */
public class Starter
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        EmployeeManager employeeManager = new EmployeeManager();
        employeeManager.insertEmployeesToDatabase();
    }
}
