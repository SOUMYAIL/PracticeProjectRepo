package com.comcast.crm.orgtest;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.comcast.crm.generic.databaseutility.DatabaseUtility;

public class ExecuteSelectQueryWithHardcoded {
	public static void main(String[] args) throws SQLException {
		DatabaseUtility dblib = new DatabaseUtility();
		dblib.getDbConnectionHardcode();
		ResultSet resultSet = dblib.executeSelectQuery("select * from project");
		resultSet.next();

		while (resultSet.next()) {
			System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t"
					+ resultSet.getString(3) + "\t" + resultSet.getString(4) + "\t" + resultSet.getString(5));

		}
	}


}
