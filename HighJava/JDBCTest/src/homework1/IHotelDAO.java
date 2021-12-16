package homework1;

import java.util.Map;

public interface IHotelDAO {
	HotelVO vo = new HotelVO();
	/**
	 * BoardVO에 담겨진 자료를 DB에 checkIn(insert)하는 메서드
	 * @param hv DB에 insert할 자료가 저장된 HotelVO 객체
	 * @return DB작업이 성공하면 1이상의 값이 반환 실패하면 0이 반환된다.
	 * 1. 체크인(checkIn)메서드
	 */
	public int checkIn(HotelVO hv);
	
	/**
	 * 키값인 roomNum를 매개변수로 받아서 해당 room을 체크아웃하는 메서드
	 * @param hv
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int checkOut(HotelVO hv);
	
	/**
	 * DB안의 HOTEL_MNG 테이블 전체 레코드를 가져와서 Map에 담아 반환하는 메서드
	 * @return HotelVO객체를 담고있는 Map객체
	 */
	public Map<?, ?> getRoomInfoList();
	
	
}
