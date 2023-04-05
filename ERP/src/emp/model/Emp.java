package emp.model;

import java.util.Date;
import java.util.Map;

public class Emp {
	//필드 emp테이블의 목록
	private int empno;
	private String ename;
	private String emppwd;
	private int deptno;
	private String deptname;
	private String erprank;
	private String email;
	private String tel;
	private Date hiredate;
	private int sal;
	private int grade;
	private String re_emppwd; 
	
	//생성자
	
	public Emp(int empno, String ename, String emppwd, int deptno, String erprank, String email, String tel,
			Date hiredate, int sal, int grade) {
		this.empno = empno;
		this.ename = ename;
		this.emppwd = emppwd;
		this.deptno = deptno;
		this.erprank = erprank;
		this.email = email;
		this.tel = tel;
		this.hiredate = hiredate;
		this.sal = sal;
		this.grade = grade;
		this.re_emppwd = re_emppwd;
	}
	//기본생성자 - 혹시나 부분적으로 필요할 때 객체를 만들어 담기위해 만듬
	public Emp(){
		
	}
	public Emp(int empno,String ename,int deptno,String deptname,String erprank,int grade) {
		this.empno =empno;
		this.ename =ename;
		this.deptno =deptno;
		this.deptname =deptname;
		this.erprank=erprank;
		this.grade=grade;
	}
	
	//비밀번호 재확인을 빼고 만든 생성자로 최종적으로 db에 들어가는 객체
	public Emp(int empno, String ename, String emppwd, int deptno, String deptname, String erprank, String email,
			String tel, Date hiredate,int sal, int grade) {
		this.empno = empno;
		this.ename = ename;
		this.emppwd = emppwd;
		this.deptno = deptno;
		this.deptname = deptname;
		this.erprank = erprank;
		this.email = email;
		this.tel = tel;
		this.hiredate = hiredate;
		this.sal = sal;
		this.grade = grade;
	}
	
	public Emp(String deptname) {
		this.deptname = deptname;
	}
	
	
	public Emp(int empno) {
		this.empno=empno;
	}
	//게터세터 메소드
	public int getEmpno() {
		return empno;
	}


	public void setEmpno(int empno) {
		this.empno = empno;
	}


	public String getEname() {
		return ename;
	}


	public void setEname(String ename) {
		this.ename = ename;
	}


	public String getEmppwd() {
		return emppwd;
	}


	public void setEmppwd(String emppwd) {
		this.emppwd = emppwd;
	}


	public int getDeptno() {
		return deptno;
	}


	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}


	public String getDeptname() {
		return deptname;
	}


	public void setDeptname(String deptname) {
		this.deptname = deptname;
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


	public String getRe_emppwd() {
		return re_emppwd;
	}


	public void setRe_emppwd(String re_emppwd) {
		this.re_emppwd = re_emppwd;
	}//게터세터 메소드 끝


	//비밀번호 일치하는지 확인하는 메소드
	public boolean matchPassword(String pwd) {
		return emppwd.equals(pwd);
	}
	
	public boolean matchDname(String dname) {
		return deptname.equals(dname);
	}
	
	public boolean matchEmail(String Email) {
		return email.equals(Email);
	}
	
	public boolean matchGrade(int Grade) {
		return grade==Grade;
	}
	
	public boolean matcherprank(String Eprank) {
		return erprank.equals(Eprank);
	}
	
	public boolean matchSal(int Sal) {
		return sal==Sal;
	}
	
	public boolean matchTel(String Tel) {
		return tel.equals(Tel);
	}
	
	//비번과 재확인용 비번 일치체크-p594 44라인
	public boolean isPasswordEqualsToConfirm() {
		System.out.println("여기지나가");
		return emppwd != null && emppwd.equals(re_emppwd);
		
	}//null이 아니면서 둘이 동일하니? 필수입력&& 비밀번호 일치 
	
	
	//유효성검사-p595 48라인
	public void validate(Map<String, Boolean> errors) {
	//필수입력체크 메서드호출
		String empno1=String.valueOf(empno);
		
		checkEmpty(errors,empno1,"empno");
		checkEmpty(errors,ename,"ename"); //이름필수입력
		checkEmpty(errors,emppwd,"emppwd"); //비번 필수입력
		checkEmpty(errors,re_emppwd,"re_emppwd"); //비번재확인필수입력
		if(!errors.containsKey("re_emppwd")) { 
			if(!isPasswordEqualsToConfirm()){
				errors.put("notMatch",Boolean.TRUE);
			}
		}
	}//validate 끝	
		
	//필수입력체크-p595 60라인
	//여기서 value는 유저가 입력한 값,fieldName(key) 은 jsp페이지에서 태그의 name들//key는 회원가입폼안의 각 input요소의 name속성값과 같다.
	private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if(value==null || value.isEmpty()){
			errors.put(fieldName,Boolean.TRUE);
		}
	}//checkEmpty;
	@Override
	public String toString() {
		return "Emp [empno=" + empno + ", ename=" + ename + ", emppwd=" + emppwd + ", deptno=" + deptno + ", deptname="
				+ deptname + ", erprank=" + erprank + ", email=" + email + ", tel=" + tel + ", hiredate=" + hiredate
				+ ", sal=" + sal + ", grade=" + grade + ", re_emppwd=" + re_emppwd + "]";
	}
	
	
	
	
	
	
	
	
	
	
	}



	
	
	

