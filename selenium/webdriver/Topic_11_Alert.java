package webdriver;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Alert {
	WebDriver driver;
	Alert alert;
	String inputText = "Xin chào cả nhà!";
	
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
		driver.manage().window().maximize();
	}
	
	
	public void TC_01_Alert_Accept() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();
		sleepInSecond(1);
		
		// Switch vào alert
		alert = driver.switchTo().alert();
		
		// Get text của alert -> kiểm tra đúng text
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		// Accept alert
		alert.accept();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");
	}

	public void TC_02_Alert_Confirm() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
		sleepInSecond(1);
		
		// Switch vào alert
		alert = driver.switchTo().alert();
		
		// Get text của alert
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		// Accept alert
		alert.accept();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Ok");
		
		
		
		
		driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
		sleepInSecond(1);
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		// Cancel alert
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");
	}
	
	public void TC_03_Alert_Prompt() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
		sleepInSecond(1);
		// Switch vào alert
		alert = driver.switchTo().alert();
		// Get text của alert
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		// Input text vào alert
		alert.sendKeys(inputText);
		sleepInSecond(1);
		// Accept alert
		alert.accept();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: "+inputText);
		
		
		
		
		driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
		sleepInSecond(1);
		// Switch vào alert
		alert = driver.switchTo().alert();
		// Get text của alert
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		// Input text vào alert
		alert.sendKeys(inputText);
		sleepInSecond(1);
		//Cancel alert
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: null");
	}
	
	@Test
	public void TC_04_Authentication_Alert() {
		String username = "admin";
		String password = "admin";
		
		driver.get("http://" +username+":" +password+ "@the-internet.herokuapp.com/basic_auth");
		
		
		
		//driver.findElement(By.xpath("//a[text()='CONTINUE']")).click();
		sleepInSecond(3);
		
		// Switch vào alert
		//alert = driver.switchTo().alert();
		
		// Get text của alert -> kiểm tra đúng text
		//Assert.assertEquals(alert.getText(), "Customer ID  cannot be left blank.");
		
		// Accept alert
		//alert.accept();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
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
