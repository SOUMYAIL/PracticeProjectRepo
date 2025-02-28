package com.comcast.crm.listeners;


import org.testng.Assert;


import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.baseUtility.BaseClass;


@Listeners(com.comcast.crm.listenerUtility.PracticeListenerImplementation.class)
public class InvoiceTest extends BaseClass {

	@Test
	public void createInvoice() {

		System.out.println("execute test1");

		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle, "Login");
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	}

	@Test
	public void createInvoiceWithContactTest() {
		System.out.println("execute test2");
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");

	}

}
