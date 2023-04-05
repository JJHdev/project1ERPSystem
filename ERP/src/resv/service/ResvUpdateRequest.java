package resv.service;

import java.util.Date;
import java.util.Map;

//예약수정 클래스
public class ResvUpdateRequest {
	
	private int empno;
	private Date resvdate;
	private String roomname;
	private String resvmemo;
	private String resvname;
	private String resvtel;
	private String resvemail;
	private int resvno;
	
	public ResvUpdateRequest(int empno, Date resvdate, String roomname, String resvmemo, String resvname, String resvtel,
			String resvemail, int resvno) {
		this.empno = empno;
		this.resvdate = resvdate;
		this.roomname = roomname;
		this.resvmemo = resvmemo;
		this.resvname = resvname;
		this.resvtel = resvtel;
		this.resvemail = resvemail;
		this.resvno = resvno;
	}
	


	public ResvUpdateRequest() {
		// TODO Auto-generated constructor stub
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


	public int getResvno() {
		return resvno;
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


	public void setResvno(int resvno) {
		this.resvno = resvno;
	}


	@Override
	public String toString() {
		return "ResvUpdateRequest [empno=" + empno + ", resvdate=" + resvdate + ", roomname=" + roomname + ", resvmemo="
				+ resvmemo + ", resvname=" + resvname + ", resvtel=" + resvtel + ", resvemail=" + resvemail
				+ ", resvno=" + resvno + "]";
	}



	public void validate(Map<String, Boolean> errors) {
		if(resvdate==null) {
			errors.put("resvdate", Boolean.TRUE);
		}
		if(roomname==null) {
			errors.put("roomname", Boolean.TRUE);
		}
		if(resvname==null || resvname.trim().isEmpty()) {
			errors.put("resvname", Boolean.TRUE);
		}
		if(resvtel==null || resvtel.trim().isEmpty()) {
			errors.put("resvtel", Boolean.TRUE);
		}
		if(resvemail==null || resvemail.trim().isEmpty()) {
			errors.put("resvemail", Boolean.TRUE);
		}
	}
	



	
	
	
	
}