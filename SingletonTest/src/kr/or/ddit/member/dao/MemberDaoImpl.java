package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil3;

public class MemberDaoImpl implements IMemberDao {
	
	// 싱글턴 패턴 1번 자기 자신 class의 참조변수를 멤버변수로 선언한다.
	private static IMemberDao memDao;
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private MemberDaoImpl() {
		
	}
	
	public static IMemberDao getInstance() {
		if(memDao == null) {
			memDao = new MemberDaoImpl();
		}
		return memDao;
	}
	
	
	@Override
	public int InsertMember(MemberVO mv) {

		
		int cnt = 0;
		
		try {
			
			conn =  JDBCUtil3.getConnection();
			
			String sql = "INSERT INTO mymember "
					+" (mem_id,mem_name,mem_tel,mem_addr) "
					+" VALUES (?, ?, ?, ?) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMemId());
			pstmt.setString(2, mv.getMemName());
			pstmt.setString(3, mv.getMemTel());
			pstmt.setString(4, mv.getMemAddr());
			
			cnt = pstmt.executeUpdate();   // select가 아니면 업데이트
				
			
		}catch(SQLException ex) {
			// System.out.println(memId + "회원 추가 작업 실패!!!");
			ex.printStackTrace();
		}finally {
			JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
		}
	
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {

		boolean isExist = false;
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = " select count(*) as cnt from mymember " 
						+" where mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
			if(cnt > 0) {
				isExist = true;
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
		}
		
		return isExist;
	}

	@Override
	public int updateMember(MemberVO mv) {
		int cnt = 0;
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = " UPDATE mymember "  
					    +" SET mem_name = ? "  
					    +" , mem_tel = ? "  
					    +" , mem_addr= ? "  
					    +" WHERE mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMemName());
			pstmt.setString(2, mv.getMemTel());
			pstmt.setString(3, mv.getMemAddr());
			pstmt.setString(4, mv.getMemId());
			
			cnt = pstmt.executeUpdate();
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		
		int cnt = 0;
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "delete from mymember where mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			cnt = pstmt.executeUpdate();
			
		}catch(SQLException ex) {
			System.out.println(memId + " 회원 정보 삭제 실패했습니다!!!");
			ex.printStackTrace();
		}finally {
			JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "select * from mymember ";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				
				MemberVO mv = new MemberVO();
				mv.setMemId(memId);
				mv.setMemName(memName);
				mv.setMemTel(memTel);
				mv.setMemAddr(memAddr);
				
				memList.add(mv);
				
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
		}
		return memList;
	}

	@Override
	public List<MemberVO> searchMember(MemberVO mv) {
		
		List<MemberVO> memList = new ArrayList<>();

		// 다이나믹(Dynamic) 쿼리
		  try {
		         
		         conn=JDBCUtil3.getConnection();
		         
		         String sql= "select * from mymember where 1=1 ";
		         if(mv.getMemId() != null&& !mv.getMemId().equals("")) {  //무조건 널값부터 체크 안하면 널포인트이셉션 발생
		            sql += " and mem_id = ? ";
		         }
		         if(mv.getMemName() != null&& !mv.getMemName().equals("")) {
		            sql += " and mem_name = ? ";
		         }
		         if(mv.getMemTel() != null&& !mv.getMemTel().equals("")) {
		            sql += " and mem_tel = ? ";
		         }
		         if(mv.getMemAddr() != null&& !mv.getMemAddr().equals("")) {
		            sql += " and mem_addr like '%' || ? || '%' ";
		         }
		         
		         pstmt = conn.prepareStatement(sql);
		         
		         int index = 1;
		         
		         if(mv.getMemId() != null&& !mv.getMemId().equals("")) {  //무조건 널값부터 체크 안하면 널포인트이셉션 발생
		            pstmt.setString(index++, mv.getMemId());
		         }
		         if(mv.getMemName() != null&& !mv.getMemName().equals("")) {
		            pstmt.setString(index++, mv.getMemName());
		         }
		         if(mv.getMemTel() != null&& !mv.getMemTel().equals("")) {
		            pstmt.setString(index++, mv.getMemTel());
		         }
		         if(mv.getMemAddr() != null&& !mv.getMemAddr().equals("")) {
		            pstmt.setString(index++, mv.getMemAddr());
		         }
		         
		         rs = pstmt.executeQuery();
		         
		         while(rs.next()) {
		            String memId = rs.getString("mem_id");
		            String memName = rs.getString("mem_name");
		            String memTel = rs.getString("mem_tel");
		            String memAddr = rs.getString("mem_addr");
		            
		            MemberVO mv2 = new MemberVO();
		            mv2.setMemId(memId);
		            mv2.setMemName(memName);
		            mv2.setMemTel(memTel);
		            mv2.setMemAddr(memAddr);
		            
		            memList.add(mv2);
		         }

			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
		}
		
		return memList;
	}
	
}
