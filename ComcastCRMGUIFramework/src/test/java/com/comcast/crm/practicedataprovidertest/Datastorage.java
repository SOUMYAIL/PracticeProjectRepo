package com.comcast.crm.practicedataprovidertest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class Datastorage {
	
	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException {
		ExcelUtility elib = new ExcelUtility();
		int rowCount = elib.getRowCount("dataprovider");
		System.out.println(rowCount);

		Object[][] objAr = new Object[rowCount][2];
		for (int i = 0; i < rowCount; i++) {
			objAr[i][0] = elib.getDataFromExcel("dataprovider", i + 1, 0);

			objAr[i][1] = elib.getDataFromExcel("dataprovider", i + 1, 1);

		}

		return objAr;

	}

}
