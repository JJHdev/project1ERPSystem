package worktime.model;

import java.sql.Date;

//End_OffDay 테이블에 해당하는 데이터를 첫번째로 받아 관리하는 클래스이다.
//-> 받아서 Work_in_Data로 넘겨 사용할수있게한다.
public class End_OffDay {

	private Date end_offday; //휴가 종료일
	private int empno;
	private int deptno; 
	private String end_half_off;//휴가 종료일에 반차여부 ->종료일에 오전만쉬고 오후 출근등 체크
	
	
	public End_OffDay(Date end_offday, int empno, int deptno, String end_half_off) {
		super();
		this.end_offday = end_offday;
		this.empno = empno;
		this.deptno = deptno;
		this.end_half_off = end_half_off;
	}
	
	//연차기록테이블 (offday_his)에 기록줘야해서 set도 선언했다.
	
	public void setEnd_offday(Date end_offday) {
		this.end_offday = end_offday;
	}



	public void setEmpno(int empno) {
		this.empno = empno;
	}



	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}



	public void setEnd_half_off(String end_half_off) {
		this.end_half_off = end_half_off;
	}



	public Date getEnd_offday() {
		return end_offday;
	}
	public int getEmpno() {
		return empno;
	}
	public int getDeptno() {
		return deptno;
	}
	public String getEnd_half_off() {
		return end_half_off;
	}
	
	@Override
	public String toString() {
		return "End_OffDay [end_offday=" + end_offday + ", empno=" + empno + ", deptno=" + deptno + ", end_half_off="
				+ end_half_off + "]";
	}
	
	
	
	
	
	
	
	
	
}
