package com.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StudentCRUD_Driver
{
    private static Connection connection;
    private static Scanner    scanner;
    private static Statement  statement;
    private static String     dbName = "student_records";
    private static String     dbLink = "jdbc:mysql://localhost:3306/" + dbName;
    private static String     dbUser = "root";
    private static String     dbPass = "root";

    public static void main(String[] args) throws Exception
    {
        connection = DriverManager.getConnection(dbLink,dbUser,dbPass);
        scanner    = new Scanner(System.in);
        statement  = connection.createStatement();

        int choice = -1;
        while(choice != 0)
        {
            System.out.println("\n\nEnter choice.\n Enter 0 to exit ");
            System.out.println("1. Insert");
            System.out.println("2. Fetch All");
            choice = scanner.nextInt();

            switch(choice)
            {
                case 1 :
                    insertRecord();
                    break;
                case 2 :
                    fetchRecords();
                    break;

                default :
                    break;
            }
        }
    }

    private static void fetchRecords() throws Exception
    {
        ResultSet result = statement.executeQuery("SELECT * FROM `student_records`.`student_data` ;");
        while(result.next())
        {
            System.out.println("NAME:" + result.getString(1));
            System.out.println("DOB:" + result.getString(2));
            System.out.println("DOJ:" + result.getString(3));
        }
    }

    private static void insertRecord() throws Exception
    {
        String studentName;
        String studentDOB;
        String studentDOJ;

        scanner.nextLine();
        System.out.println("Insert Student Name");
        studentName = scanner.nextLine();
        System.out.println("Insert Student DOB");
        studentDOB = scanner.nextLine();
        System.out.println("Insert Student DOJ");
        studentDOJ = scanner.nextLine();

        int result = statement
                .executeUpdate("INSERT INTO `student_records`.`student_data`(`student_name`,`student_dob`, `student_doj`) VALUES ('"
                   + studentName + "', '" + studentDOB + "', '" + studentDOJ + "');");
        if(result != 0)
        {
            System.out.println("Inserted");
        }
    }
}