package emp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import emp.model.SelectEmp;
import jdbc.JdbcUtil;

public class SelectEmpDAO {

	
	//부서명,직급 조회
	public static List<SelectEmp> select_deptname(Connection conn)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SelectEmp> selectEmpList = new ArrayList<>();
		String sql = "select deptname "+
				"from dept " + 
				"group by deptname";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String deptname =rs.getString("deptname");
				SelectEmp selectEmp = new SelectEmp(deptname);
				selectEmpList.add(selectEmp);
				System.out.println("user에게 받은 파라미터로 select한후 한 내용을 Emp객체에 저장한 내용" + selectEmpList);
			}
			System.out.println("selectEmpList="+selectEmpList);
			return selectEmpList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	public static List<SelectEmp> select_erprank(Connection conn)throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SelectEmp> selectEmpList = new ArrayList<>();
		String sql = "select erprank "+
					 "from emp " + 
					 "group by erprank";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String erprank =rs.getString("erprank");
				SelectEmp selectEmp = new SelectEmp(erprank);
				selectEmpList.add(selectEmp);
				System.out.println("user에게 받은 파라미터로 select한후 한 내용을 Emp객체에 저장한 내용" + selectEmpList);
			}
			return selectEmpList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	
	
	
	//selectEx01_Simple ~selectEx08_Simple은 기본조회
	
	public static int selectEx01_Simple_Count(String deptName,String erpRank,String EmpName,Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(e.empno) "+
				"from emp e join dept d on e.deptno=d.deptno " + 
				"where d.deptname=? and e.erprank=? and e.ename=? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,deptName);
			pstmt.setString(2,erpRank);
			pstmt.setString(3,EmpName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	public static List<SelectEmp> selectEx01_Simple(String deptName,String erpRank,String EmpName, Connection conn,int startRow,int size) throws SQLException{
		System.out.println("EmpDAO접근 및 selectEx01_Simple()의 파라미터로 받아온 사원명="+deptName+" 직책="+erpRank+" 사원명="+EmpName);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SelectEmp> selectEmpList = new ArrayList<>();
		String sql = "select e.empno,e.deptno,e.ename,e.erprank,e.email,e.tel,e.hiredate,d.deptname "+
					 "from emp e join dept d on e.deptno=d.deptno " + 
					 "where d.deptname=? and "+
					 "e.erprank=? and "+
					 "e.ename=? "+
					 "order by e.empno desc limit ?,?";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,deptName);
			pstmt.setString(2,erpRank);
			pstmt.setString(3,EmpName);
			pstmt.setInt(4,startRow);
			pstmt.setInt(5,size);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int empno =rs.getInt("e.empno");
				int deptno=rs.getInt("e.deptno");
				String ename =rs.getString("e.ename");
				String erprank=rs.getString("e.erprank");
				String email=rs.getString("e.email");
				String tel=rs.getString("e.tel");
				Date hiredate = toDate(rs.getTimestamp("e.hiredate"));
				String deptname = rs.getString("d.deptname");
				
				SelectEmp selectEmp = new SelectEmp(empno,deptno,ename,
						erprank,email,tel,hiredate,deptname);
				selectEmpList.add(selectEmp);
				System.out.println("user에게 받은 파라미터로 select한후 한 내용을 Emp객체에 저장한 내용" + selectEmpList);
			}
			return selectEmpList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	
	public static int selectEx02_Simple_Count(String erpRank,String EmpName,Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(e.empno) "+
				"from emp e join dept d on e.deptno=d.deptno " + 
				"where e.erprank=? and e.ename=? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,erpRank);
			pstmt.setString(2,EmpName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	public static List<SelectEmp> selectEx02_Simple(String erpRank,String EmpName, Connection conn,int startRow,int size) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SelectEmp> selectEmpList = new ArrayList<>();
		String sql = "select e.empno,e.deptno,e.ename,e.erprank,e.email,e.tel,e.hiredate,d.deptname "+
				"from emp e join dept d on e.deptno=d.deptno " + 
				"where e.erprank=? and "+
				"e.ename=? "+
				"order by e.empno desc limit ?,?";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,erpRank);
			pstmt.setString(2,EmpName);
			pstmt.setInt(3,startRow);
			pstmt.setInt(4,size);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int empno =rs.getInt("e.empno");
				int deptno=rs.getInt("e.deptno");
				String ename =rs.getString("e.ename");
				String erprank=rs.getString("e.erprank");
				String email=rs.getString("e.email");
				String tel=rs.getString("e.tel");
				Date hiredate = toDate(rs.getTimestamp("e.hiredate"));
				String deptname = rs.getString("d.deptname");
				
				SelectEmp selectEmp = new SelectEmp(empno,deptno,ename,
						erprank,email,tel,hiredate,deptname);
				selectEmpList.add(selectEmp);
				System.out.println("user에게 받은 파라미터로 select한후 한 내용을 Emp객체에 저장한 내용" + selectEmpList);
			}
			return selectEmpList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	
	public static int selectEx03_Simple_Count(String deptName,String EmpName,Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(e.empno) "+
				"from emp e join dept d on e.deptno=d.deptno " + 
				"where d.deptname=? and e.ename=?  ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,deptName);
			pstmt.setString(2,EmpName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	public static List<SelectEmp> selectEx03_Simple(String deptName,String EmpName, Connection conn,int startRow,int size) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SelectEmp> selectEmpList = new ArrayList<>();
		String sql = "select e.empno,e.deptno,e.ename,e.erprank,e.email,e.tel,e.hiredate,d.deptname "+
				"from emp e join dept d on e.deptno=d.deptno " + 
				"where d.deptname=? and "+
				"e.ename=? "+
				"order by e.empno desc limit ?,?";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,deptName);
			pstmt.setString(2,EmpName);
			pstmt.setInt(3,startRow);
			pstmt.setInt(4,size);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int empno =rs.getInt("e.empno");
				int deptno=rs.getInt("e.deptno");
				String ename =rs.getString("e.ename");
				String erprank=rs.getString("e.erprank");
				String email=rs.getString("e.email");
				String tel=rs.getString("e.tel");
				Date hiredate = toDate(rs.getTimestamp("e.hiredate"));
				String deptname = rs.getString("d.deptname");
				
				SelectEmp selectEmp = new SelectEmp(empno,deptno,ename,
						erprank,email,tel,hiredate,deptname);
				selectEmpList.add(selectEmp);
				System.out.println("user에게 받은 파라미터로 select한후 한 내용을 Emp객체에 저장한 내용" + selectEmpList);
			}
			return selectEmpList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	
	public static int selectEx04_Simple_Count(String EmpName,Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(e.empno) "+
				"from emp e join dept d on e.deptno=d.deptno " + 
				"where e.ename=? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,EmpName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	public static List<SelectEmp> selectEx04_Simple(String EmpName, Connection conn,int startRow,int size) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SelectEmp> selectEmpList = new ArrayList<>();
		String sql = "select e.empno,e.deptno,e.ename,e.erprank,e.email,e.tel,e.hiredate,d.deptname "+
				"from emp e join dept d on e.deptno=d.deptno " + 
				"where e.ename=? "+
				"order by e.empno desc limit ?,?";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,EmpName);
			pstmt.setInt(2,startRow);
			pstmt.setInt(3,size);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int empno =rs.getInt("e.empno");
				int deptno=rs.getInt("e.deptno");
				String ename =rs.getString("e.ename");
				String erprank=rs.getString("e.erprank");
				String email=rs.getString("e.email");
				String tel=rs.getString("e.tel");
				Date hiredate = toDate(rs.getTimestamp("e.hiredate"));
				String deptname = rs.getString("d.deptname");
				
				SelectEmp selectEmp = new SelectEmp(empno,deptno,ename,
						erprank,email,tel,hiredate,deptname);
				selectEmpList.add(selectEmp);
				System.out.println("user에게 받은 파라미터로 select한후 한 내용을 Emp객체에 저장한 내용" + selectEmpList);
			}
			return selectEmpList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public static int selectEx05_Simple_Count(String deptName,String erpRank,Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(e.empno) "+
				"from emp e join dept d on e.deptno=d.deptno " + 
				"where d.deptname=? and " + 
				"e.erprank=? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,deptName);
			pstmt.setString(2,erpRank);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	public static List<SelectEmp> selectEx05_Simple(String deptName,String erpRank, Connection conn,int startRow,int size) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SelectEmp> selectEmpList = new ArrayList<>();
		String sql = "select e.empno,e.deptno,e.ename,e.erprank,e.email,e.tel,e.hiredate,d.deptname "+
				"from emp e join dept d on e.deptno=d.deptno " + 
				"where d.deptname=? and "+
				"e.erprank=? "+
				"order by e.empno desc limit ?,?";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,deptName);
			pstmt.setString(2,erpRank);
			pstmt.setInt(3,startRow);
			pstmt.setInt(4,size);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int empno =rs.getInt("e.empno");
				int deptno=rs.getInt("e.deptno");
				String ename =rs.getString("e.ename");
				String erprank=rs.getString("e.erprank");
				String email=rs.getString("e.email");
				String tel=rs.getString("e.tel");
				Date hiredate = toDate(rs.getTimestamp("e.hiredate"));
				String deptname = rs.getString("d.deptname");
				
				SelectEmp selectEmp = new SelectEmp(empno,deptno,ename,
						erprank,email,tel,hiredate,deptname);
				selectEmpList.add(selectEmp);
				System.out.println("user에게 받은 파라미터로 select한후 한 내용을 Emp객체에 저장한 내용" + selectEmpList);
			}
			return selectEmpList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public static int selectEx06_Simple_Count(String deptName,Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(e.empno) "+
				"from emp e join dept d on e.deptno=d.deptno " + 
				"where d.deptname=? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,deptName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	public static List<SelectEmp> selectEx06_Simple(String deptName, Connection conn,int startRow,int size) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SelectEmp> selectEmpList = new ArrayList<>();
		String sql = "select e.empno,e.deptno,e.ename,e.erprank,e.email,e.tel,e.hiredate,d.deptname "+
				"from emp e join dept d on e.deptno=d.deptno " + 
				"where d.deptname=? "+
				"order by e.empno desc limit ?,?";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,deptName);
			pstmt.setInt(2,startRow);
			pstmt.setInt(3,size);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int empno =rs.getInt("e.empno");
				int deptno=rs.getInt("e.deptno");
				String ename =rs.getString("e.ename");
				String erprank=rs.getString("e.erprank");
				String email=rs.getString("e.email");
				String tel=rs.getString("e.tel");
				Date hiredate = toDate(rs.getTimestamp("e.hiredate"));
				String deptname = rs.getString("d.deptname");
				
				SelectEmp selectEmp = new SelectEmp(empno,deptno,ename,
						erprank,email,tel,hiredate,deptname);
				selectEmpList.add(selectEmp);
				System.out.println("user에게 받은 파라미터로 select한후 한 내용을 Emp객체에 저장한 내용" + selectEmpList);
			}
			return selectEmpList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public static int selectEx07_Simple_Count(String erpRank,Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(e.empno) "+
				"from emp e join dept d on e.deptno=d.deptno " + 
				"where e.erprank=? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,erpRank);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	public static List<SelectEmp> selectEx07_Simple(String erpRank, Connection conn,int startRow,int size) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SelectEmp> selectEmpList = new ArrayList<>();
		String sql = "select e.empno,e.deptno,e.ename,e.erprank,e.email,e.tel,e.hiredate,d.deptname "+
				"from emp e join dept d on e.deptno=d.deptno " + 
				"where e.erprank=? "+
				"order by e.empno desc limit ?,?";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,erpRank);
			pstmt.setInt(2,startRow);
			pstmt.setInt(3,size);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int empno =rs.getInt("e.empno");
				int deptno=rs.getInt("e.deptno");
				String ename =rs.getString("e.ename");
				String erprank=rs.getString("e.erprank");
				String email=rs.getString("e.email");
				String tel=rs.getString("e.tel");
				Date hiredate = toDate(rs.getTimestamp("e.hiredate"));
				String deptname = rs.getString("d.deptname");
				
				SelectEmp selectEmp = new SelectEmp(empno,deptno,ename,
						erprank,email,tel,hiredate,deptname);
				selectEmpList.add(selectEmp);
				System.out.println("user에게 받은 파라미터로 select한후 한 내용을 Emp객체에 저장한 내용" + selectEmpList);
			}
			return selectEmpList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
	public static int selectEx08_Simple_Count(Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(e.empno) "+
				"from emp e join dept d on e.deptno=d.deptno ";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	public static List<SelectEmp> selectEx08_Simple(Connection conn,int startRow,int size) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SelectEmp> selectEmpList = new ArrayList<>();
		String sql = "select e.empno,e.deptno,e.ename,e.erprank,e.email,e.tel,e.hiredate,d.deptname "+
				"from emp e join dept d on e.deptno=d.deptno  "+
				"order by e.empno desc limit ?,?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,startRow);
			pstmt.setInt(2,size);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int empno =rs.getInt("e.empno");
				int deptno=rs.getInt("e.deptno");
				String ename =rs.getString("e.ename");
				String erprank=rs.getString("e.erprank");
				String email=rs.getString("e.email");
				String tel=rs.getString("e.tel");
				Date hiredate = toDate(rs.getTimestamp("e.hiredate"));
				String deptname = rs.getString("d.deptname");
				
				SelectEmp selectEmp = new SelectEmp(empno,deptno,ename,
						erprank,email,tel,hiredate,deptname);
				selectEmpList.add(selectEmp);
				System.out.println("user에게 받은 파라미터로 select한후 한 내용을 Emp객체에 저장한 내용" + selectEmpList);
			}
			return selectEmpList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	
				
	//상세조회
	public static SelectEmp selectDetail(int eno, Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SelectEmp selectEmp = null;
		String sql = "select e.empno,e.deptno,e.ename,e.erprank,e.email,e.tel,e.hiredate,d.deptname,e.sal,e.grade,e.emppwd "+
					 "from emp e join dept d on e.deptno=d.deptno " + 
					 "where e.empno=? ";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,eno);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int empno =rs.getInt("e.empno");
				int deptno=rs.getInt("e.deptno");
				String ename =rs.getString("e.ename");
				String erprank=rs.getString("e.erprank");
				String email=rs.getString("e.email");
				String tel=rs.getString("e.tel");
				Date hiredate = toDate(rs.getTimestamp("e.hiredate"));
				String deptname = rs.getString("d.deptname");
				int sal=rs.getInt("e.sal");
				int grade=rs.getInt("e.grade");
				String emppwd=rs.getString("e.emppwd");
				
				selectEmp = new SelectEmp(empno,deptno,ename,
						erprank,email,tel,hiredate,deptname,sal,grade,emppwd);
				System.out.println("user에게 받은 파라미터로 select한후 한 내용을 Emp객체에 저장한 내용" + selectEmp);
			}
			return selectEmp;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
	}
	
	
	
	
	//수정
	public static void modify(int empno,int deptno,String ename,
			String erprank,String email,String tel,String hiredate,
			int sal,int grade,String emppwd, Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		int rs = 0;
		String sql = "update emp " + 
				"set deptno=?, ename=?, erprank=?, email=?, tel=?, hiredate=?, "
				+ "sal=?, grade=?, emppwd=? "
				+ "where empno=? ";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,deptno);
			pstmt.setString(2,ename);
			pstmt.setString(3,erprank);
			pstmt.setString(4,email);
			pstmt.setString(5,tel);
			pstmt.setString(6, hiredate);
			pstmt.setInt(7,sal);
			pstmt.setInt(8,grade);
			pstmt.setString(9,emppwd);
			pstmt.setInt(10,empno);
			rs = pstmt.executeUpdate();
			System.out.println("업데이트수"+rs);
		}finally {
			JdbcUtil.close(pstmt);
		}
		
	}
	
	
	//삭제
	public static void delete(int empno, Connection conn) throws SQLException{
		PreparedStatement pstmt = null;
		int rs = 0;
		String sql = "delete from emp " + 
				"where empno=? ";
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,empno);
			rs = pstmt.executeUpdate();
			System.out.println("업데이트수"+rs);
		}finally {
			JdbcUtil.close(pstmt);
		}
		
	}
	
	
		private static Date toDate(Timestamp ts) {
			Date date = null;
			if( ts != null ) {
			 date = new Date(ts.getTime());
			}
			return date;
			
			// 변수=(조건)?조건true일경우:false경우
			//date=(ts==null)? null:new Date(ts.getTime());
			//return new Date(timestamp.getTime());
		}
		
		
		
		
		
		
}//selectDAO

