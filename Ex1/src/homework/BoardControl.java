package homework;

import java.util.List;



public class BoardControl {
	// 사용할 객체를 담기위한 변수를 선언
	private IBoardService bdao;
	private List<BoardVO> boardList;
	
	public BoardControl() {
		bdao = BoardService.getInstance();
	}
	
	// 2. 전체 게시글 보기
	public void displayBoard() {
			
			System.out.println();
			System.out.println("----------------------------------");
			System.out.println(" NO\t제 목\t작성자\t날 짜 \t내 용");
			System.out.println("----------------------------------");

			boardList = bdao.getDisplayBoardList();
			
			if(boardList.size() == 0 ) {
				System.out.println("작성된 게시글이 없습니다.");
			} else {
				for (BoardVO bv : boardList) {
					System.out.println(bv.getBoardNo() + "\t" + bv.getBoardTitle() + "\t" + bv.getBoardWriter() + "\t" + bv.getBoardDate() + "\t" + bv.getBoardContent());
				}
			}
			System.out.println("----------------------------------");
			System.out.println("출력 작업 끝...");
		}
	
	// 3. 새 게시글 작성
	public void insertBoard() {
		
			System.out.print("글 제목 >> ");
			String boardTitle = Scan.nextLine().trim();
			
			System.out.print("작성자 >> ");
			String boardWriter = Scan.nextLine().trim();
			
			System.out.print("내용 >> ");
			String boardContent = Scan.nextLine().trim();
			
			BoardVO bv = new BoardVO();
			bv.setBoardTitle(boardTitle);
			bv.setBoardWriter(boardWriter);
			bv.setBoardContent(boardContent);
			
			int cnt = bdao.insertBoard(bv); // 회원등록
			
			if(cnt > 0) {
				System.out.println("게시판에 게시글이 등록되었습니다.");
			}else {
				System.out.println("게시판에 게시글이 등록되지 않았습니다.");
			}
	}
	
	// 4.글 수정
	public void updateBoard() {
		boolean chk = false;
		String boardNo = "";
		String boardContent = "";
		
		// 값을 주고 받기전 selet(읽기)문으로 확인하는 방법
		do {
			System.out.println();
			System.out.println("수정할 게시글 내용을 입력하세요.");
			System.out.print("글 NO >> ");
			
			boardNo = Scan.nextLine(); 
			
			chk = checkBoard(boardNo);
				
			if(chk == false) {
				System.out.println("글 NO가 " + boardNo + "인 게시글은 존재하지 않습니다.");
				System.out.println("다시 입력해 주세요.");
			}
			
		}while(chk = false);
		
		/*
		// 값을 주고받은 뒤 확인하는 방법
		do {
			System.out.println();
			System.out.println("수정할 게시글 내용을 입력하세요.");
			System.out.print("글 NO >> ");
			
			boardNo = Scan.nextLine(); 
			
			BoardVO bv = new BoardVO();
			
			if( bv.getBoardNo() == boardNo) {
				System.out.println("글 NO가 " + boardNo + "인 게시글은 존재하지 않습니다.");
				System.out.println("다시 입력해 주세요.");
			}
		}while(new BoardVO().getBoardNo() == boardNo);
		 */
		
		// Scan.nextLine();  // 입력버퍼 비우기용
		System.out.print("수정할 게시글 내용 >> ");
		boardContent = Scan.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setBoardContent(boardContent);
		bv.setBoardNo(boardNo);
		
		int cnt = bdao.updateBoard(bv);
		
		if(cnt > 0) {
			System.out.println(boardNo + " 회원 정보를 수정 했습니다.");
		}else {
			System.out.println(boardNo + " 회원 정보 수정을 실패했습니다!!!");
		}
	}

	
	// 5. 글 삭제를 위한 메서드
	public void deleteBoard() {
			boolean chk = false;
			String boardNo = "";
			
			do {
				System.out.println();
				System.out.println("삭제할 글 정보를 입력하세요.");
				System.out.print("글 NO >> ");
				
				boardNo = Scan.nextLine();
				
				chk = checkBoard(boardNo);
				
				if(chk == false) {
					System.out.println("글 NO가 " + boardNo + "로 작성된 게시글이 없습니다.");
					System.out.println("다시 입력해 주세요.");
				}
				
			}while(chk == false);
			
					
			BoardVO bv = new BoardVO();
			bv.setBoardNo(boardNo);
			
			int cnt = bdao.deleteBoard(boardNo);
			
			if(cnt > 0) {
				System.out.println("글NO가 : "+boardNo + " 인 게시글 정보를 삭제했습니다.");
			}else {
				System.out.println( boardNo + " 게시글 정보 삭제 실패했습니다!!!");
			}
	}
	
