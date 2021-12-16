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
					System.out.println(bv.getNo() + "\t" + bv.getTitle() + "\t" + bv.getWriter() + "\t" + bv.getDate() + "\t" + bv.getContent());
				}
			}
			System.out.println("----------------------------------");
			System.out.println("출력 작업 끝...");
		}
	
	// 3. 새 게시글 작성
	public void insertBoard() {
		
			System.out.print("글 제목 >> ");
			String title = Scan.nextLine();
			
			System.out.print("작성자 >> ");
			 String writer = Scan.nextLine();
			
			System.out.print("내용 >> ");
			String content = Scan.nextLine();
			
			BoardVO bv = new BoardVO();
			bv.setTitle(title);
			bv.setWriter(writer);
			bv.setContent(content);
			
			int cnt = bdao.insertBoard(bv); // 회원등록
			
			if(cnt > 0) {
				System.out.println("게시판에 게시글이 등록되었습니다.");
			}else {
				System.out.println("게시판에 게시글이 등록되지 않았습니다.");
			}
	}
	
	// 4.글 수정
	public void updateBoard() {
		
		int no = 0;
		String content = "";
		String title = "";
		do {
			System.out.println();
			System.out.println("수정할 게시글 내용을 입력하세요.");
			System.out.print("글 NO >> ");
			
			no = Integer.parseInt(Scan.nextLine()); 
			
			BoardVO bv = new BoardVO();
			
			if( bv.getNo() == no) {
				System.out.println("글 NO가 " + no + "인 게시글은 존재하지 않습니다.");
				System.out.println("다시 입력해 주세요.");
			}
			
		}while(new BoardVO().getNo() == no);
		
		// Scan.nextLine();  // 입력버퍼 비우기용
		System.out.print("수정할 제목 >> ");
		title = Scan.nextLine();
		
		System.out.println("수정할 게시글 내용 >> ");
		content = Scan.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setTitle(title);
		bv.setContent(content);
		bv.setNo(no);
		
		int cnt = bdao.updateBoard(bv);
		
		if(cnt > 0) {
			System.out.println(no + " 회원 정보를 수정 했습니다.");
		}else {
			System.out.println(no + " 회원 정보 수정을 실패했습니다!!!");
		}
	}

	
	// 5. 글 삭제를 위한 메서드
	public void deleteBoard() {
			System.out.println();
			System.out.println("삭제할 글 정보를 입력하세요.");
			System.out.print("글 NO >> ");
			
			int no = Integer.parseInt(Scan.nextLine());
					
			BoardVO bv = new BoardVO();
			bv.setNo(no);
			
			int cnt = bdao.deleteBoard(no);
			
			if(cnt > 0) {
				System.out.println("글NO : "+no + " 번의 게시글 정보 삭제했습니다.");
			}else {
				System.out.println( no + " 게시글 정보 삭제 실패했습니다!!!");
			}
	}
	
	// 6. 글 검색
	public void searchBoard() {
		System.out.println();
		System.out.println("검색할 글 정보를 입력하세요.");
		System.out.print("검색할 작성자명 >> ");
		
		String writer = Scan.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setWriter(writer);

		boardList = bdao.searchBoard(bv);
		
		if(boardList.isEmpty()) {
			System.out.println("작성된 게시글이 없습니다.");
		} else {
			System.out.println();
			System.out.println("----------------------------------");
			System.out.println(" NO\t제 목\t작성자\t날 짜 \t내 용");
			System.out.println("----------------------------------");
			for (BoardVO bv1 : boardList) {
				System.out.println(bv1.getNo() + "\t" + bv1.getTitle() + "\t" + bv1.getWriter() + "\t" + bv1.getDate() + "\t" + bv1.getContent());
			}
		}
		System.out.println("----------------------------------");
		System.out.println("출력 작업 끝...");
	}
	
	private boolean checkBoard(String writer) {
		
		boolean isExist = bdao.checkBoard(writer);
		
		return isExist;
	}

}
