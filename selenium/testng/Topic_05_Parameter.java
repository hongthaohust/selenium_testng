package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_05_Parameter {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By emailTextbox = By.xpath("//*[@id='email']");
	By passwordTextbox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");
	
	@Parameters("browserName")
	
	@BeforeClass
	public void beforeClass(@Optional("edge") String browser) {
		if(browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if(browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if(browser.equals("edge")) {
			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		} else {
			throw new RuntimeException("Please input your brower name!");
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
	
	
	@Test (dataProvider = "Login_Med247")
	public void TC_loginMed247(String username, String password) {
		driver.get("https://test.med247.me/users/sign_in");
		
		driver.findElement(By.id("user_login")).sendKeys(username);
		driver.findElement(By.id("user_password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='view']//div[contains(text(),'Bảng điều khiển')]")).isDisplayed());
		
		//...........
		
		driver.findElement(By.xpath("//span[@class='user-avatar']")).click();
		driver.findElement(By.xpath("//a[contains(text(),' Đăng xuất')]")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='Đăng nhập tài khoản']")).isDisplayed());	
	}
	
	
	@DataProvider(name = "Login_Med247")
	public Object[][] Med247_login(){
		return new Object[][] {
			{"quangvinh@yopmail.com","Med247@"},
			{"letan_test_prefix@med247.co","Med247@"}
		};
	}
	
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
