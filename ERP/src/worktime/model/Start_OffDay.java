package worktime.model;

import java.sql.Date;

//Start_OffDay 테이블에 해당하는 데이터를 첫번째로 받아 관리하는 클래스이다.
//-> 받아서 Work_in_Data로 넘겨 사용할수있게한다.
//memberBoard의  article.model->Article.java에 해당한다.
public class Start_OffDay {

	private Date start_offday; //휴가 시작일
	private int empno;
	private int deptno; 
	private String start_half_off; 
		//반차여부기록 (오전반차,오후반차 시간대기준으로 나눌꺼다),특정한곳에서카운트예정
		//휴가 시작일, 종료일로 사용한 연차수 구하는 공식 java에 제작예정(공식유지보수필요없는부분이라)
	

	public Date getStart_offday() {
		return start_offday;
	}
	public Start_OffDay(Date start_offday, int empno, int deptno, String start_half_off) {
		super();
		this.start_offday = start_offday;
		this.empno = empno;
		this.deptno = deptno;
		this.start_half_off = start_half_off;
	}
	
	//연차기록테이블 (offday_his)에 기록줘야해서 set도 선언했다.

	public int getEmpno() {
		return empno;
	}
	public void setStart_offday(Date start_offday) {
		this.start_offday = start_offday;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public void setStart_half_off(String start_half_off) {
		this.start_half_off = start_half_off;
	}
	public int getDeptno() {
		return deptno;
	}
	public String getStart_half_off() {
		return start_half_off;
	}
	
	@Override
	public String toString() {
		return "Start_OffDay [start_offday=" + start_offday + ", empno=" + empno + ", deptno=" + deptno
				+ ", start_half_off=" + start_half_off + "]";
	}





	
}
