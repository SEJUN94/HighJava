package home;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import home.hotelVO;

public class homework1 {
	private Scanner scan;
	private Map<String, hotelVO> hotelMap;
	
	public homework1() {
		scan = new Scanner(System.in);
		hotelMap = new HashMap<>();
	}
		// 메뉴를 출력하는 메서드
		public void displayMenu(){
			System.out.println();
			System.out.println("*******************************************");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
			System.out.println("*******************************************");
			System.out.print(" 번호입력 >> ");		
		}
		
	public void hotelStart(){
		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");
		System.out.println();
		
		while(true) {
			
			displayMenu();  // 메뉴 출력
			
			int menuNum = Integer.parseInt(scan.next()); //메뉴 번호 입력
			
			switch(menuNum) { 
			case 1: checkin();    // 체크인
			break;	
			case 2: checkout();   // 체크아웃
			break;
			case 3: roomCheck();  // 객실 상태
			break;	
			case 4: 
				System.out.println("**************************"); 
				System.out.println("호텔 문을 닫았습니다.");
				System.out.println("**************************");
				System.exit(0);
			} //switch문 
		} //while문
	}
	
	// 3.객실 상태
	private void roomCheck() {
		Set<String> keyset = hotelMap.keySet();
		
		if(keyset.size() == 0) {
			System.out.println("투숙객 이 없습니다.");
		} else {
			Iterator<String> it = keyset.iterator();
			
			System.out.println();
			while(it.hasNext()) {
				String roomNum = it.next();
				hotelVO vo = hotelMap.get(roomNum);
				System.out.println("방번호 : " +vo.getRoomNum() + "투숙객 : " + vo.getName());
			}
		}
	}
	// 2.호텔 체크 아웃
	private void checkout() {
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		String roomNum = scan.next();
		if(hotelMap.remove(roomNum) == null) {
			System.out.println(roomNum+"방에는 체크인한 사람이 없습니다.");
		} else {
			System.out.println("체크아웃 되었습니다.");
		}
}
	// 1. 호텔 체크인 메서드
	private void checkin() {
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		String roomNum = scan.next();
		
	
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 => ");
		String name = scan.next();
		// 예약한 사람인지 검사
		// get()메서드로 값을 가져올 때 가져올 자료가 없으면 null 반환
		if(hotelMap.get(roomNum) != null) {
			System.out.println(roomNum + "방에는 이미 사람이 있습니다.");
			return;
		}
		hotelMap.put(roomNum, new hotelVO(roomNum, name));
		
		System.out.println("체크인 되었습니다");
		
	}
		public static void main(String[] args) {
			new homework1().hotelStart();
		}	
}



// 호텔 예약정보를 알수있는 VO클래스
class hotelVO {
	//필드
	private String roomNum;
	private String name;

	//생성자
	public hotelVO(String roomNum, String name) {
		super();
		this.roomNum = roomNum;
		this.name = name;
	}

	//메서드
	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "hotelVO [roomNum=" + roomNum + ", name=" + name + "]";
	}
}
