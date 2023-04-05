package offday.model;

import java.util.Date;

//업데이트 리쿼스트를 위한 모델이다.ResvUpdateRequest 와 동일한 목적이다.
public class OffDayUpdate {

	
	private int empno;
	private int deptno;
	private String deptname;
	private Date startday;
	private Date endday;
	private String offnotice;
	private int offdayno;
	
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public void setStartday(Date startday) {
		this.startday = startday;
	}
	public void setEndday(Date endday) {
		this.endday = endday;
	}
	public void setOffnotice(String offnotice) {
		this.offnotice = offnotice;
	}
	public void setOffdayno(int offdayno) {
		this.offdayno = offdayno;
	}
	public int getEmpno() {
		return empno;
	}
	public int getDeptno() {
		return deptno;
	}
	public String getDeptname() {
		return deptname;
	}
	public Date getStartday() {
		return startday;
	}
	public Date getEndday() {
		return endday;
	}
	public String getOffnotice() {
		return offnotice;
	}
	public int getOffdayno() {
		return offdayno;
	}
	public OffDayUpdate(int empno, int deptno, String deptname, Date startday, Date endday, String offnotice,
			int offdayno) {
		this.empno = empno;
		this.deptno = deptno;
		this.deptname = deptname;
		this.startday = startday;
		this.endday = endday;
		this.offnotice = offnotice;
		this.offdayno = offdayno;
	}
	@Override
	public String toString() {
		return "OffDayUpdate [empno=" + empno + ", deptno=" + deptno + ", deptname=" + deptname + ", startday="
				+ startday + ", endday=" + endday + ", offnotice=" + offnotice + ", offdayno=" + offdayno + "]";
	}
	

	
	
	
}
