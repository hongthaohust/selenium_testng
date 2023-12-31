package javaBasic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Topic_06_TextBox_TextArea_dropdownList {
	static WebDriver driver;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Enter text into text field:
		driver.findElement(By.cssSelector("")).sendKeys("");
		
		//Xuống dòng: \n
		
		// Trick: bắt những case không khả thi 
		//(VD: textbox disable không cho nhập nhưng tester xóa attribute có giá trị disable đi.
		// -> Sau đó nhập text và gửi lên server
		// Nếu server vẫn chấp nhận thì là Bug)
		
		
		// DropdownList
		/* Dạng default (thẻ select - option)
		 * Dạng Custom (thẻ div, span, ul, li, ...)*/
		// Selenium chỉ hỗ trợ cho dropdown dạng default (thẻ sellect - option)
		// Thư viện cho front-end: Jquery, KendoUI, Bootstrap, Argular, ... -> hỗ trợ viết css
		

		// Angular dropdown
		/* Get attribute of aria-label - Only word for Chrome
		 * Get attribute of value - Work for Chrome/Firefox
		 * Get by Javascript Executor (querySelector) - Work for Chrome/Firefox
		 */
		
	}
	

}
