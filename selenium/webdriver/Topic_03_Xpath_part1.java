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

public class Topic_03_Xpath_part1 {
	WebDriver driver;
//	String projectPath = System.getProperty("user.dir");
//	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
//		if (osName.contains("Windows")) {
//			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		} else {
//			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
//		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
	}
	
	public void TC_01_Register_With_Empty_Data() {
		driver.get("https://alada.vn");
		driver.findElement(By.xpath("//a[text()='Đăng Ký']")).click();
		
		driver.findElement(By.id("txtFirstname")).sendKeys("");
		driver.findElement(By.id("txtEmail")).sendKeys("");
		driver.findElement(By.id("txtCEmail")).sendKeys("");
		driver.findElement(By.id("txtPassword")).sendKeys("");
		driver.findElement(By.id("txtCPassword")).sendKeys("");
		driver.findElement(By.id("txtPhone")).sendKeys("");
		
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		
		String emailErrorMessage = driver.findElement(By.id("txtEmail-error")).getText();
		Assert.assertEquals(emailErrorMessage,"Vui lòng nhập email");
		
		String passwordErrorMessage = driver.findElement(By.id("txtPassword-error")).getText();
		Assert.assertEquals(passwordErrorMessage,"Vui lòng nhập mật khẩu");
	}

	public void TC_02_Register_With_Invalid_Email() {
		driver.get("https://alada.vn");
		driver.findElement(By.xpath("//a[text()='Đăng Ký']")).click();
		
		driver.findElement(By.id("txtFirstname")).sendKeys("John Kennedy");
		driver.findElement(By.id("txtEmail")).sendKeys("123@123@65dd");
		driver.findElement(By.id("txtCEmail")).sendKeys("123@123@65dd");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
		
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		
		String emailErrorMessage = driver.findElement(By.id("txtEmail-error")).getText();
		Assert.assertEquals(emailErrorMessage,"Vui lòng nhập email hợp lệ");
		
		String cemailErrorMessage = driver.findElement(By.id("txtCEmail-error")).getText();
		Assert.assertEquals(cemailErrorMessage,"Email nhập lại không đúng");
	}
	
	public void TC_03_Register_With_Incorrect_Confirm_Email() {
		driver.get("https://alada.vn");
		driver.findElement(By.xpath("//a[text()='Đăng Ký']")).click();
		
		driver.findElement(By.id("txtFirstname")).sendKeys("John Kennedy");
		driver.findElement(By.id("txtEmail")).sendKeys("johnKennedy@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("johnKennedy123@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
		
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		
		String cemailErrorMessage = driver.findElement(By.id("txtCEmail-error")).getText();
		Assert.assertEquals(cemailErrorMessage,"Email nhập lại không đúng");
	}

	public void TC_04_Register_With_Password_Less_Than_6_Characters() {
		driver.get("https://alada.vn");
		driver.findElement(By.xpath("//a[text()='Đăng Ký']")).click();
		
		driver.findElement(By.id("txtFirstname")).sendKeys("John Kennedy");
		driver.findElement(By.id("txtEmail")).sendKeys("johnKennedy@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("johnKennedy@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("1234");
		driver.findElement(By.id("txtCPassword")).sendKeys("1234");
		driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
		
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		
		String passwordErrorMessage = driver.findElement(By.id("txtPassword-error")).getText();
		Assert.assertEquals(passwordErrorMessage,"Mật khẩu phải có ít nhất 6 ký tự");
		
		String cpasswordErrorMessage = driver.findElement(By.id("txtCPassword-error")).getText();
		Assert.assertEquals(cpasswordErrorMessage,"Mật khẩu phải có ít nhất 6 ký tự");
	}
	
	@Test
	public void TC_05_Login_With_Valid_Email_And_Password() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		String fullname = "tester Tester";
		String email = "tester@yopmail.com";
		
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("pass")).sendKeys("123456");
		
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		//Cách 1: Tuyệt đối: Text ngắn, không có ký tự xuống dòng, tab, khoảng trắng ở đầu/cuối
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='page-title']//h1")).getText(), "MY DASHBOARD");
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
		
		// Cách 2: Tương đối: Có ký tự xuống dòng, tab, khoảng trắng đầu/cuối
		Assert.assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'My Dashboard')]")).isDisplayed());
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='hello']//strong")).getText(),"Hello, "+ fullname +"!");
		
		String contactInfo = driver.findElement(By.xpath("//div[@class='col-1']//div[@class='box-content']//p")).getText();
		Assert.assertTrue(contactInfo.contains(fullname));
		Assert.assertTrue(contactInfo.contains(email));
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//div[@class='box-content']//p[contains(.,'"+ fullname +"')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//div[@class='box-content']//p[contains(.,'"+ email +"')]")).isDisplayed());
		
		
		driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		
	}

	@Test
	public void TC_06_Register_To_System() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		String firstname = "John";
		String lastname = "One";
		String email = "johnwitch"+ randomNumber() + "@yopmail.com";
		
		driver.findElement(By.xpath("//div[@class='buttons-set']//a[@title='Create an Account']")).click();
		
		driver.findElement(By.id("firstname")).sendKeys(firstname);
		//driver.findElement(By.id("middlename")).sendKeys("Witch");
		driver.findElement(By.id("lastname")).sendKeys(lastname);
		driver.findElement(By.id("email_address")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("confirmation")).sendKeys("123456");
		
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"Thank you for registering with Main Website Store.");
		
		String contactInfo = driver.findElement(By.xpath("//div[@class='col-1']//div[@class='box-content']//p")).getText();
		Assert.assertTrue(contactInfo.contains(firstname + " " + lastname));
		Assert.assertTrue(contactInfo.contains(email));
		
		driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		
	}
	
	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
