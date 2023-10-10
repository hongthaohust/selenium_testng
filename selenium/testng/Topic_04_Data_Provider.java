package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_Data_Provider {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By emailTextbox = By.xpath("//*[@id='email']");
	By passwordTextbox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	
	
	@Test(dataProvider = "register_login")
	public void TC_01_Register_To_System(String username, String password)  {
		System.out.println(username + " / " + password);
	}
	
	@Test(dataProvider = "register_login")
	public void TC_02_Login_To_System(String username, String password)  {
		System.out.println(username + " / " + password);
	}

	@DataProvider(name = "loginData")
	public Object[][] UserAndPasswordData() {
		return new Object[][]{
			{"selenium_11_01@gmail.com", "111111"}, 
			{"selenium_11_02@gmail.com", "111111"}, 
			{"selenium_11_03@gmail.com", "111111"}};
	}
	
	@DataProvider (name = "register_login")
	public Object[][] registerAndLogin(Method methodName){
		Object[][] result = null;
		if(methodName.getName().contains("Login")) {
			result = new Object[][] {
				{"login_01@gmail.com", "111111"}, 
				{"login_02@gmail.com", "222222"}, 
				{"login_03@gmail.com", "333333"}};
			}
		else if(methodName.getName().contains("Register")) {
			result = new Object[][] {
				{"register_01@gmail.com", "111111"}, 
				{"register_02@gmail.com", "222222"}, 
				{"register_03@gmail.com", "333333"}};
			}
		return result;
		}
	
	
	
//	@Test (dataProvider = "Login_Med247")
//	public void TC_loginMed247(String username, String password) {
//		driver.get("https://test.med247.me/users/sign_in");
//		
//		driver.findElement(By.id("user_login")).sendKeys(username);
//		driver.findElement(By.id("user_password")).sendKeys(password);
//		driver.findElement(By.xpath("//input[@type='submit']")).click();
//		
//		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='view']//div[contains(text(),'Bảng điều khiển')]")).isDisplayed());
//		
//		//...........
//		
//		driver.findElement(By.xpath("//span[@class='user-avatar']")).click();
//		driver.findElement(By.xpath("//a[contains(text(),' Đăng xuất')]")).click();
//		
//		Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='Đăng nhập tài khoản']")).isDisplayed());	
//	}
//	
//	@DataProvider(name = "Login_Med247")
//	public Object[][] Med247_login(){
//		return new Object[][] {
//			{"quangvinh@yopmail.com","Med247@"},
//			{"letan_test_prefix@med247.co","Med247@"}
//		};
//	}
	
	
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
