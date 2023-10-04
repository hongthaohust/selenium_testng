package webdriver;

import java.time.Duration;
import java.util.Date;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Webdriver_Wait_Part_VI_FluentWait {
	WebDriver driver;
	FluentWait<WebElement> fluentElement;
	
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
	}
	
	@Test
	public void TC_01_Element_Found() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		WebElement coundown = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		fluentElement = new FluentWait<WebElement>(coundown);
		
		fluentElement.withTimeout(Duration.ofSeconds(15))
			// Tần số mỗi 1s check 1 lần
			.pollingEvery(Duration.ofSeconds(1))
			// Nếu gặp exeption là tìm không thấy element sẽ bỏ qua
			.ignoring(NoSuchFieldException.class)
			// Kiểm tra điều kiện
			.until(new Function<WebElement, Boolean>() {
				public Boolean apply(WebElement element) {
					// Kiểm tra điều kiện countdown = 00
					Boolean flag = element.getText().endsWith("00");
					System.out.println("Time = " + element.getText());
					// Return giá trị cho function apply
					return flag;
				}
			});
	}
	
	
	public void TC_02_() {
	}
	
	
	public void TC_03_() {
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	
	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}

}
