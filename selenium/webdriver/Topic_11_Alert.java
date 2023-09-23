package webdriver;

import java.io.IOException;
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
	String firefoxAuthen = projectPath + "\\autoITScript\\authen_firefox.exe";
	String chromeAuthen = projectPath + "\\autoITScript\\authen_chrome.exe";
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
		// Bài 1
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
		
		
		
		// Bài 2
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
	public void TC_04_Authentication_Alert() throws IOException {
		String username = "admin";
		String password = "admin";
		
		//Cách 1: Xác thực username/password qua link luôn, không bật alert lên nữa
		//driver.get("http://" +username+":" +password+ "@the-internet.herokuapp.com/basic_auth");
		
		// Cách 2: Từ link A qua bước xác thực authen để vào được link B
//		driver.get("http://the-internet.herokuapp.com/");
//				//Get link href
//		String linkHref = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
//		handleAuthen
//		Alert(linkHref,username,password);
		
		// Cách 3: Cài autoIT hoặc có đoạn script rồi thì không cần cài
		Runtime.getRuntime().exec(new String[] { firefoxAuthen, username, password});
		sleepInSecond(3);
		driver.get("http://the-internet.herokuapp.com/basic_auth");

		
		
		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Basic Auth']")).isDisplayed());
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
	
	public void handleAuthenAlert(String link, String username, String password) {
		String spliLink[] = link.split("//");
		link = spliLink[0] + "//" + username + ":"+ password + "@" + spliLink[1];
		driver.get(link);
	}

}
