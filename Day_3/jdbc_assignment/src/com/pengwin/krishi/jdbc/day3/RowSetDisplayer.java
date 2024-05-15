package com.pengwin.krishi.jdbc.day3;

import java.sql.*;
import javax.sql.rowset.*;


public class RowSetDisplayer {
    public void displayEmpData(int typeOfRowSet) throws SQLException {
        DataFetcher dataFetcher = new DataFetcher("northwind");
        dataFetcher.connect();
        if (typeOfRowSet == 1) {
            CachedRowSet crs = dataFetcher.executeInCached();
            System.out.println("EmployeeID         |  LastName     |  FirstName  |  Title            |  BirthDate");
            System.out.println("---------------------------------------------------------------------------------");
            // Printing the result
            while (crs.next()) {
                System.out.println(crs.getInt("EmployeeID") + "\t\t" + crs.getString("LastName") + "\t\t" + crs.getString("FirstName") + "\t\t" + crs.getString("Title") + "\t\t" + crs.getDate("BirthDate"));
            }
        } else if (typeOfRowSet == 2) {
            WebRowSet wrs = dataFetcher.executeInXML();
            System.out.println("EmployeeID         |  LastName     |  FirstName  |  Title            |  BirthDate");
            System.out.println("---------------------------------------------------------------------------------");
            // Printing the result
            while (wrs.next()) {
                System.out.println(wrs.getInt("EmployeeID") + "\t\t" + wrs.getString("LastName") + "\t\t" + wrs.getString("FirstName") + "\t\t" + wrs.getString("Title") + "\t\t" + wrs.getDate("BirthDate"));
            }
        }
    	 else if (typeOfRowSet == 3) {
    		 JdbcRowSet jrs = dataFetcher.executeInJdbc();
    		 System.out.println("EmployeeID         |  LastName     |  FirstName  |  Title            |  BirthDate");
             System.out.println("---------------------------------------------------------------------------------");
             // Printing the result
             while (jrs.next()) {
                 System.out.println(jrs.getInt("EmployeeID") + "\t\t" + jrs.getString("LastName") + "\t\t" + jrs.getString("FirstName") + "\t\t" + jrs.getString("Title") + "\t\t" + jrs.getDate("BirthDate"));
             }
    	 }
	        
        else {
            System.out.println("ERROR: Invalid type of RowSet");
        }
    }

    public static void main(String[] args) throws SQLException {
        RowSetDisplayer displayer = new RowSetDisplayer();
        displayer.displayEmpData(1);
        displayer.displayEmpData(2);
        displayer.displayEmpData(3);
        displayer.displayEmpData(0);
    }
}