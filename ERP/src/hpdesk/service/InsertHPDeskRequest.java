package hpdesk.service;

import java.util.Map;

import hpdesk.model.HPDeskWriter;

//이 클래스는 문의사항글 쓰기에 필요한 데이터를 제공
public class InsertHPDeskRequest { 

	private HPDeskWriter hpdeskWriter; //작성자(이름,아이디)
	private String hdtitle; //글제목
	private String hdcontent; //글내용
	private String hdcomment;//댓글

	public InsertHPDeskRequest() {}
	public InsertHPDeskRequest(HPDeskWriter hpdeskWriter, String hdtitle, String hdcontent) {
		this.hpdeskWriter = hpdeskWriter;
		this.hdtitle = hdtitle;
		this.hdcontent = hdcontent;
	}
	public InsertHPDeskRequest(HPDeskWriter hpdeskWriter, String hdtitle, String hdcontent, String hdcomment) {
		this.hpdeskWriter = hpdeskWriter;
		this.hdtitle = hdtitle;
		this.hdcontent = hdcontent;
		this.hdcomment = hdcomment;
	}

		public HPDeskWriter getHpdeskWriter() {
		return hpdeskWriter;
	}

	public void setHpdeskWriter(HPDeskWriter hpdeskWriter) {
		this.hpdeskWriter = hpdeskWriter;
	}
	public String getHdtitle() {
		return hdtitle;
	}
	public void setHdtitle(String hdtitle) {
		this.hdtitle = hdtitle;
	}
	public String getHdcontent() {
		return hdcontent;
	}
	public void setHdcontent(String hdcontent) {
		this.hdcontent = hdcontent;
	}

	//유효성검사-필수입력체크
	public void validate(Map<String,Boolean> errors) {
		if( hdtitle==null || hdtitle.isEmpty()){
			errors.put("hdtitle",Boolean.TRUE);
		}
		if( hdcontent==null || hdcontent.isEmpty()){
			errors.put("hdcontent",Boolean.TRUE);
		}
	}

	@Override
	public String toString() {
			return "InsertHPDeskRequest [hpdeskWriter=" + hpdeskWriter + ", hdtitle=" + hdtitle + ", hdcontent="
					+ hdcontent + "]";
		}

		public String getHdcomment() {
			return null;
		}

}	