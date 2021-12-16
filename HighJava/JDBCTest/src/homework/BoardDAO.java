package homework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.util.JDBCUtil2;

public class BoardDAO implements IBoardDAO{
	private static IBoardDAO boardDao;
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private BoardDAO() {
		
	}
	
	public static IBoardDAO getInstance() {
		if(boardDao == null) {
			boardDao = new BoardDAO();
		}
		return boardDao;
		
	}
	
	// 2. 게시판 보기
	@Override
	public List<BoardVO> getDisplayBoardList() {
		
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			conn = JDBCUtil2.getConnection();
			
			String sql = "select * from jdbc_board ";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				int no = rs.getInt("board_no");
				String title = rs.getString("board_title");
				String writer = rs.getString("board_writer");
				String date = rs.getString("board_date");
				String content = rs.getString("board_content");
				
				BoardVO bv = new BoardVO();
				bv.setNo(no);
				bv.setTitle(title);
				bv.setWriter(writer);
				bv.setDate(date);
				bv.setContent(content);
				
				boardList.add(bv);
			}
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil2.disConnect(conn, stmt, pstmt, rs);
		}
		return boardList;
		
	}
	
	// 3. 새 글 작성
	@Override
	public int insertBoard(BoardVO bv) {
		int cnt = 0;
		try {
			
			conn =  JDBCUtil2.getConnection();
			
			String sql = "INSERT INTO JDBC_BOARD "
					+" VALUES (BOARD_SEQ.NEXTVAL, ?, ?, SYSDATE, ?) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getTitle());
			pstmt.setString(2, bv.getWriter());
			pstmt.setString(3, bv.getContent());
			
			cnt = pstmt.executeUpdate();   // select가 아니면 업데이트
				
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil2.disConnect(conn, stmt, pstmt, rs);
		}
	
		return cnt;
		
		
	}
	
	// 4. 글 수정
	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = 0;
		
		try {
			conn = JDBCUtil2.getConnection();
			
			String sql = " UPDATE JDBC_BOARD " + 
					"SET BOARD_CONTENT = ? " + 
					"WHERE BOARD_NO = ? "; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getContent());
			pstmt.setInt(2, bv.getNo());
			
			cnt = pstmt.executeUpdate();
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil2.disConnect(conn, stmt, pstmt, rs);
		}
		return cnt;
		
	}
	
	// 5. 글 삭제
	@Override
	public int deleteBoard(BoardVO bv) {
		int cnt = 0;
		
		try {
			conn = JDBCUtil2.getConnection();
			
			String sql = " DELETE FROM JDBC_BOARD " +  
					"WHERE BOARD_NO = ? "; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bv.getNo());
			
			cnt = pstmt.executeUpdate();
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil2.disConnect(conn, stmt, pstmt, rs);
		}
		
		return cnt;
		
	}

	// 6. 글 검색
	@Override
	public List<BoardVO> searchBoard(BoardVO bv) {
		
		List<BoardVO> boardList1 = new ArrayList<>();
		
		try {
			conn = JDBCUtil2.getConnection();
			
			
			String sql = " SELECT * FROM JDBC_BOARD WHERE BOARD_WRITER = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getWriter());
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				int no = rs.getInt("board_no");
				String title = rs.getString("board_title");
				String writer = rs.getString("board_writer");
				String date = rs.getString("board_date");
				String content = rs.getString("board_content");
				
				BoardVO bv1 = new BoardVO();
				bv1.setNo(no);
				bv1.setTitle(title);
				bv1.setWriter(writer);
				bv1.setDate(date);
				bv1.setContent(content);
				
				boardList1.add(bv1);
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil2.disConnect(conn, stmt, pstmt, rs);
		}
		return boardList1;
	}
}
