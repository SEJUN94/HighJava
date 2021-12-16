package homework;

public class BoardVO {
	// 필드
	private String boardNo; // 게시판 번호
	private String boardTitle;  // 게시글 제목
	private String boardWriter; // 작성자
	private String boardDate;   // 작성날짜
	private String boardContent;// 글 내용
	
	// 생성자
	public BoardVO() {
		super();
	}

	// 메서드
	public String getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public String getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	
	
	
	
	
}
