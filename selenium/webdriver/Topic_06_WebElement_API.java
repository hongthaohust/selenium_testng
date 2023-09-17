package webdriver;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_WebElement_API {
	WebDriver driver;
	
	By emailTextbox = By.cssSelector("input#email");
	By ageRadioButton = By.cssSelector("input#under_18");
	By education = By.cssSelector("textarea#edu");
	By user5 = By.xpath("//div[@class='figcaption']/h5[contains(text(),'User5')]");
	By languageJava = By.cssSelector("input#java");

	
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
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
	}
	
	
	public void TC_01_isDisplayed() throws InterruptedException {
		
		driver.navigate().refresh();
		
		if(driver.findElement(emailTextbox).isDisplayed()) {
			driver.findElement(emailTextbox).sendKeys("Automation Testing");
			System.out.println("Element email is Displayed");
		}
		else System.out.println("Element email is Not Displayed");
		
		if(driver.findElement(ageRadioButton).isDisplayed()) {
			driver.findElement(ageRadioButton).click();
			System.out.println("Element RadioButton is Displayed");
		}
		else System.out.println("Element RadioButton is Not Displayed");
		
		if(driver.findElement(education).isDisplayed()) {
			driver.findElement(education).sendKeys("Automation Testing");
			System.out.println("Element Education is Displayed");
		}
		else System.out.println("Element Education is Not Displayed");
		
		if(driver.findElement(user5).isDisplayed()) {
			System.out.println("Element user5 is Displayed");
		}
		else System.out.println("Element user5 is Not Displayed");
		
		Thread.sleep(3000);
	}
	
	public void TC_02_isEnable() throws InterruptedException {
		driver.navigate().refresh();
		
		//Enable
		if(driver.findElement(emailTextbox).isEnabled()) {
			System.out.println("Element email is Enabled");
		}
		else System.out.println("Element email is Disabled");
		
		if(driver.findElement(ageRadioButton).isEnabled()) {
			System.out.println("Element RadioButton is Enabled");
		}
		else System.out.println("Element RadioButton is Disabled");
		
		if(driver.findElement(education).isEnabled()) {
			driver.findElement(education).sendKeys("Automation Testing");
			System.out.println("Element Education is Enabled");
		}
		else System.out.println("Element Education is Disabled");
		
		Assert.assertTrue(driver.findElement(By.cssSelector("select#job1")).isEnabled());
		Assert.assertTrue(driver.findElement(By.cssSelector("select#job2")).isEnabled());
		Assert.assertTrue(driver.findElement(By.cssSelector("input#development")).isEnabled());
		Assert.assertTrue(driver.findElement(By.cssSelector("input#slider-1")).isEnabled());
		
		// Disabled
		if(driver.findElement(By.cssSelector("input#disable_password")).isEnabled()) {
			System.out.println("Element Password is Enabled");
		}
		else System.out.println("Element Password is Disabled");
		
		Assert.assertFalse(driver.findElement(By.cssSelector("input#radio-disabled")).isEnabled());
		Assert.assertFalse(driver.findElement(By.cssSelector("textarea#bio")).isEnabled());
		Assert.assertFalse(driver.findElement(By.cssSelector("select#job3")).isEnabled());
		Assert.assertFalse(driver.findElement(By.cssSelector("input#check-disbaled")).isEnabled());
		Assert.assertFalse(driver.findElement(By.cssSelector("input#slider-2")).isEnabled());
		
		Thread.sleep(3000);
	}
	
	@Test
	public void TC_02_isEnable_Cach2() {
		driver.navigate().refresh();
		
		Assert.assertTrue(elementIsEnabled(emailTextbox));
		Assert.assertTrue(elementIsEnabled(ageRadioButton));
		Assert.assertTrue(elementIsEnabled(By.cssSelector("select#job1")));
		Assert.assertTrue(elementIsEnabled(By.cssSelector("select#job2")));
		Assert.assertTrue(elementIsEnabled(By.cssSelector("input#development")));
		Assert.assertTrue(elementIsEnabled(By.cssSelector("input#slider-1")));
		Assert.assertFalse(elementIsEnabled(By.cssSelector("input#disable_password")));
		Assert.assertFalse(elementIsEnabled(By.cssSelector("input#radio-disabled")));
		Assert.assertFalse(elementIsEnabled(By.cssSelector("textarea#bio")));
		Assert.assertFalse(elementIsEnabled(By.cssSelector("select#job3")));
		Assert.assertFalse(elementIsEnabled(By.cssSelector("input#check-disbaled")));
		Assert.assertFalse(elementIsEnabled(By.cssSelector("input#slider-2")));
	}
	
	@Test
	public void TC_03_isSellected() {
		driver.navigate().refresh();
		
		System.out.println("Step 1: Kiểm tra chưa chọn checkbox");
		Assert.assertFalse(elementIsSellected(ageRadioButton));
		Assert.assertFalse(elementIsSellected(languageJava));
		
		System.out.println("Step 2: Kiểm tra đã chọn checkbox");
		driver.findElement(ageRadioButton).click();
		driver.findElement(languageJava).click();
		
		Assert.assertTrue(elementIsSellected(ageRadioButton));
		Assert.assertTrue(elementIsSellected(languageJava));
		
		System.out.println("Step 3: Kiểm tra bỏ chọn checkbox");
		driver.findElement(languageJava).click();
		Assert.assertFalse(elementIsSellected(languageJava));
		
		driver.findElement(ageRadioButton).click();
		Assert.assertTrue(elementIsSellected(ageRadioButton));
		
	}
	
	public boolean elementIsDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if(element.isDisplayed()) {
			System.out.println("Element '"+ element + "' is Displayed");
			return true;
		} else {
			System.out.println("Element '"+ element + "' is not Displayed");
			return false;
		}
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
	public boolean elementIsSellected(By by) {
		WebElement element = driver.findElement(by);
		if(element.isSelected()) {
			System.out.println("Element '"+ element + "' is Sellected");
			return true;
		} else {
			System.out.println("Element '"+ element + "' is Unsellected");
			return false;
		}
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