	/*
	// 6. 글 검색(글NO로 검색)
	public void searchBoard() {
		
		boolean chk = false;
		String boardNo = "";
		
		do {
			System.out.println();
			System.out.println("검색할 글 정보를 입력하세요.");
			System.out.print("검색할 글NO >> ");
			
			boardNo = Scan.nextLine();
			
			chk = checkBoard(boardNo);
			
			if(chk == false) {
				System.out.println("글 NO가 " + boardNo + "로 작성된 게시글이 없습니다.");
				System.out.println("다시 입력해 주세요.");
			}
			
		}while(chk == false);
		
		
		
		BoardVO bv = new BoardVO();
		bv.setBoardNo(boardNo);

		boardList = bdao.searchBoard(bv);
		
		if(boardList.isEmpty()) {
			System.out.println("작성된 게시글이 없습니다.");
		} else {
			System.out.println();
			System.out.println("----------------------------------");
			System.out.println(" NO\t제 목\t작성자\t날 짜 \t내 용");
			System.out.println("----------------------------------");
			for (BoardVO bv1 : boardList) {
				System.out.println(bv1.getBoardNo() + "\t" + bv1.getBoardTitle() + "\t" + bv1.getBoardWriter() + "\t" + bv1.getBoardDate() + "\t" + bv1.getBoardContent());
			}
		}
		System.out.println("----------------------------------");
		System.out.println("출력 작업 끝...");
	}
	*/
	
		// 6. 글 검색(다이나믹쿼리로 검색)
		public void searchBoard() {
	
				System.out.println("검색할 글 정보를 입력하세요.");
				System.out.print("검색할 글NO >> ");
				String boardNo = Scan.nextLine();
				System.out.println();
				
				System.out.print("검색할 글 제목 >> ");
				String boardTitle = Scan.nextLine();
				System.out.println();
				
				System.out.print("검색할 글 작성자 >> ");
				String boardWriter = Scan.nextLine();
				System.out.println();
				
				System.out.print("검색할 글 내용 >> ");
				String boardContent = Scan.nextLine();
				System.out.println();
			
			
			BoardVO bv = new BoardVO();
			bv.setBoardNo(boardNo);
			bv.setBoardTitle(boardTitle);
			bv.setBoardWriter(boardWriter);
			bv.setBoardContent(boardContent);

			boardList = bdao.searchBoard(bv);
			
			if(boardList.isEmpty()) {
				System.out.println("작성된 게시글이 없습니다.");
			} else {
				System.out.println();
				System.out.println("----------------------------------");
				System.out.println(" NO\t제 목\t작성자\t날 짜 \t내 용");
				System.out.println("----------------------------------");
				for (BoardVO bv1 : boardList) {
					System.out.println(bv1.getBoardNo() + "\t" + bv1.getBoardTitle() + "\t" + bv1.getBoardWriter() + "\t" + bv1.getBoardDate() + "\t" + bv1.getBoardContent());
				}
			}
			System.out.println("----------------------------------");
			System.out.println("출력 작업 끝...");
		}
	
	private boolean checkBoard(String boardNo) {
		
		boolean isExist = bdao.checkBoard(boardNo);
		
		return isExist;
	}

}
