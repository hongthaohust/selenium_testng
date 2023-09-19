package webdriver;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import javax.lang.model.element.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_dropdownList_default {
	WebDriver driver;
	
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
	}
	
	
	public void TC_01_dropdownList() throws InterruptedException {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=");
		
		// Tìm dropdown
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		
		select.selectByIndex(6); // Lưu ý: index ở đây tính từ 0 (còn index trong xpath html thì tính từ 1 )
		Thread.sleep(3000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "June");
		
		select.selectByValue("10");
		Thread.sleep(3000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "October");
		
		select.selectByVisibleText("December"); // Nên dùng
		Thread.sleep(3000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "December"); // Kiểm tra item đã chọn có đúng không
		
		Assert.assertEquals(select.getOptions().size(), 13); // Kiểm tra dropdown có tổng bao nhiêu item
		
		Assert.assertFalse(select.isMultiple()); // Kiểm tra dropdown có phải dạng multiple không
		
		// Job Role 02
		driver.get("https://automationfc.github.io/basic-form/");
		select = new Select(driver.findElement(By.id("job2")));
		Assert.assertTrue(select.isMultiple());
		select.selectByVisibleText("Manual");
		Thread.sleep(500);
		select.selectByVisibleText("Desktop");
		Thread.sleep(500);
		select.selectByVisibleText("Security");
		Thread.sleep(500);
		
		List<WebElement> selectedOption = select.getAllSelectedOptions();
		Assert.assertEquals(selectedOption.size(), 3); // Kiểm tra số lượng option được chọn
		for (WebElement option : selectedOption) {
			System.out.println("Text selected = " + option.getText());
		}
		
		select.deselectAll();
		Thread.sleep(500);
		
		Assert.assertEquals(select.getAllSelectedOptions().size(), 0);
	}

	
	public void TC_02_dropdownList_2() throws InterruptedException {
		int rand = getRandomNumber();
		String firstname = "Phuong";
		String lastname = "Pham";
		String email = "harry" + rand + "@yopmail.com";
		String day = "1";
		String month = "May";
		String year = "1980";
		
		driver.get("https://demo.nopcommerce.com");
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		
		driver.findElement(By.id("FirstName")).sendKeys(firstname);
		driver.findElement(By.id("LastName")).sendKeys(lastname);
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		select.selectByVisibleText(day);
		Assert.assertEquals(select.getOptions().size(), 32);
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(month);
		Assert.assertEquals(select.getOptions().size(), 13);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(year);
		Assert.assertEquals(select.getOptions().size(), 112);
		
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Password")).sendKeys("123456");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("123456");
		
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),"Your registration completed");
		
		driver.findElement(By.xpath("//li/a[text()='My account']")).click();
		
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Password")).sendKeys("123456");
		driver.findElement(By.xpath("//div[@class='buttons']//button[@type='submit']")).click();
		
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(),day);
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(),month);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(),year);
		
	}
	
	@Test
	public void TC_03_dropdownList_3() throws InterruptedException {
		driver.get("https://www.rode.com/wheretobuy");
		
		select = new Select(driver.findElement(By.id("country")));
		Assert.assertFalse(select.isMultiple());
		
		select.selectByVisibleText("Vietnam");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");
		
		driver.findElement(By.xpath("//button[text()='Search']")).click();
		
		Thread.sleep(3000);
		
		List<WebElement> dealers = driver.findElements(By.xpath("//h3[text()='Dealers']//following-sibling::div//h4[@class='text-left']"));
		for (WebElement option : dealers) {
			System.out.println("Dealers = " + option.getText());
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

}

