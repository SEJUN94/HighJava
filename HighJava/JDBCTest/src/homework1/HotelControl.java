package homework1;

import java.util.Map;
import java.util.Set;

public class HotelControl {
	private Map<Integer, String> room;
	private IHotelService hdao = new HotelDAO();

	public void checkIn() {
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.print("방번호 입력>>");
		int roomNum = Scan.nextInt();
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력=>");
		String name = Scan.nextLine();
		
		HotelVO hv = new HotelVO();
		hv.setRoomNum(roomNum);
		hv.setGuestName(name);
		
		int cnt = hdao.checkIn(hv);
		
		if(cnt>0) {
			room.put(roomNum, name);
		System.out.println("체크인이 되었습니다.");
		}else {
			System.out.println(roomNum + "방에는 이미 사람이 있습니다.");
		}
		
	}

	public void checkOut() {
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 =>");
		int roomNum = Scan.nextInt();
		// map클래스의 remove메서드에 키값인 roomNum를 넣어 키값이 있으면 
		// map은 항상 entry(한상)이기때문에 key값이 있으면 value값도 있음을 알수있기에 키값이 있다면 null 빈값이 아닌것이기에 true
		
		HotelVO hv = new HotelVO();
		hv.setRoomNum(roomNum);
		
		int cnt = hdao.checkOut(hv);
		
		if(cnt>0) {
			room.remove(roomNum);
			System.out.println("체크아웃 되었습니다.");
		}else {
			System.out.println(roomNum + "방에는 체크인한 사람이 없습니다.");
		}
		
	}

	public void roomCheck() {
		Set<Integer> keyset = room.keySet();
		
		if(room.size() != 0) {
			for (Integer key : keyset) {
				System.out.println("방번호 : " + key + ", 투숙객 : " + room.get(key) );						
			}
		}else {
			System.out.println("현재 호텔에 숙박중인 투숙객이 없습니다.");
		} 
		
	}

}
