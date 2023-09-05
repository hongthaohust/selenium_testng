package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_01_check_enviroment_console {
	static WebDriver driver;

	public static void main(String[] args) {
		System.out.println("Hello");
		driver = new FirefoxDriver();
		driver.get("https://facebook.com/");
	}

}
