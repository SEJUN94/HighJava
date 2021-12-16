package homework;

import java.util.List;


public class BoardService implements IBoardService{
	
		private static IBoardService boardService;
		
		private IBoardDAO boardDao;
		
		private BoardService() {
			boardDao = BoardDAO.getInstance();
		}
		
		public static IBoardService getInstance() {
			if(boardService == null) {
				boardService = new BoardService();
			}
			return boardService;
		}
	
		public List<BoardVO> getDisplayBoardList() {
			return boardDao.getDisplayBoardList();
		}
		
		public int insertBoard(BoardVO bv) {
			return boardDao.insertBoard(bv);
		}
		
		public int updateBoard(BoardVO bv) {
			return boardDao.updateBoard(bv);
		}
		
		public int deleteBoard(BoardVO bv) {
			return boardDao.deleteBoard(bv);
		}
		
		public List<BoardVO> searchBoard(BoardVO bv) {
			return boardDao.searchBoard(bv);
		}
}
