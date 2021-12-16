package homework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class copy {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// 1. 원본 file, 복사할 file 준비
		//File tulip = new File("d:/D_Other/Tulips.jpg");
		//File tulipCopy = new File("d:/D_Other/복사본_Tulips.jpg");
		System.out.println("마법의 소라고동에게 묻고싶은것을 말씀하세요.");
		System.out.print(">>");
		String user = scan.next();
		// 복사할 데이터를 담을 공간 
		int temp;
		try {
			//FileInputStream tu = new FileInputStream(new File("d:/D_Other/Tulips.jpg"));
			//FileOutputStream tu1 = new FileOutputStream(new File("d:/D_Other/복사본_Tulips.jpg"));
			FileInputStream tu = new FileInputStream("C:\\Users\\PC-14\\Desktop\\⁯\\고급자바\\파일복사/1.jpg");
			FileOutputStream tu1 = new FileOutputStream("d:/D_Other/1.jpg");
			
			//System.out.print("복사할 파일을 읽고있습니다.");
			while((temp=tu.read()) != -1) {
				//System.out.print(a);
				System.out.println(".\n.\n.\n.\n소라고동님께서 생각 중이십니다.\n.\n.\n.\n.");
				tu1.write(temp);
			}
			System.out.println();
			System.out.println("마법의 소라고동이 답하셨습니다.");
			tu.close();
			tu1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
