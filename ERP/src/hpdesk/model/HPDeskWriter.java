package hpdesk.model;
//hpdesk테이블에(작성자명,사원번호) 해당하는 데이터를 관리하는 클래스
public class HPDeskWriter {
	private String ename;//작성자
	private int empno;//사원번호
	
	public HPDeskWriter(){
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public HPDeskWriter(String ename, int empno) {
		this.ename = ename;
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public int getEmpno() {
		return empno;
	}
	@Override
	public String toString() {
		return "HPDeskWriter [ename=" + ename + ", empno=" + empno + "]";
	}
}
