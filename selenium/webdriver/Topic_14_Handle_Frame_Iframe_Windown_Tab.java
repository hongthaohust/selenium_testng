package webdriver;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Handle_Frame_Iframe_Windown_Tab {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	Select select;
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
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
	}
	
	
	public void TC_01_Iframe_Frame() {
		driver.get("https://skills.kynaenglish.vn/");
		
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fanpage ']//iframe")));
		String fbLink = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();
		Assert.assertEquals(fbLink, "167K followers");
		
//		WebElement element = driver.findElement(By.xpath("//html[@id='facebook']"));
//		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
//		sleepInsecond(3);
//		element.click();
		
		driver.switchTo().defaultContent();
		Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='Khóa học phát triển sự nghiệp cùng Kyna']")).isDisplayed());
		
		
		driver.switchTo().frame(driver.findElement(By.id("cs_chat_iframe")));
		sleepInsecond(3);
		driver.findElement(By.xpath("//div[@ng-click='openChatBox(1)']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Xin thông báo thời gian hỗ trợ mới của Kyna.vn')]")).isDisplayed());
		
		driver.findElement(By.xpath("//label[contains(.,'Nhập thông tin của bạn')]/following-sibling::input[1]")).sendKeys("Tôi tên là A");
		driver.findElement(By.xpath("//label[contains(.,'Nhập thông tin của bạn')]/following-sibling::input[2]")).sendKeys("0123456789");
		select = new Select(driver.findElement(By.id("serviceSelect")));
		select.selectByIndex(1);
		driver.findElement(By.xpath("//label[text()='Tin nhắn']/parent::label/following-sibling::textarea")).sendKeys("Chào cả nhà!");
		sleepInsecond(3);
		
		driver.switchTo().defaultContent();
		driver.findElement(By.id("live-search-bar")).sendKeys("Java");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepInsecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='menu-heading']/h1")).getText(),"'Java'");
		
	}

	@Test
	public void TC_02_Windown_Tab() {
		driver.get("https://skills.kynaenglish.vn/");
		String parentID = driver.getWindowHandle();
		clickToElementByJS("//div[@class='hotline']//img[@alt='facebook']");
		sleepInsecond(1);
		
		switchToWindownByTitle("Kyna.vn | Ho Chi Minh City | Facebook");
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/kyna.vn");
		
		switchToWindownByTitle("Kyna.vn - Học online cùng chuyên gia");
		clickToElementByJS("//div[@class='hotline']//img[@alt='youtube']");
		
		switchToWindownByTitle("Kyna.vn - YouTube");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='container']//*[@id='text' and text()='Kyna.vn']")).isDisplayed());
		
		switchToWindownByTitle("Kyna.vn | Ho Chi Minh City | Facebook");
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/kyna.vn");
		
		closeAllWindownsWithoutParent(parentID);
		sleepInsecond(3);
	}
	
	public void TC_03() {
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInsecond(long second) {
		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void clickToElementByJS (String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		
		jsExecutor.executeScript("arguments[0].click();", element);
	}
	
	public void switchToWindownByID(String parentWindown) {
		Set <String> allWindown = driver.getWindowHandles();
		
		for(String runWindown : allWindown) {
			if(!runWindown.equals(parentWindown)) {
				driver.switchTo().window(runWindown);
			}
		}
	}
	
	public void switchToWindownByTitle(String title) {
		Set<String> allWindown = driver.getWindowHandles();
		
		for (String currentWindown : allWindown) {
			driver.switchTo().window(currentWindown);
			sleepInsecond(2);
			String currentTitle = driver.getTitle();
			if(currentTitle.equals(title)) {
				break;
			}
		}
	}
	
	public boolean closeAllWindownsWithoutParent(String parentID) {
		Set<String> allWindown = driver.getWindowHandles();
		for (String currentWindown : allWindown) {
			if(!currentWindown.equals(parentID)) {
				driver.switchTo().window(currentWindown);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
		
		if(driver.getWindowHandles().size()==1) {
			return true;
		} else {
			return false;
		}
	}
}
