package webdriver;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_DropdownList_Custom {
	WebDriver driver;
	WebDriverWait expliciWait;
	JavascriptExecutor jsExecutor;
	
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
		expliciWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_Custom_Dropdown1() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "5");
		sleepInSecond(3);
		
		selectItemDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "11");
		sleepInSecond(3);
		
		selectItemDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "15");
		sleepInSecond(3);
		
		selectItemDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "1");
		sleepInSecond(3);
		
	}

	@Test
	public void TC_02() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		
	}

	// hàm common (dùng chung) - không fix cứng dữ liệu trong hàm
	public void selectItemDropdown(String parentXpath, String childXpath, String expectItem) {
		// 1. click vào cha -. xổ ra list con
		driver.findElement(By.xpath(parentXpath)).click();
		
		// 2. chờ cho các element được load ra
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		
		// 3. Lấy ra hết tất cả item đưa vào list
		List<WebElement> childItems = driver.findElements(By.xpath(childXpath));
		
		// 4. Duyệt qua List, lấy ra từng item (for each)
		for (WebElement actualItem : childItems) {
			// 5. Mỗi lần sẽ kiểm tra item đó có bằng item cần chọn không
			if(actualItem.getText().trim().equals(expectItem)) {
				// 6. Nếu tìm thấy item thì scrol đến item đó
				jsExecutor.executeScript("arguments[0].scrollIntoView(true)", actualItem);
				sleepInSecond(1);
				// 7. Click vào item đó
				actualItem.click();
				
				// 8. Thoát khỏi vòng lặp
				break;
			}
			
		}
		
	}
	
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
