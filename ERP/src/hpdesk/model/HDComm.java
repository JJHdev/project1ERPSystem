package hpdesk.model;

//이 클래스는 hdcomm테이블 관련 데이터 클래스이다
public class HDComm {

	private Integer hdno;//게시글번호
	private String hdcontent;//게시글내용
	private String hdcomment;//댓글
	private String isshow;//노출여부
	private String hdtitle;

	public HDComm() {}
	public HDComm(Integer hdno, String hdcontent, String hdcomment, String isshow) {
		this.hdno = hdno;
		this.hdcontent = hdcontent;
		this.hdcomment = hdcomment;
		this.isshow = isshow;
	}
	public HDComm(String hdtitle, Integer hdno, String hdcontent, String hdcomment) {
		this.hdtitle=hdtitle;
		this.hdno = hdno;
		this.hdcontent = hdcontent;
		this.hdcomment = hdcomment;
	}

	public Integer getHdno() {
		return hdno;
	}
	public String getHdcontent() {
		return hdcontent;
	}
	public String getHdcomment() {
		return hdcomment;
	}
	public String getIsshow() {
		return isshow;
	}
	public void setHdno(Integer hdno) {
		this.hdno = hdno;
	}
	public void setHdcontent(String hdcontent) {
		this.hdcontent = hdcontent;
	}
	public void setHdcomment(String hdcomment) {
		this.hdcomment = hdcomment;
	}
	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}

	@Override
	public String toString() {
		return "HDComm [hdno=" + hdno + ", hdcontent=" + hdcontent + ", hdcomment=" + hdcomment + ", isshow=" + isshow
				+ "]";
	}


}
