package mypage.dept.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import emp.model.MypageDbEmp;
import jdbc.JdbcUtil;

public class MypageDeptDAO {

	public int deptnoDuplicate(int newDeptno, Connection conn) throws SQLException {
		//3.객체준비  pstmt, rs준비
		String sql = "select deptno,deptname " + 
				 	 "from erpdb.dept " + 
				 	 "where deptno=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			//4.쿼리실행 select~
			stmt = conn.prepareStatement(sql);
			//stmt객체.set데이타입(?숫서,값)
			stmt.setInt(1, newDeptno);
			rs = stmt.executeQuery();
			if(rs.next()) { //select실행결과가 있으면 true리턴
				result = 1;
			}else {//select실행결과가 있으면 false리턴
				result = 0;
			}
		}finally {	
			//5.자원반납-> JdbcUtil.close()
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		return result;
	}
	public int deptNameDuplicate(String newDeptName, Connection conn) throws SQLException {
		//3.객체준비  pstmt, rs준비
		String sql = "select deptno,deptname " + 
					 "from erpdb.dept " + 
				     "where deptname=?";
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			//4.쿼리실행 select~
			stmt = conn.prepareStatement(sql);
			//stmt객체.set데이타입(?숫서,값)
			stmt.setString(1,newDeptName);
			rs = stmt.executeQuery();
			if(rs.next()) { //select실행결과가 있으면 true리턴
				result = 1;
			}else {//select실행결과가 있으면 false리턴
				result = 0;
			}
		}finally {	
			//5.자원반납-> JdbcUtil.close()
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		return result;
	}
	public int insertDept(int deptno, String deptname, Connection conn) throws SQLException {
		String sql= "insert into dept(deptno,deptname) "+
			    "values(?,?) " ;
		PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, deptno);
			pstmt.setString(2, deptname);
		int result = pstmt.executeUpdate();
		return result;
	}
	public int empDeptnoDuplicate(int newDeptno, Connection conn) throws SQLException {
		//3.객체준비  pstmt, rs준비
		String sql = "select deptno "+
				 	"from erpdb.emp  " + 
				 	"where deptno=? ";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			//4.쿼리실행 select~
			stmt = conn.prepareStatement(sql);
			//stmt객체.set데이타입(?숫서,값)
			stmt.setInt(1, newDeptno);
			rs = stmt.executeQuery();
			if(rs.next()) { //select실행결과가 있으면 true리턴
				result = 1;
			}else {//select실행결과가 있으면 false리턴
				result = 0;
			}
		}finally {	
			//5.자원반납-> JdbcUtil.close()
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
		return result;
	}
	public int deleteDept(int deptno, Connection conn) throws SQLException {
		String sql= "delete from erpdb.dept "+
			    	"where deptno=? " ;
		PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, deptno);
		int result = pstmt.executeUpdate();
		return result;
	}
   public List<MypageDbEmp> deptAllSelect(Connection conn) throws SQLException {
	      String sql = "select deptno,deptname "+ 
	                "from erpdb.dept  ";
	      PreparedStatement stmt = null;
	      ResultSet rs = null;
	      List<MypageDbEmp> deptList = new ArrayList<MypageDbEmp>();
	      try {
	         stmt = conn.prepareStatement(sql);
	         rs = stmt.executeQuery();
	         while(rs.next()) {
	            MypageDbEmp MypageDbEmp =new MypageDbEmp(
	                  rs.getInt("deptno"),
	                  rs.getString("deptname")
	                  );
	            deptList.add(MypageDbEmp);
	         }
	      }finally {   
	         //5.자원반납-> JdbcUtil.close()
	         JdbcUtil.close(rs);
	         JdbcUtil.close(stmt);
	      }
	      return deptList;
	   }
}
