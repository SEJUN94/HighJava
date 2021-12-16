package homework;


public class BoardView {
	
	public static void main(String[] args) {
		BoardView view = new BoardView();
		view.View();
	}
	
	public void View() {
	BoardControl con = new BoardControl();
	
	System.out.println("*******************************************");
	System.out.println("대덕인재게시판에 오신것을 환영합니다.");
	System.out.println("어떤 업무를 하시겠습니까?");
	System.out.println("1.전체 목록보기  2.게시글 보기  3.새 글 작성  4.글 수정  5.글 삭제  6.글 검색  0.업무종료");
	System.out.println("*******************************************");
	System.out.print("메뉴 선택=>");
	
		while(true) {
			int sel = Integer.parseInt(Scan.nextLine());		
			switch(sel) {
				case 1: View();  // 전체 목록보기
					break;
				case 2: con.displayBoard();  // 게시글 보기
					View();
					break;
				case 3: 
					con.insertBoard();  // 새 글 작성
					View();
					break;
				case 4: con.updateBoard(); // 글 수정
					View();
					break;
				case 5: con.deleteBoard(); // 글 삭제
					View();
					break;
				case 6: con.searchBoard(); // 글 검색
					View();
					break;
				case 0:	
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);; // 프로그램 종료
			}
		}
	}
}
