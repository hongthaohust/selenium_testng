package webdriver;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_locator_selenium_java {
	WebDriver driver;
//	String projectPath = System.getProperty("user.dir");
//	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
//		if (osName.contains("Windows")) {
//			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		} else {
//			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
//		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_locator() throws InterruptedException {
		driver.get("https://test.med247.me/users/sign_in");
		
		// ID
		driver.findElement(By.id("user_login")).sendKeys("letan_test_prefix@med247.co");
		Thread.sleep(3000);
		driver.findElement(By.id("user_password")).sendKeys("Med247@");
		Thread.sleep(3000);
		
		// Class
//		driver.findElement(By.className("btn primary d-block w-100 text-uppercase fw-medium fs-14")).click();
//		Thread.sleep(3000);
		// Name
		driver.findElement(By.name("commit")).click();
		Thread.sleep(3000);
		 
		// Link Text
		driver.findElement(By.linkText("Lịch sử khám")).click();
		Thread.sleep(3000);
		
		// Partial Link Text
		driver.findElement(By.partialLinkText("lịch hẹn")).click();
		Thread.sleep(3000);
		
		// Tagname
		Dimension textboxSize = driver.findElement(By.tagName("input")).getSize();
		System.out.println(textboxSize);
		
		// Css:
		
		driver.findElement(By.cssSelector("div[class='box-tool']")).click();
		Thread.sleep(3000);
		
		
		// Xpath: Cú pháp = //tagname[@attribute="value"] => có thể đi ngược từ dưới lên, không có ends-with
		
		// Contains: text có thể ở đầu/giữa/cuối với những text không phải dạng nested
			// Tương đối: //tagname[contains(@attribute,'value')]
					  	  //tagname[contains(text(),'value')]
			// Tuyệt đối: //tagname[@attribute='value']
		  			  	  //tagname[text()='value']
		
		// Starts-with: text phải ở đầu chuỗi
						//tagname[starts-with(@attribute,'value')]
						//tagname[starts-with(text(),'value')]

		// Phân biệt các loại contains
		// Text() =
			// Chỉ nằm trong 1 node, không có xuống dòng, không có khoảng trắng, không có nested
		
		// contains(text(),'')
			// Text nằm trong 1 node, nếu là dạng nested thì text phải ở đầu chuỗi
			// text nằm trong child node thì không lấy được
			// text có xuống dòng, khoảng trắng thì vẫn lấy được
			// Chỉ lấy ra được 1 giá trị đầu tiên
		
		// contains(.,'')
			// Text nằm trong 1 node, nếu là dạng nested thì text ở bất kỳ vị trí nào
			// text nằm trong child node thì vẫn lấy được
			// text có xuống dòng, khoảng trắng thì vẫn lấy được
			// Lấy ra được tất cả giá trị
		
		// contains(string(),'') - giống contains(.,'')
		
		//concat() - dùng khi text vừa có dấu nháy đơn, vừa có dấu nháy đôi
		
		// text index - hạn chế dùng
		
		// last() : lấy ra phần tử cuối cùng trong số các phần tử đồng cấp
		
		// position() : lấy ra phần tử có vị trí được đánh dấu trong số các phần tử đồng cấp
			// cấu trúc: position()= 'stt'
		
		// outside parent: bọc parent lại sau đó mới gọi đến last() hoặc position
			// cấu trúc: (parent)[last()] hoặc (parent)[position()='stt']
		
		// Axes
			// ancestor (tổ tiên)
			// parent (cha)
			// preceding (bác) => không đúng
			// following (chú) => không đúng
			// preceding-sibling (anh của node hiện tại)
			// following-sibling (em của node hiện tại)
			// child (con)
			// descendant (con + cháu của node hiện tại)
		
		
		// CSS: Cú pháp = tagname[attribute="value"] -> Lưu ý: chỉ làm việc với attribute, không làm việc với text. Không đi ngược được.
			// # = id (vd: #email | input#email | input[id='email'])
			// . = class (vd: .input-box | div.input-box | div[class='input-box') -> Lưu ý: thêm dấu "chấm" nếu class có chứa khoảng trắng
			// * = contains (vd: input[title*='Sign up'])
			// ^ = starts-with (vd: input[title^='Sign up'])
			// $ = end-with (vd: input[title$='newsletter'])
			// space > space = child(/) -> đi qua 1 node
			// sapce = đi nhiều node
			// + = following-sibling
			// attribute: bỏ // và @
			// [A][B] = A and B (vd: input[name='login[username]']#email | input[name='login[username]'][id= 'email'])
			// A , B = A or B (vd: input#email,[class*='input-text'])
			// nth-child(index) = index (vd: div[class='footer']>div:nth-child(1))
			// first-child
			// last-child
		// https://prnt.sc/8Ki-iHx0vIRg
		// https://prnt.sc/RCd935GzPKK5
		// https://prnt.sc/dgWmtxr1KDEt
		
		// Console (Selenium command)
			// Cho phép copy paste dùng lệnh: allow pasting
			// Verify:
				// Xpath = $x
				// Css = $$
				// JQuery = $ - Lưu ý: JQuery chỉ bắt với Css, không bắt được Xpath
		
		
		//Perfomance: https://prnt.sc/B437yR5838LS
		
		// Nhân Chromium: Cốc cốc, Opera, Edge, Chrome, Brave
		
		// Selenium Component gồm:
			// Selenium IDE: 
				// Cũ (extension) ở trên trình duyệt Firefox cũ
				// Mới (extension) ở trên trình duyệt Chrome/Firefox mới
			// Selenium RC (Remote control) - Deprecated (lỗi thời)
			// Selenium WebDriver *đang được sử dụng hiện nay
			// Selenium Grid *đang được sử dụng hiện nay
		
		// Selenium Bindings: Java, C#, Phython, Javascript, Ruby
		
		// Tiêu chuẩn testcase Automation = AAA (Arrange - Action - Assert)
		// @BeforeClass = Pre-condition (Arrange)
		// @Test = Step (Action)
		// @Test = Kiểm tra, verify (Assert)
		
		
		// Tips: truyền vào chuỗi chứa dấu "" thì có cách nào để nó nhận kí tự "" đó -> Truyền vào ký tự "\" trước dấu nháy đôi (vd: "return \"Tôi cảm ơn bạn\" rất nhiều!")
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
