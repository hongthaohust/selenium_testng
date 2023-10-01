package webdriver;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Upload_File {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	String path = System.getProperty("user.dir") + "\\uploadFiles\\";
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
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
	}
	
	
	public void TC_01_sendKey() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
//		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
//		uploadFile.sendKeys(imagePath[0]);
//		sleepInSecond(1);
//		
//		uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
//		uploadFile.sendKeys(imagePath[1]);
//		sleepInSecond(1);
//		
//		uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
//		uploadFile.sendKeys(imagePath[2]);
//		sleepInSecond(1);
		
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(imagePath[0] +"\n" + imagePath[1] +"\n" + imagePath[2]);
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+imageName[0]+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+imageName[1]+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+imageName[2]+"']")).isDisplayed());
		
		List<WebElement> startButton = driver.findElements(By.cssSelector("td .start"));
		
		for (WebElement start : startButton) {
			start.click();
			sleepInSecond(1);
		}
		sleepInSecond(3);
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'"+imageName[0]+"')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'"+imageName[1]+"')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'"+imageName[2]+"')]"));
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+imageName[0]+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+imageName[1]+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+imageName[2]+"']")).isDisplayed());
	}

	@Test
	public void TC_02() {
		driver.get("https://gofile.io/uploadFiles");
		WebElement uploadFiles = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFiles.sendKeys(imagePath[0] + "\n" + imagePath[1] +"\n" + imagePath[2]);
		sleepInSecond(3);
	}
	
	public void TC_03() {
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		return status;
	}
	
	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
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
