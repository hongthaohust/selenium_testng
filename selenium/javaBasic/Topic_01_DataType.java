package javaBasic;

public class Topic_01_DataType {
	/* Kiểu dữ liệu nguyên thủy: 
	 * byte/short/int/longfloat/double: Số
	 * char: Ký tự
	 * boolean: logic (true/false)
	
	 * Kiểu dữ liệu tham chiếu:
	 * String: chuỗi
	 * Array: mảng
	 * Class/Object
	 * Collect: List/ Set
	*/

	public static void main(String[] args) {
		
		// Chuỗi
		String name = "Nguyễn Văn A";
		
		//Ký tự 
		char c = 's';
		
		// Số nguyên: byte/short/int/long
		int pupilNumber = 100;
		long timeout = 3000;
		
		// Số thực: fload/ double
		float price = 123.123f;
		double money = 321.321d;
		
		// Logic: boolean
		boolean sex = true;
		
		System.out.println(name);
		System.out.println(pupilNumber);
		System.out.println(timeout);
		System.out.println(price);
		System.out.println(money);
		System.out.println(sex);
		System.out.println(c);

	}
	

}
