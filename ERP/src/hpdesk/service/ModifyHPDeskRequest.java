package hpdesk.service;


import java.util.Map;

import hpdesk.model.HPDeskWriter;


//수정작업에필요한 데이터들
public class ModifyHPDeskRequest {
	private int empno; //사원번호
	private int hdno; //글번호
	private String ename; //작성자
	private String hdtitle;  //글제목
	private String hdcontent; //글내용
	private String hdcomment; //댓글
	private int hdcheck; //글확인상태체크
	
	
	
	public ModifyHPDeskRequest() {}
	public ModifyHPDeskRequest(int empno, int hdno, String ename, String hdtitle, String hdcontent, String hdcomment,
			int hdcheck) {
		super();
		this.empno = empno;
		this.hdno = hdno;
		this.ename = ename;
		this.hdtitle = hdtitle;
		this.hdcontent = hdcontent;
		this.hdcomment = hdcomment;
		this.hdcheck = hdcheck;
	}

	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public int getHdno() {
		return hdno;
	}
	public void setHdno(int hdno) {
		this.hdno = hdno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
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
	public String getHdcomment() {
		return hdcomment;
	}
	public void setHdcomment(String hdcomment) {
		this.hdcomment = hdcomment;
	}
	public int getHdcheck() {
		return hdcheck;
	}
	public void setHdcheck(int hdcheck) {
		this.hdcheck = hdcheck;
	}

	@Override
	public String toString() {
		return "ModifyHPDeskRequest [empno=" + empno + ", hdno=" + hdno + ", ename=" + ename + ", hdtitle=" + hdtitle
				+ ", hdcontent=" + hdcontent + ", hdcomment=" + hdcomment + ", hdcheck=" + hdcheck + "]";
	}
}