package javaBasic;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_WebElement_API {
	WebDriver driver;
	WebElement element = null;
	List<WebElement> elements = null;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	  /*Loại Element:
	   * Textbox
	   * TextArea
	   * Dropdown List
	   * Radio Button
	   * Alert
	   * Header
	   * Tooltip
	   * Upload
	   * Link
	   * Text
	   * .....
	   */
	 

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
	}
	
	@Test
	public void TC_01() {
		driver.get("https://facebook.com/");
		
		// Khi mình chỉ thao tác với element này 1 lần
		driver.findElement(By.cssSelector("")).click();
		
		// Khi biến này được sử dụng nhiều lần -> nên khai báo thành 1 biến
		element = driver.findElement(By.cssSelector(""));
		element.click();
		element.clear();
		//...
		
		
		// FUI: function UI
		
		element.clear(); // Xóa dữ liệu trong 1 fiels (editable - cho phép nhập): textbox, textarea/dropdown ***
		
		element.sendKeys(""); // Nhập dữ liệu trong 1 fiels (editable - cho phép nhập): textbox, textarea/dropdown ***
		
		element.click(); // Thực hiện thao tác click vào 1 element bất kỳ ***
		
		driver.findElement(By.cssSelector(""));// Tìm kiếm và trả về 1 element ***
		
		elements = driver.findElements(By.cssSelector(""));// Tìm nhiều element ->Lưu ý: chỉ làm việc với element đầu tiên
		
		element.getAttribute("placeholder"); // Lấy ra giá trị nằm trong 1 attribute nào đó của element ***
		
		element.getText(); // Lấy ra text của element ***
		
		//element.getScreenshotAs(arg0); // Chụp hình và add vào report
		
		element.getTagName(); // Lấy ra tên thẻ của element - lấy ra locator
		
		element.isDisplayed(); // Kiểm tra hiển thị: thường đi kèm assertTrue/False/Equals ***
		
		element.isSelected(); // Kiểm tra 1 element đã được chọn: checkbox/radio button ***
		
		element.isEnabled(); // Kiểm tra 1 element có thể thao tác được không
		
		element.submit(); // Chỉ làm việc được với element nằm trong form
		
		// GUI: font, size, value, location, position,...
		element.getCssValue("font-size"); // Lấy ra css của element
		
		element.getLocation();
		
		element.getSize();
		
		element.getRect();
		
		
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
