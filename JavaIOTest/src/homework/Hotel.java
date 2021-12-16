package homework;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class Hotel {
	private Scanner scan;
	private Map<Integer, String> room;
	
	// 매개변수값을 초기화해주는 메서드
	public Hotel() {
		super();
		room = new HashMap<Integer, String>();
	}

	public static void main(String[] args) {
		new Hotel().hotelStart();
	}	
	
	// 메뉴를 출력하는 메서드
	public void displayMenu() {
		System.out.println("*******************************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
		System.out.println("*******************************************");
		System.out.print("메뉴 선택=>");
	}
	
	//프로그램을  시작하는 메서드
	public void hotelStart() {
		scan = new Scanner(System.in);
		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");
		
		// 저장된 파일을 불러온 객체 선언
		ObjectInputStream ois = null;
		try {
			// 저장된 객체를 읽어와 출력하기
			// 입력용 스트림 객체 생성
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("d:/D_Other/Hotel.bin")));
			
			// 불러온 파일을 저장할 객체
			Object obj = null;
			
			// ObjectInputStream의 readObject메서드를 사용하여 역직렬화하여 읽어들임 ois에 읽어들이고 obj에 담아줌
			// 복사가 다되면 더 이상 읽을 파일이 없기때문에 null값을 읽게됨 null이되면 다읽었다는 것으로 간주하고 while문을 벗어나게됨
			while((obj = ois.readObject()) != null) {
				
				// 읽어온 데이터를 원래의 객체의 타입으로 변환 후 담아준다.
				room = (HashMap<Integer, String>) obj;
				System.out.println("------------------------");
			}
			// 아무이상이 없다면 catch문을 건너뛰고 실행이됨
		}catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}catch(IOException ex) {
			//ex.printStackTrace();   // 에러가 뜨지 않게 하려면
			System.out.println();
			System.out.println();
			for (int i = 0; i < 10; i++) {
				System.out.println("호텔의 예약리스트를 읽고있습니다...");
				System.out.println(".\n.\n.\n.\n.");
			}
			System.out.println();
		}finally {
			try {
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		while(true) {
			displayMenu(); // 메뉴 출력
			
			int menuNum = scan.nextInt();
			
			switch(menuNum) {
			case 1: checkin();  // 체크인
				break;
			case 2: checkout(); // 체크아웃
				break;
			case 3: roomCkeck(); // 객실상태
				break;
			case 4: save_exit(); // 객실상태 저장후 프로그램 종료
			}
		}
	}

	// 4. 시스템 종료 및 파일 저장
	private void save_exit() {
		ObjectOutputStream oos = null;
		String end = "\r\n" + 
				"**************************\r\n" + 
				"호텔 문을 닫았습니다.\r\n" + 
				"**************************";
		System.out.println(end);
		try {
			// 출력용 스트림 객체 생성 객체에 파일에 저장하기 
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("d:/D_Other/Hotel.bin")));
			
			// 쓰기 작업
			oos.writeObject(room); //직렬화
			
			System.out.println();
			System.out.println("현재까지 호텔의 고객 예약을 데이터에 보관하였습니다.");
		}catch(IOException ex) {
			ex.printStackTrace();
		}finally {
			try {
				oos .close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		System.exit(0);
		
	}

	// 3. 객실상태 체크
	private void roomCkeck() {
		Set<Integer> keyset = room.keySet();
		
		if(room.size() != 0) {
			for (Integer key : keyset) {
				System.out.println("방번호 : " + key + ", 투숙객 : " + room.get(key) );						
			}
		}else {
			System.out.println("현재 호텔에 숙박중인 투숙객이 없습니다.");
		} 
	}

	// 2. 체크아웃 메서드
	private void checkout() {
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 =>");
		int roomNum = scan.nextInt();
		// map클래스의 remove메서드에 키값인 roomNum를 넣어 키값이 있으면 
		// map은 항상 entry(한상)이기때문에 key값이 있으면 value값도 있음을 알수있기에 키값이 있다면 null 빈값이 아닌것이기에 true
		if(room.remove(roomNum) != null) {
			//room.remove(roomNum);
			System.out.println("체크아웃 되었습니다.");
		}else {
			System.out.println(roomNum + "방에는 체크인한 사람이 없습니다.");
		}
		
	}

	// 1. 체크인 메서드
	private void checkin() {
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.print("방번호 입력>>");
		int roomNum = scan.nextInt();
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력=>");
		String name = scan.next();
		if(room.get(roomNum) == null) {
			room.put(roomNum, name);
		System.out.println("체크인이 되었습니다.");
		}else {
			System.out.println(roomNum + "방에는 이미 사람이 있습니다.");
		}
	}
}