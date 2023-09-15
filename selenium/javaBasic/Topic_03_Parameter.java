package javaBasic;

public class Topic_03_Parameter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Showname("John Money");

	}
	
	public static void Showname(String name) {
		System.out.println("Xin chào " + name);
	}

}



/*
 * Hàm main() bắt buộc phải là static mới chạy được
 * void: không cần trả về dữ liệu, hàm chạy xong là xong
 * Các kiểu dữ liệu còn lại (string, int, long, double,...) phải trả về dữ liệu -> return a;
 * Nguyên tắc của lập trình: để bảo mật dữ liệu không được lấy dữ liệu trực tiếp từ tên biến, chỉ được lấy từ hàm get() hoặc set() -> Đóng gói dữ liệu
 *
 *
 *
 *
 */