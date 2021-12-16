package homework;

import java.util.List;


public interface IBoardService {
		
		/**
		 * 2. 게시판 보기
		 * DB안의 JDBC_BOARD 테이블 전체 레코드를 가져와서 List에 담아 반환하는 메서드
		 * @return BOARDVO객체를 담고 있는 List객체
		 */
		public List<BoardVO> getDisplayBoardList();
		
		/**
		 *	3. 게시글 작성
		 *	BoardVO에 담겨진 자료를 DB에 insert하는 메서드
		 * @param bv DB에 insert할 자료가 저장된 BoardVO 객체
		 * @return DB작업이 성공하면 1이상의 값이 반환 실패하면 0이 반환된다.
		 */
		public int insertBoard(BoardVO bv);
		
		/**
		 * 4. 글 수정
		 * 하나의 BoardVO자료를 이용하여 DB를 update하는 메서드
		 * @param bv update할 회원 정보가 담긴 BoardVO객체
		 * @return 작업성공 : 1, 작업실패 : 0
		 */
		public int updateBoard(BoardVO bv);
		
		/**
		 * 5. 글 삭제
		 * 글번호(no)를 매개변수로 받아서 해당 게시글을 삭제하는 메서드
		 * @param no 삭제할 글no
		 * @return 작업성공 : 1, 작업실패 : 0
		 */
		public int deleteBoard(BoardVO bv);
		
		/**
		 * 6. 번호를 이용한 글 검색
		 * BoardVO에 담긴 자료를 이용하여 회원을 검색하는 메서드
		 * @param bv 검색할 자료가 들어있는 BoardVO객체
		 * @return 검색된 결과를 담은 List객체
		 */
		public List<BoardVO> searchBoard(BoardVO bv);
}
