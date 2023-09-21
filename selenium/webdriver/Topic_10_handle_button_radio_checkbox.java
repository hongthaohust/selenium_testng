package webdriver;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_handle_button_radio_checkbox {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	
	By loginButton = By.xpath("//button[@class='fhs-btn-login']");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_handle_button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		
		Assert.assertFalse(elementIsEnabled(loginButton));
		
		// Verify button có màu xám
		
		
		driver.findElement(By.id("login_username")).sendKeys("uesername123456@yopmail.com");
		driver.findElement(By.id("login_password")).sendKeys("a123456789");
		
		Assert.assertTrue(elementIsEnabled(loginButton));
		
		driver.navigate().refresh();
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		
		clickByJS(loginButton);
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Số điện thoại/Email']//following-sibling::div[text()='Thông tin này không thể để trống']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Mật khẩu']//following-sibling::div[text()='Thông tin này không thể để trống']")).isDisplayed());
		
		driver.navigate().refresh();
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		
		Assert.assertFalse(elementIsEnabled(loginButton));
		
		// Remove attribute disable
		
		
		
	}

	
	public void TC_02_radio() {
	}
	
	public void TC_03_checkbox() {
	}
	
	public boolean elementIsEnabled(By by) {
		WebElement element = driver.findElement(by);
		if(element.isEnabled()) {
			System.out.println("Element '"+ element + "' is Enabled");
			return true;
		} else {
			System.out.println("Element '"+ element + "' is Disable");
			return false;
		}
	}
	
	public void clickByJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].click();", element);
	}
	
	public void removeDisableAttributeByJS(By by, String attributeName) {
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
