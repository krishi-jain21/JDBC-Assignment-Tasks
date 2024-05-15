package com.pengwin.krishi.jdbc.day3;
	
import javax.sql.rowset.CachedRowSet;	
import java.sql.*;	
	
public class FetcherAndAdder {	
    private String command;	
    private String dataSourceName;	
    private String url;	
    private String username;	
    private String password;	
	
    public FetcherAndAdder(String command, String dataSourceName) {	
        this.command = command;	
        this.dataSourceName = dataSourceName;	
        this.url = "jdbc:mysql://localhost:3306/" + dataSourceName;	
        this.username = "root";	
        this.password = "root";	
    }	
	
    public void connect() {	
        try {	
            Class.forName("com.mysql.cj.jdbc.Driver");	
        } catch (ClassNotFoundException e) {	
            e.printStackTrace();	
        }	
    }	
	
    public CachedRowSet fetchRecords() {	
        connect();	
        try (Connection connection = DriverManager.getConnection(url, username, password);	
             PreparedStatement statement = connection.prepareStatement(command);	
             ResultSet resultSet = statement.executeQuery()) {	
	
            CachedRowSet cachedRowSet = javax.sql.rowset.RowSetProvider.newFactory().createCachedRowSet();	
            cachedRowSet.populate(resultSet);	
            return cachedRowSet;	
        } catch (SQLException e) {	
            e.printStackTrace();	
            return null;	
        }	
    }	
	
    public void addRecord(int employeeID, String lastName, String firstName, String title, Date birthDate) {	
        connect();	
        try (Connection connection = DriverManager.getConnection(url, username, password);	
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Employees (EmployeeID, LastName, FirstName, Title, BirthDate) VALUES (?, ?, ?, ?, ?)")) {	
            statement.setInt(1, employeeID);	
            statement.setString(2, lastName);	
            statement.setString(3, firstName);	
            statement.setString(4, title);	
            statement.setDate(5, birthDate);	
            statement.executeUpdate();	
            System.out.println("Record added successfully.");	
        } catch (SQLException e) {	
            e.printStackTrace();	
        }	
    }	
}	
