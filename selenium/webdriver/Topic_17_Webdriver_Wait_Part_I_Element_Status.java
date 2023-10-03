package webdriver;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Webdriver_Wait_Part_I_Element_Status {
	WebDriver driver;
	WebDriverWait explicitWait;
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
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		driver.manage().window().maximize();
	}
	
	public void TC_01_Visible() {
		driver.get("https://facebook.com");
		
		// wait for email visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		driver.findElement(By.id("email")).sendKeys("0358394433");
	}

	
	public void TC_02_Invisible() {
		driver.get("https://facebook.com");
		// wait for popup "Đăng ký" Invisible
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text()='Đăng ký']")));
		
	}
	
	
	public void TC_03_Presence() {
		// Element có trong dom, có hiển thị trên UI
		driver.get("https://facebook.com");
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
		driver.findElement(By.id("email")).sendKeys("0358394433");
		
		// Element có trong dom, Không hiển thị trên UI
		driver.get("http://live.techpanda.org/index.php/");
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='header-account']//a[text()='My Account']")));
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	}
	
	@Test
	public void TC_04_Staleness() {
		driver.get("https://test.med247.me/users/sign_in");
		driver.findElement(By.id("user_login")).sendKeys("aaaa");
		driver.findElement(By.id("user_password")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert danger']")));
		WebElement element = driver.findElement(By.xpath("//div[@class='alert danger']"));
		
		driver.navigate().refresh();
		explicitWait.until(ExpectedConditions.stalenessOf(element));
		
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
