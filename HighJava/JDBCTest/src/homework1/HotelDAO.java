package homework1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import kr.or.ddit.util.JDBCUtil2;

public class HotelDAO implements IHotelService {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Override
	public int checkIn(HotelVO hv) {
		int cnt = 0;
		try {
			
			conn =  JDBCUtil2.getConnection();
			
			String sql = "INSERT INTO HOTEL_MNG "
					+" VALUES (? , ?) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, hv.getRoomNum());
			pstmt.setString(2, hv.getGuestName());
			
			cnt = pstmt.executeUpdate();   // select가 아니면 업데이트
				
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil2.disConnect(conn, stmt, pstmt, rs);
		}
	
		return cnt;
	}

	@Override
	public int checkOut(HotelVO hv) {
		int cnt = 0;
		
		try {
			conn = JDBCUtil2.getConnection();
			
			String sql = " DELETE FROM HOTEL_MNG " +  
					"WHERE ROOM_NUM = ? "; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, hv.getRoomNum());
			
			cnt = pstmt.executeUpdate();
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil2.disConnect(conn, stmt, pstmt, rs);
		}
		
		return cnt;
	}

	@Override
	public Map<Integer, String> getRoomInfoList() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
