package webdriver;

import java.time.Duration;
import java.util.Date;

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

public class Topic_17_Webdriver_Wait_Part_IV_ExplicitWait {
	WebDriver driver;
	WebDriverWait explicitWait;
	Alert alert;
	WebElement dateSelected;
	String today = "Tuesday, October 24, 2023";
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	String path = projectPath + "\\uploadFiles\\";
	String[] imageName = {"med247_01.jpg", "med247_02.jpg", "mypicture_01.jpg"};
	String[] imagePath = {path+"med247_01.jpg", path + "med247_02.jpg", path + "mypicture_01.jpg"};

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
	
	
	public void TC_01_Alert_Presence() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement element = explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@onclick='jsAlert()']")));
		element.click();
		
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		alert.accept();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");
	}
	
	
	public void TC_02_ExplicitWait_Visible() {
		driver.get("http://juliemr.github.io/protractor-demo/");
		
		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("10");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("15");
		driver.findElement(By.id("gobutton")).click();
		
		WebElement resultText = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='25']")));
		
		Assert.assertTrue(resultText.isDisplayed());
	}
	
	
	public void TC_03_ExplicitWait_Invisible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Start']"))).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Hello World!']")));
	}

	
	
	public void TC_04_Ajax_loading() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		// Chờ cho date time được hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_Panel1")));
		
		dateSelected = driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));
		Assert.assertEquals(dateSelected.getText(), "No Selected Dates to display.");
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@title='"+ today +"']"))).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']")));
		
		dateSelected = driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));
		Assert.assertEquals(dateSelected.getText(), today);
	}
	
	@Test
	public void TC_05_UploadFile() {
		driver.get("https://gofile.io/uploadFiles");
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='file']"))).sendKeys(imagePath[0] + "\n" + imagePath[1] + "\n" + imagePath[2]);
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'mainUploadSuccessLink')]//a"))).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Abuse']")));
		Assert.assertTrue(driver.findElement(By.xpath("//button[text()='Abuse']")).isDisplayed());
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
