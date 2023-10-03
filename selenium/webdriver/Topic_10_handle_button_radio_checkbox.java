package webdriver;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_handle_button_radio_checkbox {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;
	
	By loginButton = By.xpath("//button[@class='fhs-btn-login']");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		}

		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
	}
	
	
	public void TC_01_button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		
		Assert.assertFalse(elementIsEnabled(loginButton));
		
		// Verify button có màu xám
		
		
		driver.findElement(By.id("login_username")).sendKeys("uesername123456@yopmail.com");
		driver.findElement(By.id("login_password")).sendKeys("a123456789");
		
		Assert.assertTrue(elementIsEnabled(loginButton));
		
		driver.navigate().refresh();
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		
//		clickByJS(loginButton);
//		sleepInSecond(3);
//		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Số điện thoại/Email']//following-sibling::div[text()='Thông tin này không thể để trống']")).isDisplayed());
//		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Mật khẩu']//following-sibling::div[text()='Thông tin này không thể để trống']")).isDisplayed());
//		
//		driver.navigate().refresh();
//		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		
		Assert.assertFalse(elementIsEnabled(loginButton));
		
		// Remove attribute disable
		removeDisableAttributeByJS(loginButton);
		sleepInSecond(3);
		
		Assert.assertTrue(elementIsEnabled(loginButton));
		
		driver.findElement(loginButton).click();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Số điện thoại/Email']//following-sibling::div[text()='Thông tin này không thể để trống']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Mật khẩu']//following-sibling::div[text()='Thông tin này không thể để trống']")).isDisplayed());
	}

	
	public void TC_02_checkbox_radio_default() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
//		List <WebElement> checkboxs = driver.findElements(By.xpath("//input[@type='checkbox']"));
//		
//		for (WebElement item : checkboxs) {
//			if(!elementIsSellected(item)) {
//				item.click();
//				sleepInSecond(1);
//			}
//			Assert.assertTrue(elementIsSellected(item));
//		}
//		for (WebElement item : checkboxs) {
//			if(elementIsSellected(item)) {
//				item.click();
//				sleepInSecond(1);
//			}
//			Assert.assertFalse(elementIsSellected(item));
//		}
		
		WebElement element = driver.findElement(By.xpath("//label[text()=' High Blood Pressure ']/preceding-sibling::input"));
		element.click();
		Assert.assertTrue(elementIsSellected(element));
		
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		WebElement element2 = driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
		element2.click();
		Assert.assertTrue(elementIsSellected(element2));
	}
	
	@Test
	public void TC_03_checkbox_radio_custom() {
//		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		
		// Cách 1
//		WebElement RadioLabel = driver.findElement(By.xpath("//div[contains(text(),'Đăng ký cho người thân')]/parent::label"));
//		RadioLabel.click();
//		
//		WebElement RadioInput = driver.findElement(By.xpath("//div[contains(text(),'Đăng ký cho người thân')]/preceding-sibling::div/input"));
//		Assert.assertTrue(elementIsSellected(RadioInput));
		
		// Cách 2: dùng JS để click vào item bị hidden (ẩn)
//		WebElement RadioInput = driver.findElement(By.xpath("//div[contains(text(),'Đăng ký cho người thân')]/preceding-sibling::div/input"));
//		clickElementByJS(RadioInput);
//		Assert.assertTrue(elementIsSellected(RadioInput));
		
		
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		Assert.assertFalse(driver.findElement(By.xpath("//div[@data-value='Hà Nội']//div[@class='rseUEf nQOrEb']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//div[@data-value='Hải Phòng']//div[@class='rseUEf nQOrEb']")).isDisplayed());
		WebElement country = driver.findElement(By.xpath("//div[@data-value='Hà Nội']"));
		country.click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@data-value='Hà Nội']//div[@class='rseUEf nQOrEb']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//div[@data-value='Hải Phòng']//div[@class='rseUEf nQOrEb']")).isDisplayed());
		
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
	
	
	// Chỉ click được vào element bị ẩn, không click được vào element bị disable
	public void clickByJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].click();", element);
	}
	
	public void clickElementByJS(WebElement element) {
		jsExecutor.executeScript("arguments[0].click();", element);
	}
	
	public void removeDisableAttributeByJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", element);
		
	}
	
	public boolean elementIsSellected(WebElement element) {
		if(element.isSelected()) {
			System.out.println("Element '"+ element + "' is Sellected");
			return true;
		} else {
			System.out.println("Element '"+ element + "' is Unsellected");
			return false;
		}
	}
	
	// Sleep
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
