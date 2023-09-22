package javaBasic;

public class Topic_07_Radio_Checkbox_Alert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/* Radio button / Checkbox:
		 * Dạng default: làm việc với thẻ input (dùng thẻ input có thể thực hiện được action check/uncheck và có thể kiểm tra được nó được select hay chưa)
		 * Dạng custom: nếu thẻ input ở dạng hidden thì phải làm việc với 2 locator:
		 * - Thẻ input: để kiểm tra xem checkbox/radio đã được check/uncheck thành công
		 * - Thẻ label/div/span...: để lấy được 1 matching note tương ứng với checkbox/radio button được chọn
		 * Hoặc cách 2: click vào thẻ input dùng lệnh JS 
		 * Nếu thẻ input không bị hidden thì làm việc bình thường
		 */
		
		/* Alert: của trình duyệt -> không bắt element được
		 * Có 3 loại alert:
		 * - Loại 1: Alert Accept: chỉ cho chọn yes (code js = alert("title alert"))
		 * - Loại 2: Alert Confirm: cho chọn yes/no (code js = Confirm("title alert"))
		 * - Loại 3: Alert Input: cho phép nhập text (code js = prompt("title alert"))
		 * 
		 * Các action với alert:
		 * 1. Click để hiển thị alert
		 * 2. Switch vào alert
		 * 3. Get text của alert -> kiểm tra text có đúng không
		 * 4. Send text vào alert (nếu là alert input)
		 * 5. Accept alert
		 * 6. Cancel alert
		 */
	}

}
