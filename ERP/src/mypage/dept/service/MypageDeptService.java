package mypage.dept.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import mypage.dept.dao.MypageDeptDAO;

public class MypageDeptService {
	
	private MypageDeptDAO mypageDeptDAO = new MypageDeptDAO();

	public int deptNoDuplicate(int newDeptno) {
		Connection conn = null;
		int result1 = 0;
		try {
			conn = ConnectionProvider.getConnection();
			result1 = mypageDeptDAO.deptnoDuplicate(newDeptno,conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		return result1;
	}
	public int deptNameDuplicate(String newDeptName){
		Connection conn = null;
		int result2 = 0 ;
		try {
			conn = ConnectionProvider.getConnection();
			result2 = mypageDeptDAO.deptNameDuplicate(newDeptName,conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
		}
		return result2;
	}
	public int insertDept(int deptno, String deptname){
		Connection conn = null;
		int result3=0;
		try {
			conn = ConnectionProvider.getConnection();
			result3= mypageDeptDAO.insertDept(deptno,deptname,conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		return result3;
	}
	public int EmpdeptNoDuplicate(int newDeptno) {
		Connection conn =null;;
		int result4 =0;
		try {
			conn = ConnectionProvider.getConnection();
			result4 = mypageDeptDAO.empDeptnoDuplicate(newDeptno,conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		return result4;
	}
	public int deleteDept(int deptno){
		Connection conn = null;
		int result5=0;
		try {
			conn = ConnectionProvider.getConnection();
			result5= mypageDeptDAO.deleteDept(deptno,conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
		}
		return result5;
	}
}
