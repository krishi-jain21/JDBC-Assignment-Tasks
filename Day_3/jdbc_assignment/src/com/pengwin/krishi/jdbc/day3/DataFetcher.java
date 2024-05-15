package com.pengwin.krishi.jdbc.day3;

import java.sql.*;
import javax.sql.rowset.*;
import javax.sql.rowset.JdbcRowSet;

import com.pengwin.krishi.jdbc.day1.PropertyUtil;


public class DataFetcher {
    private String command = "SELECT EmployeeID,LastName,FirstName,Title,BirthDate FROM Employees";
    private String dataSourceName;
    private Connection conn;

    public DataFetcher(String dataSourceName) {
    	this.dataSourceName = dataSourceName;
    }
    public void connect() {
    	String url = PropertyUtil.getProperty("url2");		
        String username = PropertyUtil.getProperty("username");		
        String password = PropertyUtil.getProperty("password");	
        
        try {		
            Class.forName("com.mysql.cj.jdbc.Driver" );		
            conn = DriverManager.getConnection(url+dataSourceName, username, password);		
        } catch (Exception e) {		
            e.printStackTrace();		
        }		
    }

    public CachedRowSet executeInCached() throws SQLException {
    	CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(command);
            crs.populate(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return crs;
    }

    public WebRowSet executeInXML() throws SQLException {
    	WebRowSet wrs = RowSetProvider.newFactory().createWebRowSet();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(command);
            wrs.populate(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return wrs;
    }
    
    public JdbcRowSet executeInJdbc() throws SQLException {
        RowSetFactory factory = RowSetProvider.newFactory();
        JdbcRowSet jrs = factory.createJdbcRowSet();
        String url = PropertyUtil.getProperty("url");		
        String username = PropertyUtil.getProperty("username");		
        String password = PropertyUtil.getProperty("password");	
        try {
            jrs.setUrl(url);
            jrs.setUsername(username);
            jrs.setPassword(password);
            jrs.setCommand(command);
            jrs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return jrs;
    }
}

