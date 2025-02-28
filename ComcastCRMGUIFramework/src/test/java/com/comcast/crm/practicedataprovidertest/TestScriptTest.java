package com.comcast.crm.practicedataprovidertest;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.baseUtility.PracticeBaseClass;

@Listeners(com.comcast.crm.listenerUtility.ListenerImplemetantionClass.class)
public class TestScriptTest extends PracticeBaseClass {
	@Test
	public void Test1() {
		System.out.println("Test1");
	}

}
