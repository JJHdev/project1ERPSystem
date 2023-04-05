package hpdesk.model;

import java.util.Date;

import emp.model.Emp;

//이 클래스는 hpdesk테이블에 해당하는 데이터를 관리하는 클래스
public class HPDesk {

	private Integer hdno; //게시글번호
	private String hdtitle; //게시글제목
	private HPDeskWriter hpdeskWriter; //작성자,사원번호
	private Date hddate; //작성일
	private String isshow;//노출여부
	private String hdcontent;//게시글내용
	private int hdcheck;//글확인체크 1미답변 2처리중 3 답변완료  

	public HPDesk() {	}
	public HPDesk(Integer hdno, String hdtitle, HPDeskWriter hpdeskWriter, Date hddate, String isshow, String hdcontent, int hdcheck) {
		this.hdno = hdno;
		this.hdtitle = hdtitle;
		this.hpdeskWriter = hpdeskWriter; //작성자ename, 사원번호empno
		this.hddate = hddate;
		this.isshow = isshow;
		this.hdcontent = hdcontent;
		this.hdcheck = hdcheck;
	}
	
	public Integer getHdno() {
		return hdno;
	}
	public String getHdtitle() {
		return hdtitle;
	}
	public HPDeskWriter getHpdeskWriter() {
		return hpdeskWriter;
	}
	public Date getHddate() {
		return hddate;
	}
	public String getIsshow() {
		return isshow;
	}
	public String getHdcontent() {
		return hdcontent;
	}
	public void setHdno(Integer hdno) {
		this.hdno = hdno;
	}
	public void setHdtitle(String hdtitle) {
		this.hdtitle = hdtitle;
	}
	public void setHpdeskWriter(HPDeskWriter hpdeskWriter) {
		this.hpdeskWriter = hpdeskWriter;
	}
	public void setHddate(Date hddate) {
		this.hddate = hddate;
	}
	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}
	public void setHdcontent(String hdcontent) {
		this.hdcontent = hdcontent;
	}
	public void setHdcheck(int hdcheck) {
		this.hdcheck = hdcheck;
	}
	public int getHdcheck() {
		return hdcheck;
	}

	@Override
	public String toString() {
		return "HPDesk [hdno=" + hdno + ", hdtitle=" + hdtitle + ", hpdeskWriter=" + hpdeskWriter + ", hddate=" + hddate
				+ ", isshow=" + isshow + ", hdcontent=" + hdcontent + ", hdcheck=" + hdcheck + "]";
	}

}
