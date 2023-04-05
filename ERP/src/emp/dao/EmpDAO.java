package emp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import emp.model.Emp;
import emp.model.MypageDbEmp;
import jdbc.JdbcUtil;
import mypage.deletemodel.MypageEmp;


public class EmpDAO {
	
	public MypageDbEmp MypageselectByEmpno(int no ,Connection conn) {
		//1.드라이버로드->2.conn얻기->3.객체준비->4.실행->5.자원해제
		//3.객체준비
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select empno, ename, emppwd, deptno, erprank, email, tel, hiredate ,sal, grade "+
					 "from erpdb.emp  " + 
					 "where empno=? ";
		MypageDbEmp mypageDbEmp = null; //user가 입력한 id를 사용하는 기존member정보를 저장하기위한 변수
		//4.실행
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int empno =rs.getInt("empno");
				String ename =rs.getString("ename");
				String emppwd=rs.getString("emppwd");
				int deptno=rs.getInt("deptno");
				String level=rs.getString("erprank");
				String email=rs.getString("email");
				String tel=rs.getString("tel");
				Date hiredate = toDate(rs.getTimestamp("hiredate"));
				int sal =rs.getInt("sal");
				int grade=rs.getInt("grade");
				mypageDbEmp= new MypageDbEmp(empno, ename, emppwd, deptno, level, email, tel, hiredate ,sal, grade);
				return mypageDbEmp;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {//5.자원해제
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return mypageDbEmp;
	}// selectById()끝
	public Emp selectByEmpno(int no ,Connection conn) {
		//1.드라이버로드->2.conn얻기->3.객체준비->4.실행->5.자원해제
		//3.객체준비
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select empno, ename, emppwd, deptno, erprank, email, tel, hiredate ,sal, grade "+
					 "from erpdb.emp  " + 
					 "where empno=? ";
		Emp emp = null; //user가 입력한 id를 사용하는 기존member정보를 저장하기위한 변수
		//4.실행
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int empno =rs.getInt("empno");
				String ename =rs.getString("ename");
				String emppwd=rs.getString("emppwd");
				int deptno=rs.getInt("deptno");
				String erprank=rs.getString("erprank");
				String email=rs.getString("email");
				String tel=rs.getString("tel");
				Date hiredate = toDate(rs.getTimestamp("hiredate"));
				int sal =rs.getInt("sal");
				int grade=rs.getInt("grade");
				emp= new Emp(empno, ename, emppwd, deptno, erprank, email, tel, hiredate ,sal, grade);
				return emp;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {//5.자원해제
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return emp;
	}// selectById()끝
	//Timestamp타입을 Date타입으로 형변환-p593 38라인
	private Date toDate(Timestamp ts) {
		Date date = null;
		if( ts != null ) {
		 date = new Date(ts.getTime());
		}
		return date;
	}
	public void insert(Emp emp, Connection conn) throws SQLException {
		String sql= "insert into emp(empno,ename,emppwd,deptno,erprank,email,tel,hiredate,sal,grade) "+
				    "values(?,?,?,?,?,?,?,?,?,?) " ;
		PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, emp.getEmpno());
		pstmt.setString(2, emp.getEname());
		pstmt.setString(3, emp.getEmppwd());
		pstmt.setInt(4, emp.getDeptno());
		pstmt.setString(5, emp.getErprank());
		pstmt.setString(6, emp.getEmail());
		pstmt.setString(7, emp.getTel());
		pstmt.setTimestamp(8,new Timestamp(emp.getHiredate().getTime()));
		pstmt.setInt(9, emp.getSal());
		pstmt.setInt(10, emp.getGrade());
		int result = pstmt.executeUpdate();
	}
	public boolean idDuplicate(String userid, Connection conn) {
		String sql = "select empno, ename, emppwd, grade "+
					 "from erpdb.emp  " + 
					 "where empno=? ";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			//4.쿼리실행 select~
				stmt = conn.prepareStatement(sql);
				stmt.setString(1,userid);
				rs = stmt.executeQuery();
				if(rs.next()) {
					result = false;
				}else {
					result = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {	
				//5.자원반납-> JdbcUtil.close()
				JdbcUtil.close(rs);
				JdbcUtil.close(stmt);
			}
		return result;
	}
	public void updateTel(Connection conn, MypageDbEmp mypageDbEmp) {
		PreparedStatement stmt = null;
		String sql = "update erpdb.emp "+ 
					 "set tel=? "+ 
					 "where empno=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, mypageDbEmp.getTel());
			stmt.setInt(2,mypageDbEmp.getEmpno());
			int result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateSal(Connection conn, MypageDbEmp mypageDbEmp) {
		PreparedStatement stmt = null;
		String sql = "update erpdb.emp "+ 
					 "set sal=? "+ 
					 "where empno=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mypageDbEmp.getSal());
			stmt.setInt(2,mypageDbEmp.getEmpno());
			
			int result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateLevel(Connection conn, MypageDbEmp mypageDbEmp) {
		PreparedStatement stmt = null;
		String sql = "update erpdb.emp "+ 
					 "set erprank=? "+ 
					 "where empno=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, mypageDbEmp.getLevel());
			stmt.setInt(2,mypageDbEmp.getEmpno());
			int result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateEmail(Connection conn, MypageDbEmp mypageDbEmp) {
		PreparedStatement stmt = null;
		String sql = "update erpdb.emp "+ 
					 "set email=? "+ 
					 "where empno=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, mypageDbEmp.getEmail());
			stmt.setInt(2,mypageDbEmp.getEmpno());
			int result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateGrade(Connection conn, MypageDbEmp mypageDbEmp) {
		PreparedStatement stmt = null;
		String sql = "update erpdb.emp "+ 
					 "set grade=? "+ 
					 "where empno=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mypageDbEmp.getGrade());
			stmt.setInt(2,mypageDbEmp.getEmpno());
			int result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateDno(Connection conn, MypageDbEmp mypageDbEmp) {
		PreparedStatement stmt = null;
		String sql = "update erpdb.emp "+ 
					 "set deptno=? "+ 
					 "where empno=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, mypageDbEmp.getDeptno());
			stmt.setInt(2,mypageDbEmp.getEmpno());
			int result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateEmppwd(Connection conn, MypageDbEmp mypageDbEmp) {
		PreparedStatement stmt = null;
		String sql = "update erpdb.emp "+ 
					 "set emppwd=? "+ 
					 "where empno=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, mypageDbEmp.getEmppwd());
			stmt.setInt(2,mypageDbEmp.getEmpno());
			int result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public int selectCount(Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		String sql = "select count(empno) "+
					 "from erpdb.emp";
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	public List<MypageEmp> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement stmt = null;
		String sql = 
				"select empno,ename,deptno, " + 
				"       erprank,hiredate " + 
				"from  erpdb.emp " +
				"order by empno asc limit ?,?";
		ResultSet rs = null;
		List<MypageEmp> mypageDeleteList = new ArrayList<MypageEmp>();
		try {//limit 시작행인덱번호,1페이지당출력게시물수
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, startRow);
			stmt.setInt(2, size);
			rs = stmt.executeQuery();
			while(rs.next()) {//p647 26
				MypageEmp article = converMypageDelete(rs);
				System.out.println("DAO의 article="+article);/*확인용-콘솔출력=> 삭제하세요*/
				mypageDeleteList.add(article);
			}
			return mypageDeleteList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	private MypageEmp converMypageDelete(ResultSet rs) throws SQLException {
		return new MypageEmp(rs.getInt("empno"), 
							rs.getString("ename"), 
							rs.getString("deptno"), 
							rs.getString("erprank"), 
							toDate(rs.getTimestamp("hiredate"))
							);
	}
	public int deleteEmpNo(Connection conn, int[] no) {
		PreparedStatement stmt = null;
		String sql = "delete from erpdb.emp " + 
					 "where empno=?";
		int cnt = 0;//삭제(delte)된 행 수를 저당할 변수
		try {
			stmt = conn.prepareStatement(sql);
			for(int i=0; i<no.length ; i++) {
				System.out.println("i"+i);
				stmt.setInt(1, no[i]);
				cnt =stmt.executeUpdate();
			}
			return cnt;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(stmt);
		}
		return cnt;
	}
	public boolean searchEno(String usereno, Connection conn) {
		System.out.println("MyMemberDAO의 idDuplicate()호출 userid="+usereno);
		String sql = "select empno, ename, emppwd, grade "+
					 "from erpdb.emp  " + 
					 "where empno=? ";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			//4.쿼리실행 select~
				stmt = conn.prepareStatement(sql);
				stmt.setString(1,usereno);
				rs = stmt.executeQuery();
				if(rs.next()) {
					result = false;
				}else {
					result = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {	
				//5.자원반납-> JdbcUtil.close()
				JdbcUtil.close(rs);
				JdbcUtil.close(stmt);
			}
		return result;
	}
	public List<MypageEmp> selecteno(Connection conn, int startRow, int size,int eno) throws SQLException {
		PreparedStatement stmt = null;
		String sql = 
				"select empno,ename,deptno, erprank,hiredate "+
				"from  erpdb.emp "+ 
				"where empno = ? "+
				"order by empno asc limit ?,?";
		ResultSet rs = null;
		List<MypageEmp> mypageDeleteList = new ArrayList<MypageEmp>();
		try {//limit 시작행인덱번호,1페이지당출력게시물수
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, eno);
			stmt.setInt(2, startRow);
			stmt.setInt(3, size);
			rs = stmt.executeQuery();
			while(rs.next()) {//p647 26
				MypageEmp article = converMypageDelete(rs);
				mypageDeleteList.add(article);
			}
			return mypageDeleteList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	public int deleteMessageEmpNo(Connection conn, int[] delemessageno) {
		for(int i=0; i<delemessageno.length ; i++) {
		}
		PreparedStatement stmt = null;
		String sql = "delete from erpdb.message " + 
					 "where messageno=?";
		int cnt = 0;//삭제(delte)된 행 수를 저당할 변수
		try {
			stmt = conn.prepareStatement(sql);
			for(int i=0; i<delemessageno.length ; i++) {
				System.out.println("i"+i);
				stmt.setInt(1, delemessageno[i]);
				cnt =stmt.executeUpdate();
			}
			return cnt;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(stmt);
		}
		return cnt;
	}
	public MypageDbEmp MypageselectByDeptno(int deptNo, Connection conn) {
		//3.객체준비
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select deptno,deptname "+
					 "from erpdb.dept  " + 
					 "where deptno=? ";
		MypageDbEmp mypageDbDeptno = null; //user가 입력한 id를 사용하는 기존member정보를 저장하기위한 변수
		//4.실행
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,deptNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int deptno=rs.getInt("deptno");
				String deptname=rs.getString("deptname");
				mypageDbDeptno= new MypageDbEmp(deptno, deptname);
				return mypageDbDeptno;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {//5.자원해제
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return mypageDbDeptno;
	}
	public List<MypageDbEmp> MypageAllSelect(Connection conn) {
		//3.객체준비
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select empno,ename "+
					 "from erpdb.emp";
		List<MypageDbEmp> mypageDbEnoList = new ArrayList<>(); //user가 입력한 id를 사용하는 기존member정보를 저장하기위한 변수
		//4.실행
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int empno=rs.getInt("empno");
				String ename=rs.getString("ename");
				mypageDbEnoList.add(new MypageDbEmp(ename, empno));
			}
			return mypageDbEnoList;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {//5.자원해제
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return mypageDbEnoList;
	}
}