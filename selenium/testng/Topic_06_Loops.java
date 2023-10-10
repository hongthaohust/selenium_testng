package testng;

import java.util.Random;

import org.testng.annotations.Test;

@Test (invocationCount=20)
public class Topic_06_Loops {
	public void TC_01_login() {
		System.out.println("Run TC_01" + randomNumber());
	}
	
	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}
}
