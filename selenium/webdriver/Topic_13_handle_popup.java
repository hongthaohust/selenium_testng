package webdriver;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_handle_popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String actualText;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.manage().window().maximize();
	}
	
	
	public void TC_01_popup_fixed() {
		driver.get("https://www.zingpoll.com/");
		
		driver.findElement(By.id("Loginform")).click();
		Boolean status = driver.findElement(By.id("Login")).isDisplayed();
		Assert.assertTrue(status);
		
		driver.findElement(By.xpath("//div[@id='Login']//button[@class='close']")).click();
		status = driver.findElement(By.id("Login")).isDisplayed();
		Assert.assertFalse(status);
		
		sleepInSecond(3);
		driver.findElement(By.id("Loginform")).click();
		driver.findElement(By.id("loginEmail")).sendKeys("automationfc.vn@gmail.com");
		driver.findElement(By.id("loginPassword")).sendKeys("automationfc");
		sleepInSecond(3);
	}

	@Test
	public void TC_02_popup_Random() {
		driver.get("https://www.javacodegeeks.com/");
		WebElement element = driver.findElement(By.xpath("//div[@data-type='rectangle']"));
		// Kiểm tra có hiển thị popup không, nếu có thì click đóng popup
		if(element.isDisplayed()) {
			driver.findElement(By.xpath("//a[text()='×']")).click();
			sleepInSecond(2);
		}
		
		// Nhập text tìm kiếm và kiểm tra kết quả trả về trang đầu tiên artic title có chứa từ khóa tìm kiếm
		driver.findElement(By.id("search-input")).sendKeys("Selenium");
		sleepInSecond(1);
		driver.findElement(By.id("search-submit")).click();
		
		List<WebElement> allElement = driver.findElements(By.xpath("//h2[@class='post-title']/a"));
		
		for( WebElement item : allElement) {
			actualText = item.getText();
			System.out.println(actualText);
			Assert.assertTrue(actualText.contains("Selenium"));
		}
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
