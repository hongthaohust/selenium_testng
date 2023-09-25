package webdriver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
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
	WebElement element;
	Actions action;
	JavascriptExecutor jsExecutor;
	String osName = System.getProperty("os.name");
	String projectPath = System.getProperty("user.dir");
	String javascriptPath = projectPath + "\\dragAndDrop\\drag_and_drop_helper.js";
	String jQueryPath = projectPath + "\\dragAndDrop\\jquery_load_helper.js";
	
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
	
	
	public void TC_04_double_click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		element = driver.findElement(By.xpath("//button[text()='Double click me']"));

		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
		sleepInsecond(3);
		action.doubleClick(element).perform();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='demo']")).isDisplayed());
	}
	
	
	public void TC_05_right_click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		// Right click to button: contextClick()
		element = driver.findElement(By.xpath("//span[text()='right click me']"));
		action.contextClick(element).perform();
		
		// Hover vào button Quit
		element = driver.findElement(By.cssSelector(".context-menu-icon-quit"));
		action.moveToElement(element).perform();
		
		// Verify Quit có hover/visible status
		String quitClassAttribute = element.getAttribute("class");
		Assert.assertTrue(quitClassAttribute.contains("context-menu-hover"));
		Assert.assertTrue(quitClassAttribute.contains("context-menu-visible"));
	}
	
	
	public void TC_06_Drag_and_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		
		WebElement sourceCircle = driver.findElement(By.cssSelector("div#draggable"));
		WebElement targetCircle = driver.findElement(By.cssSelector("div#droptarget"));
		
//		action.dragAndDrop(sourceCircle, targetCircle).perform(); // Cách 1
		
		action.clickAndHold(sourceCircle).moveToElement(targetCircle).release().perform(); // Cách 2
		sleepInsecond(3);
		
		Assert.assertEquals(targetCircle.getText(), "You did great!");
	}
	
	@Test
	public void TC_07_Drag_and_Drop_HTML5() throws IOException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
		String sourceCss = "div#column-a";
		String targetCss = "div#column-b";
		
		String java_script = getContentFile(javascriptPath);
		
		// Inject Jquery lib to site - nếu website chưa có jQuery
//		 String jqueryscript = getContentFile(jQueryPath);
//		 jsExecutor.executeScript(jqueryscript);
		
		// A to B
		java_script = java_script + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
		jsExecutor.executeScript(java_script);
		sleepInsecond(3);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-a']/header[text()='B']"));
		
		// B to A
		java_script = "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
		jsExecutor.executeScript(java_script);
		sleepInsecond(3);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-a']/header[text()='A']"));
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
	
	public String getContentFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}
	
	public boolean isElementDisplayed(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if(element.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

}
