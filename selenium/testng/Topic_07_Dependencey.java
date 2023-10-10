package testng;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;


@Test
public class Topic_07_Dependencey {
	
	@Test()
	public void TC_01_register() {
		System.out.println("Run TC_01_Login");
		Assert.assertTrue(false);
	}
	
	@Test(dependsOnMethods="TC_01_register")
	public void TC_02_login() {
		System.out.println("Run TC_01_Login");
	}
	
}
