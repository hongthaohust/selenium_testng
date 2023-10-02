package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
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
	
	String path = projectPath + "\\uploadFiles\\";
	String[] imageName = {"med247_01.jpg", "med247_02.jpg", "mypicture_01.jpg"};
	String[] imagePath = {path+"med247_01.jpg", path + "med247_02.jpg", path + "mypicture_01.jpg"};
	
	String firefox_autoIT1 = projectPath + "\\autoITScript\\firefoxUploadOneTime.exe";
	String firefox_autoIT2 = projectPath + "\\autoITScript\\firefoxUploadMultiple.exe";

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
	
	
	public void TC_01_sendKey_01() {
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

	
	public void TC_02_SendKey_02() {
		driver.get("https://gofile.io/uploadFiles");
		WebElement uploadFiles = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFiles.sendKeys(imagePath[0] + "\n" + imagePath[1] +"\n" + imagePath[2]);
		sleepInSecond(10);
		
		driver.findElement(By.xpath("//div[contains(@class,'mainUploadSuccessLink')]//a")).click();
		sleepInSecond(3);
		
		for (String image : imageName) {
			WebElement download = driver.findElement(By.xpath("//span[text()='"+image+"']//ancestor::div[contains(@class,'text-truncate')]//following-sibling::div//span[text()='Download']"));
			WebElement play = driver.findElement(By.xpath("//span[text()='"+image+"']//ancestor::div[contains(@class,'text-truncate')]//following-sibling::div//span[text()='Play']"));
			
			Assert.assertTrue(download.isDisplayed());
			Assert.assertTrue(play.isDisplayed());
		}
		
	}
	
	
	public void TC_03_AutoIT_01() throws IOException {
		driver.get("https://gofile.io/uploadFiles");
		
//		driver.findElement(By.xpath("//div[@id='filesUpload']//button[contains(@class,'filesUploadButton')]")).click();
//		sleepInSecond(3);
//		Runtime.getRuntime().exec(new String[] {firefox_autoIT1,imagePath[0]});   // Thực thi file .exe đã inject - Upload 1 file
		
		
		driver.findElement(By.xpath("//div[@id='filesUpload']//button[contains(@class,'filesUploadButton')]")).click();
		sleepInSecond(2);
		Runtime.getRuntime().exec(new String[] {firefox_autoIT2,imagePath[0],imagePath[1],imagePath[2]});    // Thực thi file .exe đã inject - Upload nhiều file
		
		
		sleepInSecond(17);
		driver.findElement(By.xpath("//div[contains(@class,'mainUploadSuccessLink')]//a")).click();
		sleepInSecond(3);
		
		for (String image : imageName) {
			WebElement download = driver.findElement(By.xpath("//span[text()='"+image+"']//ancestor::div[contains(@class,'text-truncate')]//following-sibling::div//span[text()='Download']"));
			WebElement play = driver.findElement(By.xpath("//span[text()='"+image+"']//ancestor::div[contains(@class,'text-truncate')]//following-sibling::div//span[text()='Play']"));
			
			Assert.assertTrue(download.isDisplayed());
			Assert.assertTrue(play.isDisplayed());
		}
	}
	
	
	public void TC_04_AutoIT_02() throws IOException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		driver.findElement(By.cssSelector(".fileinput-button")).click();
		Runtime.getRuntime().exec(new String[] {firefox_autoIT1,imagePath[0]});   // Thực thi file .exe đã inject - Upload 1 file
		sleepInSecond(5);
		
		driver.findElement(By.cssSelector(".fileinput-button")).click();
		Runtime.getRuntime().exec(new String[] {firefox_autoIT1,imagePath[1]});   // Thực thi file .exe đã inject - Upload 1 file
		sleepInSecond(5);
		
		driver.findElement(By.cssSelector(".fileinput-button")).click();
		Runtime.getRuntime().exec(new String[] {firefox_autoIT1,imagePath[2]});   // Thực thi file .exe đã inject - Upload 1 file
		
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+imageName[0]+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+imageName[1]+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+imageName[2]+"']")).isDisplayed());
	}
	
	
	@Test
	public void TC_05_Java_Robot() throws AWTException {
		// Giả lập hành vi người dùng: copy paste đường dẫn
		
		driver.get("https://gofile.io/uploadFiles");
		
		driver.findElement(By.xpath("//div[@id='filesUpload']//button[contains(@class,'filesUploadButton')]")).click();
		sleepInSecond(1);
		
		// Copy file path
		StringSelection select = new StringSelection(imagePath[0]);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
		
		// Load file
		Robot robot = new Robot();
		sleepInSecond(1);
		
		// Nhấn xuống Ctrl-V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		// Nhả Ctrl-V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		sleepInSecond(1);
		
		// Nhấn enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		sleepInSecond(10);
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
