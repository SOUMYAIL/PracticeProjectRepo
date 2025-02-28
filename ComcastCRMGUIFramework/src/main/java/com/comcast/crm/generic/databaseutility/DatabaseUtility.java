package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

/**
 * Utility class for handling database operations such as connection, executing
 * queries, and closing the connection.
 * 
 * Author: Soumya
 */
public class DatabaseUtility {

	private Connection conn;

	/**
	 * Establishes a database connection using provided credentials.
	 * 
	 * @param url      JDBC URL of the database
	 * @param username Database username
	 * @param password Database password
	 * @throws SQLException if a database access error occurs
	 */
	public void getDbConnection(String url, String username, String password) throws SQLException {
		try {
			Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Database connection established successfully.");
		} catch (SQLException e) {
			System.err.println("Failed to establish database connection: " + e.getMessage());
			throw e; // Re-throw the exception for better debugging
		}
	}

	/**
	 * Establishes a hardcoded connection to the local database.
	 * 
	 * @throws SQLException if a database access error occurs
	 */
	public void getDbConnectionHardcode() throws SQLException {
		try {
			Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
			System.out.println("Database connection established with hardcoded values.");
		} catch (SQLException e) {
			System.err.println("Failed to establish hardcoded database connection: " + e.getMessage());
			throw e;
		}
	}

	/**
	 * Closes the database connection.
	 * 
	 * @throws SQLException if a database access error occurs
	 */
	public void closeConnection() throws SQLException {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
				System.out.println("Database connection closed successfully.");
			}
		} catch (SQLException e) {
			System.err.println("Failed to close the database connection: " + e.getMessage());
			throw e;
		}
	}

	/**
	 * Executes a SELECT SQL query and returns the result set.
	 * 
	 * @param query SQL SELECT query
	 * @return ResultSet containing the data produced by the query
	 * @throws SQLException if a database access error occurs
	 */
	public ResultSet executeSelectQuery(String query) throws SQLException {
		ResultSet resultSet = null;
		try {
			Statement statement = conn.createStatement();
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			System.err.println("Failed to execute SELECT query: " + e.getMessage());
			throw e;
		}
		return resultSet;
	}

	/**
	 * Executes an INSERT, UPDATE, or DELETE SQL query.
	 * 
	 * @param query SQL query to execute
	 * @return number of affected rows
	 * @throws SQLException if a database access error occurs
	 */
	public int executeNonSelectQuery(String query) throws SQLException {
		int result = 0;
		try {
			Statement statement = conn.createStatement();
			result = statement.executeUpdate(query);
		} catch (SQLException e) {
			System.err.println("Failed to execute non-SELECT query: " + e.getMessage());
			throw e;
		}
		return result;
	}
}
