package surv.service;

import java.util.Date;
import java.util.Map;

//글 수정을 위한 수정하는 사용자 id, 수정할 글번호, 수정할제목 수정할 설문제목,수정할 마감날짜를 제공하는 클래스
//
public class SurvModifyRequest {

	private String userId; //수정하는 사용자id
	private int empno;
	private int articleNumber; //수정할 글번호
	private String writer_name; //작성자name
	private String title; //수정할 제목
	private String content;//수정할 내용
	private Date res_date;
	
	public SurvModifyRequest(String userId, int articleNumber, String writer_name, String title, String content,
			Date res_date) {
		this.userId = userId;
		this.articleNumber = articleNumber;
		this.writer_name = writer_name;
		this.title = title;
		this.content = content;
		this.res_date = res_date;
	}



	public SurvModifyRequest(int empno, int articleNumber, String writer_name, String title, String content,
			Date res_date) {
		this.empno = empno;
		this.articleNumber = articleNumber;
		this.writer_name = writer_name;
		this.title = title;
		this.content = content;
		this.res_date = res_date;
		// TODO Auto-generated constructor stub
	}



	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public int  getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public int getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(int articleNumber) {
		this.articleNumber = articleNumber;
	}

	public String getWriter_name() {
		return writer_name;
	}

	public void setWriter_name(String writer_name) {
		this.writer_name = writer_name;
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

	public Date getRes_date() {
		return res_date;
	}

	public void setRes_date(Date res_date) {
		this.res_date = res_date;
	}

	public void validate(Map<String,Boolean> errors) {
		if(title==null || title.trim().isEmpty()) {
			errors.put("title",Boolean.TRUE);
		}
	}



	@Override
	public String toString() {
		return "SurvModifyRequest [userId=" + userId + ", empno=" + empno + ", articleNumber=" + articleNumber
				+ ", writer_name=" + writer_name + ", title=" + title + ", content=" + content + ", res_date="
				+ res_date + "]";
	}
	
}
