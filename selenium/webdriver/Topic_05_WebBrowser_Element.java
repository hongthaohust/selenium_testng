package webdriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_WebBrowser_Element {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	String url = "https://facebook.com";

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
	
	@Test
	public void TC_01_Web_Browser() {
		driver.get(url);
		
		driver.getCurrentUrl();
		
		driver.getPageSource(); //mã code của trang hiện tại
		
		driver.getTitle();
		
		driver.getWindowHandle(); // ID tab hiện tại
		
		driver.close(); // đóng tab hiện tại
		
		driver.quit(); // đóng cả trình duyệt hiện tại
		
		driver.findElement(By.xpath("")); // Tìm 1 element
		
		driver.findElements(By.xpath(""));
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // thời gian để chờ tìm kiếm element
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30)); // Thời gian chờ cho page tải xong
		
		driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(30));
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
