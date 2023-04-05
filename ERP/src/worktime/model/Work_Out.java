package worktime.model;

import java.util.Date;

//Work_out 테이블에 해당하는 데이터를 첫번째로 받아 관리하는 클래스이다.
//-> 받아서 Work_in_Data로 넘겨 사용할수있게한다.
//memberBoard의  article.model->Article.java에 해당한다.
public class Work_Out {

	//퇴근기록
	private Date work_out_time;
	private int empno;
	private String work_out_notice;
	private Date work_in_time;
	private String work_in_notice;
	
	
	
	
	
	
	public Work_Out(int empno,Date work_out_time, String work_out_notice, Date work_in_time, String work_in_notice) {
		
		this.empno = empno;
		this.work_out_time = work_out_time;
		this.work_out_notice = work_out_notice;
		this.work_in_time = work_in_time;
		this.work_in_notice = work_in_notice;
	}
	public void setWork_out_time(Date work_out_time) {
		this.work_out_time = work_out_time;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public void setWork_out_notice(String work_out_notice) {
		this.work_out_notice = work_out_notice;
	}
	public void setWork_in_time(Date work_in_time) {
		this.work_in_time = work_in_time;
	}
	public void setWork_in_notice(String work_in_notice) {
		this.work_in_notice = work_in_notice;
	}
	public Date getWork_out_time() {
		return work_out_time;
	}
	public int getEmpno() {
		return empno;
	}
	public String getWork_out_notice() {
		return work_out_notice;
	}
	public Date getWork_in_time() {
		return work_in_time;
	}
	public String getWork_in_notice() {
		return work_in_notice;
	}
	@Override
	public String toString() {
		return "Work_Out [work_out_time=" + work_out_time + ", empno=" + empno + ", work_out_notice=" + work_out_notice
				+ ", work_in_time=" + work_in_time + ", work_in_notice=" + work_in_notice + "]";
	}
	
	
	
	
	
	
}
