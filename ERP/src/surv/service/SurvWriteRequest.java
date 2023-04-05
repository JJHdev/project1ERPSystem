package surv.service;

import java.util.Date;
import java.util.Map;

import emp.model.Emp;
import surv.model.SurvArticle;
import surv.model.SurvArticleContent;

public class SurvWriteRequest {

	private Emp emp;//작성자정보
	private String surv_tit;//작성자정보
	private String surv_content;
	/*(String writer_id;//작성자id,
	 * String writer_name;//작성자명)*/
	private String today;
	private String resdate;
	
	public SurvWriteRequest() {
		
	}
	

	
	public SurvWriteRequest(Emp emp, String surv_tit, String surv_content, String today, String resdate) {
		
		this.emp = emp;
		this.surv_tit = surv_tit;
		this.surv_content = surv_content;
		this.today = today;
		this.resdate = resdate;
	}



	public SurvWriteRequest(int empno, String parameter, String parameter2, String parameter3, String parameter4) {
		// TODO Auto-generated constructor stub
	}



	public Emp getEmp() {
		return emp;
	}



	public void setEmp(Emp emp) {
		this.emp = emp;
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



	public String getToday() {
		return today;
	}



	public void setToday(String today) {
		this.today = today;
	}



	public String getResdate() {
		return resdate;
	}



	public void setResdate(String resdate) {
		this.resdate = resdate;
	}



	



	@Override
	public String toString() {
		return "SurvWriteRequest [emp=" + emp + ", surv_tit=" + surv_tit + ", surv_content=" + surv_content + ", today="
				+ today + ", resdate=" + resdate + "]";
	}
}
