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
	
	String[] months = {"January", "March", "June"};
	String[] newMonths = {"March", "January", "December", "April", "November"};
	

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
		
		//jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}
	
	
	public void TC_01_DropDown_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemDropdown("//span[@id='number-button']", "ul#number-menu div", "5");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[text()='5']")).isDisplayed());
		
		selectItemDropdown("//span[@id='number-button']", "ul#number-menu div", "10");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[text()='10']")).isDisplayed()); // Lỗi
		
		selectItemDropdown("//span[@id='number-button']", "ul#number-menu div", "15");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[text()='15']")).isDisplayed());
		
		selectItemDropdown("//span[@id='number-button']", "ul#number-menu div", "1");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[text()='1']")).isDisplayed());
		
	}

	
	public void TC_02_DropDown_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectItemDropdown("//i[@class='dropdown icon']", "div.item>span", "Elliot Fu");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Elliot Fu']")).isDisplayed());
		
		selectItemDropdown("//i[@class='dropdown icon']", "div.item>span", "Justen Kitsune");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Justen Kitsune']")).isDisplayed());
		
		selectItemDropdown("//i[@class='dropdown icon']", "div.item>span", "Jenny Hess");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Jenny Hess']")).isDisplayed());
	}
	
	
	public void TC_03_DropDown_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectItemDropdown("//span[@class='caret']", "ul.dropdown-menu a", "Third Option");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(text(),'Third Option')]")).isDisplayed());
		
		selectItemDropdown("//span[@class='caret']", "ul.dropdown-menu a", "First Option");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(text(),'First Option')]")).isDisplayed());
		
	}
	
	
	public void TC_04_DropDown_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		selectItemEditableDropdown("//input[@class='search']", "div[role='option'] span", "Algeria");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@role='combobox']/div[text()='Algeria']")).isDisplayed());
		
		selectItemEditableDropdown("//input[@class='search']", "div[role='option'] span", "Benin");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@role='combobox']/div[text()='Benin']")).isDisplayed());
		
		selectItemEditableDropdown("//input[@class='search']", "div[role='option'] span", "Bangladesh");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@role='combobox']/div[text()='Bangladesh']")).isDisplayed());
		
	}
	
	@Test
	public void TC_05_DropDown_Angular() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		
		// Mũi tiêm thứ mấy
		selectItemDropdownAngular("//div[text()='Đăng kí mũi tiêm thứ']/parent::div/following-sibling::span[last()]", "div[role='option'] span", "Mũi tiêm tiếp theo");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='form-group']//span[text()=' Mũi tiêm tiếp theo ']")).isDisplayed());
		
		selectItemDropdownAngular("//div[text()='Đăng kí mũi tiêm thứ']/parent::div/following-sibling::span[last()]", "div[role='option'] span", "Mũi tiêm thứ nhất");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='form-group']//span[text()=' Mũi tiêm thứ nhất ']")).isDisplayed());
		
		// Giới tính
		selectItemDropdownAngular("//div[text()='Giới tính']/parent::div/following-sibling::span[last()]", "div[role='option']  span", "Nữ");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='form-group']//span[text()=' Nữ ']")).isDisplayed());
		
		selectItemDropdownAngular("//div[text()='Giới tính']/parent::div/following-sibling::span[last()]", "div[role='option']  span", "Nam");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='form-group']//span[text()=' Nam ']")).isDisplayed());
		
		
		// Tỉnh/Thành phố
		selectItemDropdownAngular("//div[text()='Tỉnh/Thành phố']/parent::div/following-sibling::span[last()]", ".ng-dropdown-panel div[role='option']", "Tỉnh Hà Giang");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='form-group']//span[text()='Tỉnh Hà Giang']")).isDisplayed());
		
		selectItemDropdownAngular("//div[text()='Tỉnh/Thành phố']/parent::div/following-sibling::span[last()]", ".ng-dropdown-panel div[role='option']", "Tỉnh Bắc Kạn");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='form-group']//span[text()='Tỉnh Bắc Kạn']")).isDisplayed());
		
		selectItemDropdownAngular("//div[text()='Tỉnh/Thành phố']/parent::div/following-sibling::span[last()]", ".ng-dropdown-panel div[role='option']", "Tỉnh Thái Nguyên");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='form-group']//span[text()='Tỉnh Thái Nguyên']")).isDisplayed());
		
	}
	
	
	
	public void TC_06_DropDown_Multiple_Select() {
		driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
		sleepInSecond(1);
		
		selectMultiItemDropdown("(//button[@class='ms-choice'])[1]","(//div[@class='ms-drop bottom'])[1]//span", months);
		Assert.assertTrue(areItemSelected(months));
		driver.navigate().refresh();
		
		selectMultiItemDropdown("(//button[@class='ms-choice'])[1]","(//div[@class='ms-drop bottom'])[1]//span", newMonths);
		Assert.assertTrue(areItemSelected(newMonths));
	}
	
	// hàm common (dùng chung) - không fix cứng dữ liệu trong hàm
	public void selectItemDropdown(String parentXpath, String childCss, String expectItem) {
		// 1. click vào cha -> xổ ra list con
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSecond(3);
		
		// 2. chờ cho các element được load ra
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCss)));
		
		// 3. Lấy ra hết tất cả item đưa vào list
		List<WebElement> childItems = driver.findElements(By.cssSelector(childCss));
		
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
	
	public void selectItemDropdownAngular(String parentXpath, String childCss, String expectItem) {
		// 1. click vào cha -> xổ ra list con
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSecond(1); //bắt buộc ???
		
		// 2. chờ cho các element được load ra
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCss)));
		
		// 3. Lấy ra hết tất cả item đưa vào list
		List<WebElement> childItems = driver.findElements(By.cssSelector(childCss));
		
		// 4. Duyệt qua List, lấy ra từng item (for each)
		for (WebElement actualItem : childItems) {
			// 5. Mỗi lần sẽ kiểm tra item đó có bằng item cần chọn không
			if(actualItem.getText().trim().equals(expectItem)) {
				// 6. Nếu tìm thấy item thì scroll đến item đó
				if(!actualItem.isDisplayed()) {
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", actualItem);
					sleepInSecond(1);
				}
				// 7. Click vào item đó
				clickByJS(actualItem);
				// 8. Thoát khỏi vòng lặp
				break;
			}
			
		}
		
	}
	
	public void selectItemEditableDropdown(String parentXpath, String childCss, String expectItem) {
		//0. Clear input cha
		driver.findElement(By.xpath(parentXpath)).clear();
		sleepInSecond(1);
		
		// 1. Nhập text tìm kiếm
		driver.findElement(By.xpath(parentXpath)).sendKeys(expectItem);
		sleepInSecond(1);
		
		// 2. chờ cho các element được load ra
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCss)));
		
		// 3. Lấy ra hết tất cả item đưa vào list
		List<WebElement> childItems = driver.findElements(By.cssSelector(childCss));
		
		// 4. Duyệt qua List, lấy ra từng item (for each)
		for (WebElement actualItem : childItems) {
			// 5. Mỗi lần sẽ kiểm tra item đó có bằng item cần chọn không
			if(actualItem.getText().trim().equals(expectItem)) {
				sleepInSecond(1);
				// 6. Nếu tìm thấy item và item bị che thì scroll đến item đó
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", actualItem);
				sleepInSecond(1);
				// 7. Click vào item đó
				actualItem.click();
				// 8. Thoát khỏi vòng lặp
				break;
			}
			
		}
		
	}
	
	// Hàm chọn nhiều item trong dropdown
	public void selectMultiItemDropdown(String parentXpath, String childXpath, String[] expectedValueItem) {
		// 1: click vào cái dropdown cho nó xổ hết tất cả các giá trị ra
		driver.findElement(By.xpath(parentXpath)).click();

		// 2: chờ cho tất cả các giá trị trong dropdown được load ra thành công
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

		List<WebElement> allItems = driver.findElements(By.xpath(childXpath));

		// Duyệt qa hết tất cả các phần tử cho đến khi thỏa mãn điều kiện
		for (WebElement childElement : allItems) {
			// "January", "April", "July"
			for (String item : expectedValueItem) {
				if (childElement.getText().equals(item)) {
					// 3: scroll đến item cần chọn (nếu như item cần chọn có thể nhìn thấy thì ko cần scroll)
					//jsExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement); // Không work???????????
					//sleepInSecond(1);

					// 4: click vào item cần chọn
					childElement.click();
					sleepInSecond(1);
					
					List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
					System.out.println("Item selected = " + itemSelected.size());
					if (expectedValueItem.length == itemSelected.size()) {
						break;
					}
				}
			}
		}
	}
	
	// Hàm kiểm tra các item đã chọn trong dropdown
	public boolean areItemSelected (String[] months) {
		List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
		int numberItemSelected = itemSelected.size();
		
		String allItemSelectedText = driver.findElement(By.xpath("(//button[@class='ms-choice']/span)[1]")).getText();
		System.out.println("Text da chon = " + allItemSelectedText);
		
		if(numberItemSelected <= 3 && numberItemSelected > 0) {
			boolean status = true;
			for (String item : months) {
				if (!allItemSelectedText.contains(item)) {
					status = false;
					return status;
				}
			}
			return status;
		} else if(numberItemSelected >=12) {
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='All selected']")).isDisplayed();
		} else if(numberItemSelected > 3 && numberItemSelected < 12) {
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='" + numberItemSelected + " of 12 selected']")).isDisplayed();
		} else {
			return false;
		}
	}
	
	// Lấy ra text nằm trong element bị ẩn
  	public String getHiddenText(String cssLocator) {
		return (String) jsExecutor.executeScript("return document.querySelector(\""+cssLocator+"\").textContent");
	}
  	
  	// Click by JS: chỉ click dk vào item bị ẩn, không click dk vào item bị disable
  	public void clickByJS(WebElement element) {
		jsExecutor.executeScript("arguments[0].click();", element);
	}
	
	// Sleep
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
