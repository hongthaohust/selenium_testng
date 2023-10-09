package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class Topic_03_Priority_and_Skips {
	@BeforeClass
	public void beforeClass() {
		System.out.println("Run BeforeClass");
	}
	
	@Test (priority = 1, description = "Mô tả create user")
	public void Create() {
		System.out.println("Create user");
	}
	
	@Test (priority = 2)
	public void Edit() {
		System.out.println("Edit User");
	}
	
	@Test (priority = 3)
	public void View() {
		System.out.println("View User");
	}
	
	@Test (priority = 4)
	public void Search() {
		System.out.println("Search User");
	}
	
	@Test (priority = 5, enabled = false)
	public void Delete() {
		System.out.println("Delete User");
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("Run AfterClass");
	}
}
