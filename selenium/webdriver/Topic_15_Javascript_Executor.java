package webdriver;

import java.time.Duration;
import java.util.List;
import java.util.Random;

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

public class Topic_15_Javascript_Executor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait expliciWait;
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
		jsExecutor = (JavascriptExecutor) driver;
		expliciWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();

		// //div[text()='Tiếng Pháp căn bản dành cho người mới bắt đầu 3']
	}
	
	
	public void TC_01_clickHiddenElement() {
		driver.get("https://www.myntra.com/");
		
		WebElement content = driver.findElement(By.xpath("//a[text()='Laundry Bags']"));
		
		jsExecutor.executeScript("arguments[0].click();", content);
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Laundry Bags & Baskets']")).isDisplayed());
	}

	
	public void TC_02_Demo_Javascript_demoguru99() {
		// 1. vào trang "https://demo.guru99.com"
		
		navigateToUrlByJS("https://demo.guru99.com/");
		
		// 2. Click link "My Account" trên header để tới trang đăng nhập
		
		clickToElementByJS("");
		
		// 3. Click "Create an account" để tới trang đăng ký
		
		clickToElementByJS("");
		
		// 4. Nhập thông tin hợp lệ
		
		
		
		// 5. Click Register
		
		// 6. Verify message xuất hiện "Thank you for registering with Main Website Store"
		
		// 7. Logout khỏi hệ thống
		
		// 8. Kiểm tra hệ thống navigate về Homepage sau khi logout (Sử dụng isDisplayed để check wait)
	}
	
	@Test
	public void TC_03_Demo_Javascript_Med247() {
		String user_login = "letan_test_prefix@med247.co";
		String password = "Med247@";
		int rand = random();
		String username = "Automation"+rand;
		String dateOfBirth = "12/08/1997";
		String phoneNumber ="035511"+rand;
		// 1. vào trang "https://test.med247.me/users/sign_in"
		
		navigateToUrlByJS("https://test.med247.me/users/sign_in");
		
		// 2. Nhập thông tin đăng nhập
		sendkeyToElementByJS("//input[@id='user_login']",user_login);
		sendkeyToElementByJS("//input[@id='user_password']",password);
		
		// 3. Click button Đăng nhập
		
		clickToElementByJS("//input[@type='submit']");
		
		// 4. Verify đăng nhập thành công chứa text "Bảng điều khiển"
		
		sleepInSecond(3);
		Assert.assertTrue(getElement("//div[contains(text(),'Bảng điều khiển')]").isDisplayed());
		
		// 5. Click "+Bệnh nhân mới"
		clickToElementByJS("//ul[@class='nav navbar-nav ml-auto flex-row']//div[@class='d-none d-lg-block'][3]//a");
		
		// 6. Nhập thông tin bệnh nhân: chủ tài khoản
		sendkeyToElementByJS("//label[text()='Họ Tên ']/following-sibling::input",username);
		
			// Chọn ngày sinh
		// sendkeyToElementByJS("//input[@id='patient_birthday']",dateOfBirth); //C1: Sendkey - đang không work
		
			// Chọn dropdown giới tính = Nữ
		selectItemDropdown("//span[@id='select2-patient_gender-container']","//ul[@id='select2-patient_gender-results']/li","Nữ");
		
		sendkeyToElementByJS("//label[text()='Điện thoại ']/following-sibling::input",phoneNumber);
			// chọn Tỉnh/TP
		selectItemDropdown("//span[@id='select2-patient_city_id-container']","//span[@class='select2-results']//li","Thành phố Hải Phòng");
			//Chọn Quận/huyện
		selectItemDropdown("//span[@id='select2-patient_district_id-container']","//span[@class='select2-results']//li","Huyện Vĩnh Bảo");
		
		sleepInSecond(5);
		// 7. Click Tạo và verify tạo thành công
		
		// 8. Logout khỏi hệ thống
		
		// 9. Kiểm tra hệ thống navigate về Homepage sau khi logout (Sử dụng isDisplayed để check wait)
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Browser
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
		sleepInSecond(3);
	}

	// Element
	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
		sleepInSecond(3);
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}
	
	public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
		jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}
	
	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}
	
	public String getAttributeInDOM(String locator, String attributeName) {
		return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		return status;
	}
	
	// hàm common (dùng chung) - không fix cứng dữ liệu trong hàm
	public void selectItemDropdown(String parentXpath, String childXpath, String expectItem) {
		// 1. click vào cha -> xổ ra list con
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSecond(1);
		
		// 2. chờ cho các element được load ra
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		
		// 3. Lấy ra hết tất cả item đưa vào list
		List<WebElement> childItems = driver.findElements(By.xpath(childXpath));
		
		// 4. Duyệt qua List, lấy ra từng item (for each)
		for (WebElement actualItem : childItems) {
			// 5. Mỗi lần sẽ kiểm tra item đó có bằng item cần chọn không
			if(actualItem.getText().trim().equals(expectItem)) {
				sleepInSecond(1);
				// 6. Nếu tìm thấy item thì scroll đến item đó
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", actualItem);
				sleepInSecond(1);
				// 7. Click vào item đó
				actualItem.click();
				// 8. Thoát khỏi vòng lặp
				break;
			}
			
		}
		
	}
		
	public void select_DateofBirth(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		
	}
		

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
	
	public int random() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}


}
