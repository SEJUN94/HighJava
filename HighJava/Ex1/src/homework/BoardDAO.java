package homework;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.util.SqlMapClientFactory;

public class BoardDAO implements IBoardDAO{
	private static IBoardDAO boardDao;
	
	private SqlMapClient smc;
	
	private BoardDAO() {
		smc = SqlMapClientFactory.getInstance();
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
			boardList = smc.queryForList("board.getBoardAll");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return boardList;
		
	}
	
	// 3. 새 글 작성
	@Override
	public int insertBoard(BoardVO bv) {
		int cnt = 0;
		
		try {
			cnt = smc.update("board.insertBoard", bv);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return cnt;
		
		
	}
	
	// 4. 글 수정
	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = 0;
		
		try {
			cnt = smc.update("board.updateBoard", bv);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
		
	}
	
	// 5. 글 삭제
	@Override
	public int deleteBoard(String boardNo) {
		int cnt = 0;
		
		try {
			cnt = smc.delete("board.deleteBoard", boardNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
		
	}

	// 6. 글 검색
	@Override
	public List<BoardVO> searchBoard(BoardVO bv) {
		
		List<BoardVO> boardList1 = new ArrayList<>();
		
		try {
			boardList1 = smc.queryForList("board.searchBoard", bv);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return boardList1;
	}

	@Override
	public boolean checkBoard(String boardNo) {

		boolean isExist = false;
		
		try {
			int cnt = (int) smc.queryForObject("board.checkBoard", boardNo);
			
			if(cnt > 0) {
				isExist = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isExist;
	}
}
