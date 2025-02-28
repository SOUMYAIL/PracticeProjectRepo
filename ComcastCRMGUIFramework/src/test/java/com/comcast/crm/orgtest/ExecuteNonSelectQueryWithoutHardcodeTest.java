package com.comcast.crm.orgtest;

import java.sql.SQLException;

import com.comcast.crm.generic.databaseutility.DatabaseUtility;

public class ExecuteNonSelectQueryWithoutHardcodeTest {
	public static void main(String[] args) throws SQLException {
		DatabaseUtility dblib = new DatabaseUtility();
		dblib.getDbConnection("jdbc:mysql://49.249.28.218:3333/ninza_hrm", "root", "root");

		int data1 = dblib.executeNonSelectQuery(
				"insert into project (project_id,project_name,created_by,created_on,status,team_size) values('NH_PROJ_0198765','FB_23456709887','Deepak','05/08/2021','On Going',0);");
		System.out.println(data1);
		dblib.closeConnection();

	}

}
