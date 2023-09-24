package webdriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_User_interaction {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	Actions action;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		
		action = new Actions(driver);
		
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
	}
	
	
	public void TC_01_Hover() {
//		driver.get("https://automationfc.github.io/jquery-tooltip/");
//		action.moveToElement(driver.findElement(By.id("age"))).perform();
//		sleepInsecond(1);
//		Assert.assertTrue(driver.findElement(By.xpath("//div[@role='tooltip']/div")).isDisplayed());
		
		
//		driver.get("https://www.myntra.com/");
//		action.moveToElement(driver.findElement(By.xpath("//a[@data-group='kids']"))).perform();
//		sleepInsecond(3);
//		driver.findElement(By.xpath("//a[text()='Girls Clothing']/parent::li//following-sibling::li/a[text()='Nightwear & Loungewear']")).click();
//		sleepInsecond(3);
//		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Kids Nightdresses']")).isDisplayed());
		
		
		driver.get("https://www.fahasa.com/");
		action.moveToElement(driver.findElement(By.xpath("//span[@class='icon_menu']"))).perform();
		sleepInsecond(3);
		action.moveToElement(driver.findElement(By.xpath("//span[text()='Đồ Chơi']"))).perform();
		sleepInsecond(3);
		driver.findElement(By.xpath("//div[@class='fhs_column_stretch']//a[text()='Ô Tô']")).click();
		sleepInsecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Ô Tô - Xe Buýt']")).isDisplayed());
		
	}

	public void TC_02_click_and_hold_01() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		String[] selectedTextExpected = {"1", "2", "3", "4", "5", "6", "7", "8"};
		
		List<WebElement> allElement = driver.findElements(By.cssSelector("#selectable>li"));
		
		// Chọn từ 1 đến 8
				// Click chọn tại số 1: clickAndHold(allElement.get(0))
				// Move chuột đến số 8: moveToElement(allElement.get(7))
				// Thả chuột trái ra: release()
		action.clickAndHold(allElement.get(0)).moveToElement(allElement.get(7)).release().perform();
		sleepInsecond(3);
		
		List<WebElement> allItemSelected = driver.findElements(By.cssSelector(".ui-selected"));
		
		// verify size = 8
		Assert.assertEquals(allItemSelected.size(), 8);
		
		// Tạo ra 1 arraylist để lưu lại selected text
		ArrayList<String> allItemSelectedText = new ArrayList<String>();
		
		// Kiểm tra text của các element được chọn là từ 1 đến 8
		for (WebElement item : allItemSelected) {
			allItemSelectedText.add(item.getText());
		}
		Object[] selectedTextActual = (Object[]) allItemSelectedText.toArray();
		Assert.assertEquals(selectedTextExpected, selectedTextActual);
		
	}
	

	public void TC_03_click_and_hold_random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		String[] selectedTextExpected = {"1", "4", "7", "12"};
		
		List<WebElement> allElement = driver.findElements(By.cssSelector("#selectable>li"));
		
		// Nhấn phím Ctrl xuống
		action.keyDown(Keys.CONTROL).perform();
		
		// Chọn các số: 1 / 4 / 7 / 12
		action.click(allElement.get(0)).click(allElement.get(3)).click(allElement.get(6)).click(allElement.get(11)).perform();
		
		// Thả nút Ctr ra
		action.keyUp(Keys.CONTROL).perform();
		
		List<WebElement> allItemSelected = driver.findElements(By.cssSelector(".ui-selected"));
		
		// verify size = 4
		Assert.assertEquals(allItemSelected.size(), 4);
		
		// Tạo ra 1 arraylist để lưu lại selected text
		ArrayList<String> allItemSelectedText = new ArrayList<String>();
		
		// Kiểm tra text của các element được chọn là các giá trị đúng
		for (WebElement item : allItemSelected) {
			allItemSelectedText.add(item.getText());
		}
		Object[] selectedTextActual = (Object[]) allItemSelectedText.toArray();
		Assert.assertEquals(selectedTextExpected, selectedTextActual);
	}
	
	@Test
	public void TC_04_double_click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement element = driver.findElement(By.xpath("//button[text()='Double click me']"));

		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
		sleepInsecond(3);
		action.doubleClick(element).perform();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='demo']")).isDisplayed());
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInsecond(long second) {
		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
