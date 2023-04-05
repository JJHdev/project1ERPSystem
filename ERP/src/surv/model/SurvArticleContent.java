package surv.model;

import java.util.Date;

import emp.model.Emp;

public class SurvArticleContent {
	//필드
	private SurvArticle surv_no;
	private Emp empno;
	private SurvArticle surv_title;
	private String surv_desc;
	private Date surv_resdate;
	private String surv_isshow;
	
	private int num;
	private String surv_tit;
	private String surv_content;
	private int survWriter_no;
	
	


	public int getSurvWriter_no() {
		return survWriter_no;
	}


	public void setSurvWriter_no(int survWriter_no) {
		this.survWriter_no = survWriter_no;
	}


	//기본 생성자//세팅해서 사용
	public SurvArticleContent() {
		
	}
	
	
	//전체 생성자
	public SurvArticleContent(SurvArticle surv_no, Emp empno, SurvArticle surv_title, String surv_desc,
			Date surv_resdate, String surv_isshow, int num, String surv_tit,
			String surv_content) {
		
		this.surv_no = surv_no;
		this.empno = empno;
		this.surv_title = surv_title;
		this.surv_desc = surv_desc;
		this.surv_resdate = surv_resdate;
		this.surv_isshow = surv_isshow;
		this.num = num;
		this.surv_tit = surv_tit;
		this.surv_content = surv_content;
	}

	


	


	public SurvArticleContent(SurvArticle surv_no, Emp empno, SurvArticle surv_title, String surv_content,
			Date surv_resdate, String surv_isshow) {
		this.surv_no = surv_no;
		this.empno = empno;
		this.surv_title = surv_title;
		this.surv_content = surv_content;
		this.surv_resdate = surv_resdate;
		this.surv_isshow = surv_isshow;
	}


	public SurvArticleContent(int num, String surv_tit, String surv_content, int survWriter_no,Date surv_resdate) {
		this.num = num;
		this.surv_tit = surv_tit;
		this.surv_content = surv_content;
		this.survWriter_no = survWriter_no;
		this.surv_resdate= surv_resdate;
	}


	

	public SurvArticle getSurv_no() {
		return surv_no;
	}

	public void setSurv_no(SurvArticle surv_no) {
		this.surv_no = surv_no;
	}

	public Emp getEmpno() {
		return empno;
	}

	public void setEmpno(Emp empno) {
		this.empno = empno;
	}

	public SurvArticle getSurv_title() {
		return surv_title;
	}

	public void setSurv_title(SurvArticle surv_title) {
		this.surv_title = surv_title;
	}

	public String getSurv_desc() {
		return surv_desc;
	}

	public void setSurv_desc(String surv_desc) {
		this.surv_desc = surv_desc;
	}


	public Date getSurv_resdate() {
		return surv_resdate;
	}

	public void setSurv_resdate(Date surv_resdate) {
		this.surv_resdate = surv_resdate;
	}

	public String getSurv_isshow() {
		return surv_isshow;
	}

	public void setSurv_isshow(String surv_isshow) {
		this.surv_isshow = surv_isshow;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getSurv_tit() {
		return surv_tit;
	}

	public void setSurv_tit(String surv_tit) {
		this.surv_tit = surv_tit;
	}

	public String getSurv_content() {
		return surv_content;
	}

	public void setSurv_content(String surv_content) {
		this.surv_content = surv_content;
	}
	
	
	@Override
	public String toString() {
		return "SurvArticleContent [surv_no=" + surv_no + ", empno=" + empno + ", surv_title=" + surv_title
				+ ", surv_desc=" + surv_desc + ", surv_resdate=" + surv_resdate + ", surv_isshow=" + surv_isshow
				+ ", num=" + num + ", surv_tit=" + surv_tit + ", surv_content=" + surv_content + ", survWriter_no="
				+ survWriter_no + "]";
	}
	
	
}
