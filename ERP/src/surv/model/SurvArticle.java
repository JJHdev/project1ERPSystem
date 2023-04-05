package surv.model;

import java.util.Date;
import java.util.Map;

//p631
//survArticle테이블에 해당하는 데이터를 관리하는 클래스
public class SurvArticle {
	private int surv_no; //글번호 surv_no
	private int survWriter_no;///사원번호
	private String surv_tit;//제목surv_tit
	private String surv_writer;// 작성자 회원이름과 번호 저장
	private int survReq_cnt; //조회수
	private int result_count;//응답자수
	private Date surv_regdate;//최초작성일    regdate
	private Date surv_resdate;//설문 종료날짜resdate
	private String surv_isshow;//설문 결과 공개 비공개여부 기본 Y(결과노출), N(결과는 관리자만)
	private String surv_content;
	
	public SurvArticle() {
	}
	public SurvArticle(int surv_no) {
		this.surv_no=surv_no;
	}
	

	
	public SurvArticle(String surv_tit, Date surv_regdate, Date surv_resdate, String surv_isshow) {
		this.surv_tit = surv_tit;
		this.surv_regdate = surv_regdate;
		this.surv_resdate = surv_resdate;
		this.surv_isshow = surv_isshow;
	}
	public SurvArticle(int surv_no, int survWriter_no, String surv_tit, String surv_writer, int survReq_cnt,
			int result_count, Date surv_regdate, Date surv_resdate, String surv_isshow) {
		this.surv_no = surv_no;
		this.survWriter_no = survWriter_no;
		this.surv_tit = surv_tit;
		this.surv_writer = surv_writer;
		this.survReq_cnt = survReq_cnt;
		this.result_count = result_count;
		this.surv_regdate = surv_regdate;
		this.surv_resdate = surv_resdate;
		this.surv_isshow = surv_isshow;
	}
	
	public SurvArticle(int surv_no, String surv_tit, String surv_writer, int survReq_cnt, int result_count, Date surv_regdate, Date surv_resdate,
			String surv_isshow) {
		this.surv_no = surv_no;
		this.surv_tit = surv_tit;
		this.surv_writer = surv_writer;
		this.survReq_cnt = survReq_cnt;
		this.result_count = result_count;
		this.surv_regdate = surv_regdate;
		this.surv_resdate = surv_resdate;
		this.surv_isshow = surv_isshow;	
		}
	
	//상세조회용
	public SurvArticle(int surv_no, String surv_tit, String surv_writer, int survReq_cnt, Date surv_regdate, Date surv_resdate, String surv_isshow) {
		this.surv_no = surv_no;
		this.surv_tit = surv_tit;
		this.surv_writer = surv_writer;
		this.survReq_cnt = survReq_cnt;
		this.surv_regdate = surv_regdate;
		this.surv_resdate = surv_resdate;
		this.surv_isshow = surv_isshow;
	}
	public SurvArticle(String surv_tit, String surv_writer, Date surv_regdate, Date surv_resdate,
			String surv_content) {
		this.surv_tit = surv_tit;
		this.surv_writer = surv_writer;
		this.surv_regdate = surv_regdate;
		this.surv_resdate = surv_resdate;
		this.surv_content = surv_content;
	}
	public SurvArticle(String surv_tit) {
		this.surv_tit=surv_tit;
	}
	public int getSurv_no() {
		return surv_no;
	}
	public void setSurv_no(int surv_no) {
		this.surv_no = surv_no;
	}
	public int getSurvWriter_no() {
		return survWriter_no;
	}
	public void setSurvWriter_no(int survWriter_no) {
		this.survWriter_no = survWriter_no;
	}
	public String getSurv_tit() {
		return surv_tit;
	}
	public void setSurv_tit(String surv_tit) {
		this.surv_tit = surv_tit;
	}
	public String getSurv_writer() {
		return surv_writer;
	}
	public void setSurv_writer(String surv_writer) {
		this.surv_writer = surv_writer;
	}
	public int getSurvReq_cnt() {
		return survReq_cnt;
	}
	public void setSurvReq_cnt(int survReq_cnt) {
		this.survReq_cnt = survReq_cnt;
	}
	public int getResult_count() {
		return result_count;
	}
	public void setResult_count(int result_count) {
		this.result_count = result_count;
	}
	public Date getSurv_regdate() {
		return surv_regdate;
	}
	public void setSurv_regdate(Date surv_regdate) {
		this.surv_regdate = surv_regdate;
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
	public void setSurv_content(String surv_content) {
		this.surv_content = surv_content;
	}
	public String getSurv_content() {
		return surv_content;
	}
	
	
	@Override
	public String toString() {
		return "SurvArticle [surv_no=" + surv_no + ", survWriter_no=" + survWriter_no + ", surv_tit=" + surv_tit
				+ ", surv_writer=" + surv_writer + ", survReq_cnt=" + survReq_cnt + ", result_count=" + result_count
				+ ", surv_regdate=" + surv_regdate + ", surv_resdate=" + surv_resdate + ", surv_isshow=" + surv_isshow
				+ ", surv_content=" + surv_content + "]";
	}
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void validate(Map<String,Boolean> errors) {
		if( surv_tit==null || surv_tit.isEmpty()){
			errors.put("surv_tit",Boolean.TRUE);
		}
		if( surv_content==null || surv_content.isEmpty()){
			errors.put("surv_content",Boolean.TRUE);
		}
	}
	
	
}
	
	
	

