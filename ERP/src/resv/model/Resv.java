package resv.model;

import java.util.Date;
import java.util.Map;

import org.apache.catalina.User;

//회의실 예약한 예약자 데이터를 저장하는 모델
public class Resv {

	//필드
	private int resvno;
	private int empno;
	private Date resvdate;
	private String roomname;
	private String resvmemo;
	private String resvname;
	private String resvtel;
	private String resvemail;
	
	
	//생성자
	public Resv (int resvno, int empno, Date resvdate, String roomname, String resvmemo, String resvname, String resvtel,
			String resvemail) {
		this.resvno = resvno;
		this.empno = empno;
		this.resvdate = resvdate;
		this.roomname = roomname;
		this.resvmemo = resvmemo;
		this.resvname = resvname;
		this.resvtel = resvtel;
		this.resvemail = resvemail;
	}


	public Resv (int empno, Date resvdate, String roomname, String resvmemo, String resvname, String resvtel,
			String resvemail) {
		this.empno = empno;
		this.resvdate = resvdate;
		this.roomname = roomname;
		this.resvmemo = resvmemo;
		this.resvname = resvname;
		this.resvtel = resvtel;
		this.resvemail = resvemail;
	}


	public Resv() {
		// TODO Auto-generated constructor stub
	}


	//메서드
	public int getResvno() {
		return resvno;
	}


	public int getEmpno() {
		return empno;
	}


	public Date getResvdate() {
		return resvdate;
	}


	public String getRoomname() {
		return roomname;
	}


	public String getResvmemo() {
		return resvmemo;
	}


	public String getResvname() {
		return resvname;
	}


	public String getResvtel() {
		return resvtel;
	}


	public String getResvemail() {
		return resvemail;
	}


	public void setResvno(int resvno) {
		this.resvno = resvno;
	}


	public void setEmpno(int empno) {
		this.empno = empno;
	}


	public void setResvdate(Date resvdate) {
		this.resvdate = resvdate;
	}


	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}


	public void setResvmemo(String resvmemo) {
		this.resvmemo = resvmemo;
	}


	public void setResvname(String resvname) {
		this.resvname = resvname;
	}


	public void setResvtel(String resvtel) {
		this.resvtel = resvtel;
	}


	public void setResvemail(String resvemail) {
		this.resvemail = resvemail;
	}


	@Override
	public String toString() {
		return "Resv [resvno=" + resvno + ", empno=" + empno + ", resvdate=" + resvdate + ", roomname=" + roomname
				+ ", resvmemo=" + resvmemo + ", resvname=" + resvname + ", resvtel=" + resvtel + ", resvemail="
				+ resvemail + "]";
	}

	

	//유효성검사
	public void validate(Map<String, Boolean> errors) {
		//필수입력 체크메서드 호출
		checkEmpty(errors,this.roomname,"roomname"); //회의실 필수선택
		checkEmpty(errors,this.resvname,"resvname"); //사용자 이름
		checkEmpty(errors,this.resvtel,"resvtel"); //사용자 연락처
		checkEmpty(errors,this.resvemail,"resvemail"); //사용자 이메일

	}
	
	//필수입력체크  p595 60라인
	private void checkEmpty(Map<String, Boolean> errors,
			String value, String fieldName) {
		if(value==null || value.isEmpty() ) {
			//key는 회원가입폼 각 input요소의 name속성값
			//유저가 입력(선택)한 값을 value
			errors.put(fieldName, Boolean.TRUE);
		}
	}
		
	
	
}
