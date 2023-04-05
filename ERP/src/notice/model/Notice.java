package notice.model;

import java.util.Date;
import java.util.List;
import java.util.Map;



public class Notice {
	
	//필드
	private int notice_no;
	private int empno;
	private String title;
	private String content;
	private Date regdate;
	private Date moddate;
	private int readcnt;
	private String isshow;
	
	//페이징필드
	//필드
	private int total;			 //전체게시물수
	private int currentPage;	 //현재 페이지
	private List<Notice> contents;//게시글목록정보(글번호,제목,작성자,조회수,최초작성일,마지막수정일)
	private int totalPages;		//총페이지수
	private int startPage;		//시작페이지번호
	private int endPage;		//끝페이지번호
	
	
	
	
	//기본생성자 
	public Notice() {

	}
	
	//사원정보 생성자
	public Notice(int notice_no, int empno,String title, String content, Date regdate,
			Date moddate, int readcnt, String isshow) {
		
		this.notice_no = notice_no;
		this.empno = empno;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.moddate = moddate;
		this.readcnt = readcnt;
		this.isshow = isshow;
	}
	
	//페이징 생성자
	public Notice(int total, int currentPage, 
			   int size, List<Notice> contents) {
		this.total = total;
		this.currentPage = currentPage;
		this.contents = contents;
		if (total == 0) { //게시물이 존재하지 않는 경우
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		}else{ //게시물이 존재하는 경우
			totalPages = total/size; //총페이지수=전체게시물수/1page당 보여줄 게시물수
			if (total%size>0) {      //나머지가 0보다 크면
				totalPages++;        //전체페이수를 1씩증가
			}
			int modVal=currentPage%5;  //user가보고싶은 요청페이지를 5로 나눈 나머지를 저장
			startPage =currentPage/5*5 + 1;
			//modVal==0은 요청페이지가 5의배수인 5 10 15....
			if (modVal==0) 
			startPage-=5;//startPage=startPage-5;
			endPage = startPage + 4;
			//endPage가 전체페이수보다크면  endPage값을 전체페이수로 적용해라
			if(endPage>totalPages)endPage=totalPages;
		}
	}
	
	//수정요청을 위한 생성자
	public Notice(int empno,int notice_no,String title,String content) {
		this.empno=empno;
		this.notice_no=notice_no;
		this.title=title;
		this.content=content;
	}
	
	//글쓴이를 위한 생성자
	public Notice(int empno,String title, String content) {
		this.empno=empno;
		this.title=title;
		this.content=content;
		
	}
	//공지사항 글쓰기 insert를 위한 생성자
	public Notice(int empno,String title, String content, Date regdate,
			Date moddate, int readcnt, String isshow) {
		
		this.empno = empno;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.moddate = moddate;
		this.readcnt = readcnt;
		this.isshow = isshow;
	}
		
	
	//회원정보 메서드
	public int getNotice_no() {
		return notice_no;
	}
	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getModdate() {
		return moddate;
	}
	public void setModdate(Date moddate) {
		this.moddate = moddate;
	}
	public int getReadcnt() {
		return readcnt;
	}
	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}
	public String getIsshow() {
		return isshow;
	}
	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}
	
	
	//페이징 메서드
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	public List<Notice> getContents() {
		return contents;
	}
	public void setContent(List<Notice> contents) {
		this.contents = contents;
	}
	
	//게시글 존재여부 확인하는 메서드 2가지
	//게시글없니? 게시글없으면 true리턴
	public boolean hasNoNotices() {
		return total == 0;
	}
	//게시글이 존재하니? 게시글 있으면 true
	public boolean hasNotices() {
		return total > 0;
	}
	
	public void validate(Map<String ,Boolean> errors) {
		if(title==null || title.trim().isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
		
	}//validate() 끝
	
	public void validate2(Map<String,Boolean> errors) {
		if(title == null || title.isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
		if(content == null || content.isEmpty()) {
			errors.put("content", Boolean.TRUE);
		}
		
	}//validate2() 끝
	

	@Override
	public String toString() {
		return "Notice [notice_no=" + notice_no + ", empno=" + empno +", title=" + title
				+ ", content=" + content + ", regdate=" + regdate + ", moddate=" + moddate + ", readcnt=" + readcnt
				+ ", isshow=" + isshow + ", total=" + total + ", currentPage=" + currentPage + ", contents=" + contents
				+ ", totalPages=" + totalPages + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
	
	//정보전달 확인을 위한 toString()
	
	
	
	
}//Notice Class 끝
