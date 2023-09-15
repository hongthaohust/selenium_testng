package javaBasic;

import java.util.Random;

public class Topic_02_Fake_data {
	public static void main( String[] args) {
		Random rand = new Random();
		rand.nextInt(999999);
		
		
		System.out.print(rand.nextInt(999999) + " + ");
		System.out.print(rand.nextInt(999999) + " + ");
		System.out.print(rand.nextInt(999999) + " + ");
		System.out.print(rand.nextInt(999999));
		
	}
}
