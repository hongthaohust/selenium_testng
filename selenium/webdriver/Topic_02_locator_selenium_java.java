package webdriver;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_locator_selenium_java {
	WebDriver driver;
//	String projectPath = System.getProperty("user.dir");
//	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
//		if (osName.contains("Windows")) {
//			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		} else {
//			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
//		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_locator() throws InterruptedException {
		driver.get("https://test.med247.me/users/sign_in");
		
		// ID
		driver.findElement(By.id("user_login")).sendKeys("letan_test_prefix@med247.co");
		Thread.sleep(3000);
		driver.findElement(By.id("user_password")).sendKeys("Med247@");
		Thread.sleep(3000);
		
		// Class
//		driver.findElement(By.className("btn primary d-block w-100 text-uppercase fw-medium fs-14")).click();
//		Thread.sleep(3000);
		// Name
		driver.findElement(By.name("commit")).click();
		Thread.sleep(3000);
		 
		// Link Text
		driver.findElement(By.linkText("Lịch sử khám")).click();
		Thread.sleep(3000);
		
		// Partial Link Text
		driver.findElement(By.partialLinkText("lịch hẹn")).click();
		Thread.sleep(3000);
		
		// Tagname
		Dimension textboxSize = driver.findElement(By.tagName("input")).getSize();
		System.out.println(textboxSize);
		
		// Css:
		
		driver.findElement(By.cssSelector("lịch hẹn")).click();
		Thread.sleep(3000);
		
		// Css: Cú pháp = tagname[attribute="value"] => chỉ đi xuôi từ trên xuống dưới
		
		// Xpath: Cú pháp = //tagname[@attribute="value"] => có thể đi ngược từ dưới lên
		
		// Contains: text có thể ở đầu/giữa/cuối
			// Tương đối: //tagname[contains(@attribute,'value')]
					  	  //tagname[contains(text(),'value')]
			// Tuyệt đối: //tagname[@attribute='value']
		  			  	  //tagname[text()='value']
		
		// Starts-with: text phải ở đầu chuỗi
						//tagname[starts-with(@attribute,'value')]
						//tagname[starts-with(text(),'value')]
		
		
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
