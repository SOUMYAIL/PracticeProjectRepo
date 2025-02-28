package com.comcast.crm.listeners;

import org.testng.Assert;

import org.testng.annotations.Test;

public class activateSim {

	@Test(retryAnalyzer = com.comcast.crm.listenerUtility.RetryListernerImplementationClass.class)
	public void activateSim1() {

		System.out.println("execute test1");

		Assert.assertEquals("", "Login");
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	}

}
