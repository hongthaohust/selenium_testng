package webdriver;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Handle_TextBox_TextArea {
	WebDriver driver;
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
	}
	
	public void TC_01_TextBox_TextArea_01() {
		Random rand = new Random();
		int randInt = rand.nextInt(999999);
		String firstName = "Nhung";
		String middleName = "Thi";
		String lastName = "Nguyen";
		
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		
		driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#middlename")).sendKeys(middleName);
		driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
		driver.findElement(By.cssSelector("input#email_address")).sendKeys("nhungnguyen"+randInt+"@yopmail.com");
		driver.findElement(By.cssSelector("input#password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#confirmation")).sendKeys("123456");
		
		driver.findElement(By.cssSelector("button[title='Register']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Change Password']//parent::p")).getText().contains(firstName +" "+ middleName +" "+ lastName));
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Change Password']//parent::p")).getText().contains("nhungnguyen"+randInt+"@yopmail.com"));
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//div[@id='header-account']//li[last()]")).click();
		
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/logoutSuccess/");
	}

	@Test
	public void TC_02_TextBox_TextArea_02() {
		String firstName = "Nhung";
		String lastName = "Nguyen";
		
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin123");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		
		driver.findElement(By.xpath("//li//span[text()='PIM']")).click();
		driver.findElement(By.xpath("//li//a[text()='Add Employee']")).click();
		
		driver.findElement(By.name("firstName")).sendKeys(firstName);
		driver.findElement(By.name("lastName")).sendKeys(lastName);
		
		String EmployeeID = driver.findElement(By.xpath("//label[text()='Employee Id']//parent::div//following-sibling::div/input")).getText();
		
		driver.findElement(By.xpath("//p[text()='Create Login Details']//following-sibling::div")).click();
		
		driver.findElement(By.xpath("//label[text()='Username']//parent::div//following-sibling::div/input")).sendKeys("");
		driver.findElement(By.xpath("//label[text()='Password']//parent::div//following-sibling::div/input")).sendKeys("");
		driver.findElement(By.xpath("//label[text()='Confirm Password']//parent::div//following-sibling::div/input")).sendKeys("");
		
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//*[@name='firstName']")).getText(), firstName);
		Assert.assertEquals(driver.findElement(By.xpath("//*[@name='lastName']")).getText(), lastName);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']//parent::div//following-sibling::div/input")).getText(), EmployeeID);
		
		driver.findElement(By.xpath("//div[@role='tab']/a[text()='Immigration']")).click();
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']//following-sibling::button[@type='button']")).click();
		
	}
	@Test
	public void TC_03_TextBox_TextArea_03() {
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
