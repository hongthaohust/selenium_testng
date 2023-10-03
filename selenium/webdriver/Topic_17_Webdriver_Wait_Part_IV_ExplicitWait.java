package webdriver;

import java.time.Duration;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Webdriver_Wait_Part_IV_ExplicitWait {
	WebDriver driver;
	WebDriverWait explicitWait;
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
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(20));
		//driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_ExplicitWait() {
		driver.get("http://juliemr.github.io/protractor-demo/");
		
		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("10");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("15");
		driver.findElement(By.id("gobutton")).click();
		
		sleepInSecond(10);
		Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='25']")).isDisplayed());
	}
	
	
	public void TC_02_() {
		
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	
	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
