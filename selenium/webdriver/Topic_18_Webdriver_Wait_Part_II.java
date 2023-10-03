package webdriver;

import java.util.Date;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Webdriver_Wait_Part_II {
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//driver.manage().window().maximize();
		driver.get("https://test.med247.me/users/sign_in");
	}
	
	@Test
	public void TC_01_Find_Element() {
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
