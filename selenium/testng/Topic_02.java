package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_02 {
	WebDriver driver;
  
  @BeforeClass
  public void beforeClass() {
	  System.out.println("Run BeforeClass");
	  driver = new FirefoxDriver();
	  
	  //Assert.assertTrue(false);
  }
  
  @Test (groups = "Annotation")
  public void TC_01() {
	  System.out.println("Run testcase 01");
  }
  @Test (groups = "Annotation")
  public void TC_02() {
	  System.out.println("Run testcase 02");
  }
  @Test
  public void TC_03() {
	  System.out.println("Run testcase 03");
  }
  
  @AfterClass (alwaysRun = true)
  public void afterClass() {
	  System.out.println("Run AfterClass");
	  driver.quit();
  }

}
