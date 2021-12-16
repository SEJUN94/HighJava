package homework;

public class BoardVO {
	// 필드
	private int no; // 게시판 번호
	private String title;  // 게시글 제목
	private String writer; // 작성자
	private String date;   // 작성날짜
	private String content;// 글 내용
	
	// 생성자
	public BoardVO() {
		super();
	}

	// 메서드
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
