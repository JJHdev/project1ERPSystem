package mypage.deletemodel;

import java.util.Date;

//p631
//article테이블에 해당하는 데이터를 관리하는 클래스
public class MypageEmp {
	private boolean delete;
	private Integer empNo; 
	private String eName;
	private String dName;
	private String empRank;
	private Date hiredate;
	
	public MypageEmp(Integer empNo, String eName, String dName, String empRank, Date hiredate) {
		this.empNo = empNo;
		this.eName = eName;
		this.dName = dName;
		this.empRank = empRank;
		this.hiredate = hiredate;
	}

	@Override
	public String toString() {
		return "MypageEmp [delete=" + delete + ", empNo=" + empNo + ", eName=" + eName + ", dName=" + dName
				+ ", empRank=" + empRank + ", hiredate=" + hiredate + "]";
	}

	public boolean isDelete() {
		return delete;
	}

	public Integer getEmpNo() {
		return empNo;
	}

	public String geteName() {
		return eName;
	}

	public String getdName() {
		return dName;
	}

	public String getEmpRank() {
		return empRank;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	public void setEmpRank(String empRank) {
		this.empRank = empRank;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	} 
}
