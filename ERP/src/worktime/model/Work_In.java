package worktime.model;

import java.util.Date;


//Work_In 테이블에 해당하는 데이터를 첫번째로 받아 관리하는 클래스이다.
//        -> 받아서 Work_in_Data로 넘겨 사용할수있게한다.
//memberBoard의  article.model->Article.java에 해당한다.
public class Work_In {
	
	private Date work_in_time; //금일 출근시간
	private int empno; 	//사원번호 
	private String work_in_notice; //출근 특이사항을 적는다 ex)없음,단순지각,질병지각,기타
	
//Work_In 테이블의 DB의 데이터 받아서 (get)사용할 수 있게 연결준비 우클릭->소스 -> 겟터,toString,used	
	
	public Work_In(int empno,Date work_in_time, String work_in_notice) {
		this.empno = empno;
		this.work_in_time = work_in_time;
		this.work_in_notice = work_in_notice;
	}
	
	/*
	public int getTodayCheck(){
		return todaycheck==0; 
		} //게시글없니? 게시글없으면 true리턴
	 public boolean hasNoTodayCheck() { 
	
	return todaycheck == 0; } //게시글이 존재하니? 게시글있으면 true 
	
	public boolean hasTodayCheck() { return todaycheck > 0; }
	
	 */
	
	
	public Date getwork_in_time() {
		return work_in_time;
	}
	public int getEmpno() {
		return empno;
	}

	public String getwork_in_notice() {
		return work_in_notice;
	}

	
	public void setWork_in_time(Date work_in_time) {
		this.work_in_time = work_in_time;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	
	public void setWork_in_notice(String work_in_notice) {
		this.work_in_notice = work_in_notice;
	}

	@Override
	public String toString() {
		return "Work_In [work_in_time=" + work_in_time + ", empno=" + empno + ", work_in_notice=" + work_in_notice + "]";
	}
	}

