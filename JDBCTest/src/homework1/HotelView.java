package homework1;

import java.util.Scanner;

public class HotelView {
	public static void main(String[] args) {
		new HotelView().View();
	}
	
	public void View() {
		Scanner scan = new Scanner(System.in);
		HotelControl con = new HotelControl();
		
		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");
		System.out.println();
		System.out.println("*******************************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인   2.체크아웃  3.객실상태  4.업무종료");
		System.out.println("*******************************************");
		System.out.print("메뉴선택=> ");
		
		while(true) {
			int select = Integer.parseInt(scan.next());
			switch(select) {
				case 1: con.checkIn();   // 체크인
					break;
				case 2: con.checkOut();   // 체크아웃
					break;
				case 3: con.roomCheck();  // 객실상태
					break;
				case 4:
					System.out.println("**************************");
					System.out.println("호텔 문을 닫았습니다.");
					System.out.println("**************************");
					System.exit(0);
			}
		}
	}
}
