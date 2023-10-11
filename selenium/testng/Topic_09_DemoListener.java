package testng;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import graphql.Assert;

@Listeners(testng.Topic_08_Listener.class)
public class Topic_09_DemoListener {
	
	@Test
	public void TC_01_register() {
		System.out.println("Run TC_01_register");
	}
	
	@Test
	public void TC_02_login() {
		System.out.println("Run TC_02_login");
		Assert.assertTrue(false);
	}
	
	@Test (dependsOnMethods = "TC_02_login")
	public void TC_03_new() {
		System.out.println("Run TC_03_new");
	}
	
}
