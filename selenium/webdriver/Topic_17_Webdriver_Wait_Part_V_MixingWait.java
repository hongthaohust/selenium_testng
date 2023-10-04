package webdriver;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
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

public class Topic_17_Webdriver_Wait_Part_V_MixingWait {
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
//		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//driver.manage().window().maximize();
	}
	
	
	public void TC_01_Element_Found() {
		driver.get("https://www.facebook.com/");
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys("0358394433");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pass"))).sendKeys("hongthao241198");
		
		driver.findElement(By.name("login")).click();
	}
	
	
	public void TC_02_Element_notFound_Implicit() {
		driver.get("https://www.facebook.com/");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("Start: "+getDateTimeNow());
		
		try {
			WebElement emailTextbox= driver.findElement(By.xpath("//input[@name='khong_co_o_day']"));
			Assert.assertTrue(emailTextbox.isDisplayed());
			System.out.println("Có tìm thấy element");
		}
		catch (Exception e) {
			System.out.println("Không tìm thấy element");
		}
		System.out.println("End: "+getDateTimeNow());
	}
	
	@Test
	public void TC_03_Element_NotFound_Implicit_GreaterThan_Explicit() {
		driver.get("https://www.facebook.com/");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		System.out.println("Start: "+getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='khong_co_o_day']")));
			System.out.println("Có tìm thấy element");
		}
		catch (Exception e) {
			System.out.println("Không tìm thấy element");
		}
		System.out.println("End: "+getDateTimeNow());
	}

	
	public void TC_04_() {
		
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
