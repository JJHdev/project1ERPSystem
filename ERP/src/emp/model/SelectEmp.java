package emp.model;

import java.util.Date;

public class SelectEmp {
	
	private int empno;
	private int deptno;
	private String ename;
	private String erprank;
	private String email;
	private String tel;
	private Date hiredate;
	private String deptname;
	private int sal;
	private int grade;
	private String emppwd;
	
	//0.부서명, 직책
	public SelectEmp(String deptname) {
		this.deptname = deptname;
	}
	
	//1.기본조회
	public SelectEmp(int empno, int deptno, String ename, String erprank, String email, String tel, Date hiredate,
			String deptname) {
		this.empno = empno;
		this.deptno = deptno;
		this.ename = ename;
		this.erprank = erprank;
		this.email = email;
		this.tel = tel;
		this.hiredate = hiredate;
		this.deptname = deptname;
	}

	//2.상세조회
	public SelectEmp(int empno, int deptno, String ename, String erprank, String email, String tel, Date hiredate,
			String deptname, int sal, int grade, String emppwd) {
		this.empno = empno;
		this.deptno = deptno;
		this.ename = ename;
		this.erprank = erprank;
		this.email = email;
		this.tel = tel;
		this.hiredate = hiredate;
		this.deptname = deptname;
		this.sal = sal;
		this.grade = grade;
		this.emppwd = emppwd;
	}
	//3.수정
	public SelectEmp(int empno, int deptno, String ename, String erprank, String email, String tel, Date hiredate,
			int sal, int grade, String emppwd) {
		this.empno = empno;
		this.deptno = deptno;
		this.ename = ename;
		this.erprank = erprank;
		this.email = email;
		this.tel = tel;
		this.hiredate = hiredate;
		this.sal = sal;
		this.grade = grade;
		this.emppwd = emppwd;
	}
	//4.삭제
	public SelectEmp(int empno) {
		this.empno = empno;
	}
	
	
	
	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getErprank() {
		return erprank;
	}

	public void setErprank(String erprank) {
		this.erprank = erprank;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getEmppwd() {
		return emppwd;
	}

	public void setEmppwd(String emppwd) {
		this.emppwd = emppwd;
	}

	
	
	
	
	
	
}
