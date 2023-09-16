package javaBasic;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_04_WebBrowser {

	public static void main(String[] args) {
		WebDriver driver;
		String url = "https://facebook.com";
		driver = new FirefoxDriver();
		
		driver.get(url); // Mở 1 tab ***
		
		driver.getCurrentUrl(); // Lấy ra url của trang hiện tại ***
		
		driver.getPageSource(); //mã code của trang hiện tại
		
		driver.getTitle(); // Lấy title của trang hiện tại ***
		
		driver.getWindowHandle(); // ID tab hiện tại ***
		
		driver.getWindowHandles(); // Lấy tất cả ID ***
		
		driver.close(); // đóng tab hiện tại
		
		driver.quit(); // đóng cả trình duyệt hiện tại ***
		
		driver.findElement(By.xpath("")); // Tìm 1 element ***
		
		driver.findElements(By.xpath("")); // Tìm nhiều element ***
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // thời gian để chờ tìm kiếm element ***
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30)); // Thời gian chờ cho page tải xong
		
		driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(30)); // Thời gian chờ để đoạn code JS thực thi xong
		
		driver.manage().window().maximize(); // Full hết windows ***
		
		
		driver.navigate().back(); // Back về trang trước
		
		driver.navigate().refresh(); // Tải lại trang (F5)
		
		driver.navigate().forward(); // Chuyển tiếp tới trang trước
		
		driver.navigate().to(url); // Mở ra 1 url
		
		
		driver.switchTo().alert(); // Alert ***
		
		driver.switchTo().frame("facebook"); //Frame/Iframe ***
		
		driver.switchTo().window("aaaa-6564-76574-47665-6547"); //Windows/Tab ***

	}

}
