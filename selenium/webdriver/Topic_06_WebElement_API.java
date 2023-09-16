package webdriver;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_WebElement_API {
	WebDriver driver;
	WebElement element;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01() {
		driver.get("https://facebook.com/");
		
		// Khi mình chỉ thao tác với element này 1 lần
		driver.findElement(By.cssSelector("")).click();
		
		// Khi biến này được sử dụng nhiều lần -> nên khai báo thành 1 biến
		element = driver.findElement(By.cssSelector(""));
		element.click();
		element.clear();
		//...
		
	}

	@Test
	public void TC_02() {
	}
	@Test
	public void TC_03() {
	}

	@Test
	public void TC_04() {
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}