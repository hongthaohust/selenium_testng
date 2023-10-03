package webdriver;

import java.util.Date;
import java.util.List;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Webdriver_Wait_Part_II_FindElement_ImplicitWait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//driver.manage().window().maximize();
	}
	
	
	public void TC_01_Find_Element() {
		driver.get("https://test.med247.me/users/sign_in");
		// find 1 element
		System.out.println("1 - Start: " + getDateTimeNow());
		driver.findElement(By.xpath("//input[@id='user_remember_me']")).click();
		System.out.println("1 - End: " + getDateTimeNow());
		
		// > 1 element
		System.out.println("2 - Start: " + getDateTimeNow());
		driver.findElement(By.xpath("//input[@required='required']")).sendKeys("letan_test_prefix@med247.co");
		System.out.println("2 - End: " + getDateTimeNow());
		
		// 0 element
		System.out.println("3 - Start: " + getDateTimeNow());
		try {
			driver.findElement(By.xpath("//input[@required='required123']")).isDisplayed();
		} catch (Exception e) {
			System.out.println("3 - End: " + getDateTimeNow());
		}
	}
	
	
	public void TC_02_Find_Elements() {
		driver.get("https://test.med247.me/users/sign_in");
		// Khai báo list elements
		List<WebElement> elements;
		
		// find 1 element
		System.out.println("1 - Start: " + getDateTimeNow());
		elements = driver.findElements(By.xpath("//input[@id='user_remember_me']"));
		System.out.println("Element size: " + elements.size());
		System.out.println("1 - End: " + getDateTimeNow());
		
		// > 1 element
		System.out.println("2 - Start: " + getDateTimeNow());
		elements = driver.findElements(By.xpath("//input[@required='required']"));
		System.out.println("Element size: " + elements.size());
		System.out.println("2 - End: " + getDateTimeNow());
		
		// 0 element
		System.out.println("3 - Start: " + getDateTimeNow());
		elements = driver.findElements(By.xpath("//input[@required='required123']"));
		System.out.println("Element size: " + elements.size());
		System.out.println("3 - End: " + getDateTimeNow());
	}

	
	@Test
	public void TC_03_Demo_ImplicitWait() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		System.out.println("1 - Start: " + getDateTimeNow());
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Hello World!']")).isDisplayed());
		System.out.println("1 - End: " + getDateTimeNow());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	
	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}

}
