package com.comcast.crm.orgtest;

import java.sql.SQLException;

import com.comcast.crm.generic.databaseutility.DatabaseUtility;

public class ExecuteNonSelectQueryWithHardcodeTest {
	public static void main(String[] args) throws SQLException {
		DatabaseUtility dblib = new DatabaseUtility();
		dblib.getDbConnectionHardcode();
		int data1 = dblib.executeNonSelectQuery(
				" insert into project values('TY_PROJ_4005','Deepak','04/27/2025','FB','on Going',100);");
		System.out.println(data1);
		dblib.closeConnection();

	}

}
