package offday.model;

import java.sql.Date;
import java.util.List;

//End_OffDay와Start_OffDay  테이블에 해당하는 데이터를 받아 조인,활용하기 위한 클래스이다.
//-> 받아서 Work_in_Data로 넘겨 사용할수있게한다.

public class OffDay {
	
	
	private int empno;
	private int deptno;
	private String deptname;
	private Date startday;
	private Date endday;
	private String offnotice;
	private int offdayno;
	private List<OffDay> offdays;
	
	public int getOffdayno() {
		return offdayno;
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

	//오프데이 리스트를위한 생성자
	public OffDay(List<OffDay> offdays) {
		this.offdays=offdays;
	}
	
	//오프데이 리스트 게터세터
	public List<OffDay> getOffdays() {
		return offdays;
	}

	public OffDay(int empno, int deptno, String deptname, Date startday, Date endday, String offnotice,
			int offdayno) {
		this.empno = empno;
		this.deptno = deptno;
		this.deptname = deptname;
		this.startday = startday;
		this.endday = endday;
		this.offnotice = offnotice;
		this.offdayno = offdayno;
	}
	
	
	public void setOffdays(List<OffDay> offdays) {
		this.offdays = offdays;
	}

	@Override
	public String toString() {
		return "OffDay [empno=" + empno + ", deptno=" + deptno + ", deptname=" + deptname + ", startday="
				+ startday + ", endday=" + endday + ", offnotice=" + offnotice + ", offdays=" + offdays
				+ ", offdayno=" + offdayno + "]";
	}

	
	
	
	
	
}
