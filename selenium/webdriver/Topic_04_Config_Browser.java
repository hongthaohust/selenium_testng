package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_04_Config_Browser {
	/*
	 * Enviroment:
	 * Selenium: 4.12.1
	 * Chrome: latest
	 * Firefox: 117.0.1
	 * Edge Chromium: latest
	 */
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
  @Test
  public void TC_01_Run_On_Firefox() {
	  System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	  driver = new FirefoxDriver();
	  driver.get("https://facebook.com");
	  
	  driver.quit();
  }
  
  @Test
  public void TC_02_Run_On_Chrome() {
	  System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.get("https://facebook.com");
	  
	  driver.quit();
  }
  
  @Test
  public void TC_03_Run_On_Edge_Chromium() {
	  System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
	  driver = new EdgeDriver();
	  driver.get("https://facebook.com");
	  
	  driver.quit();
  }
}
