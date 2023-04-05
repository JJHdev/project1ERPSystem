package hpdesk.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import hpdesk.model.HDComm;
import hpdesk.model.HPDesk;
import hpdesk.model.HPDeskWriter;
import hpdesk.service.HPDeskData;
import jdbc.JdbcUtil;

//이 클래스는 hpdesk테이블과 hdcomm테이블과 관련된 DB작업실행 클래스이다
public class HPDeskDAO {

	// 목록조회
	public List<HPDesk> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement stmt = null;
		String sql = "select hdno,hdtitle,ename,empno,hddate,isshow,hdcontent,hdcheck " + "from hpdesk "
				+ "where isshow='Y' " + "order by hdno desc limit ?,?";
		// limit 시작행인덱스번호, 1페이지당 출력게시물수
		ResultSet rs = null;
		List<HPDesk> hpdeskList = new ArrayList<HPDesk>();

		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, startRow);
			stmt.setInt(2, size);
			rs = stmt.executeQuery();
			while (rs.next()) {
				HPDesk hpdesk = converHPDesk(rs);
				System.out.println("DAO의 hpdesk=" + hpdesk);
				hpdeskList.add(hpdesk);
			}
			for (Iterator iterator = hpdeskList.iterator(); iterator.hasNext();) {
				HPDesk hpdesk = (HPDesk) iterator.next();
				System.out.println("dao의 hpdesk=" + hpdesk);
			}
			System.out.println("dao의 hpdeskList.size()=" + hpdeskList.size());

			return hpdeskList;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	// HPDesk객체로 변환하기 Integer hdno, String hdtitle, HPDeskWriter hpdeskWriter, Date
	private HPDesk converHPDesk(ResultSet rs) throws SQLException {
		return new HPDesk(rs.getInt("hdno"), rs.getString("hdtitle"),
				new HPDeskWriter(rs.getString("ename"), rs.getInt("empno")), toDate(rs.getTimestamp("hddate")),
				rs.getString("isshow"), rs.getString("hdcontent"), rs.getInt("hdcheck"));
	}

	// Timestamp->Date객체로 변환
	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}

	// 전체게시글 수
	public int selectCount(Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		String sql = "select count(hdno) " + "from hpdesk " + "where isshow='Y'";
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	// 내용상세조회 int no(상세조회할 글번호)
	public HPDesk selectByIdHPDesk(Connection conn, int no) throws SQLException {
		PreparedStatement stmt = null;
		String sql = "select hdno,ename,empno,hdtitle,hdcontent,hdcheck " + 
				"from hpdesk " + 
				"where isshow='Y' " + 
				"and hdno=?";
		ResultSet rs = null;
		HPDesk hpdesk = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);// 상세조회할 글 번호
			rs = stmt.executeQuery();
			if (rs.next()) {
				int hdno = rs.getInt("hdno");
				String ename = rs.getString("ename");
				int empno=rs.getInt("empno");
				String hdtitle = rs.getString("hdtitle");
				String hdcontent = rs.getString("hdcontent");
				int hdcheck= rs.getInt("hdcheck");

				HPDeskWriter writer =new HPDeskWriter();
				writer.setEname(ename);
				writer.setEmpno(empno);

				hpdesk = new HPDesk();
				hpdesk.setHdno(hdno);
				hpdesk.setHpdeskWriter(writer);
				hpdesk.setHdtitle(hdtitle);
				hpdesk.setHdcontent(hdcontent);
				hpdesk.setHdcheck(hdcheck);

			}
			return hpdesk;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	// 댓글조회
	public HDComm selectByIdHDComm(Connection conn, int no) throws SQLException {
		PreparedStatement stmt = null;
		String sql = "select hdno,hdcontent,hdcomment " + 
							"from hdcomm " + 
							"where isshow='Y' " + 
							"and hdno=?";
		ResultSet rs = null;
		HDComm hdcomm = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);// 상세조회할 글 번호
			rs = stmt.executeQuery();
			if (rs.next()) {
				int hdno=rs.getInt("hdno");
				String hdcontent=rs.getString("hdcontent");
				String hdcomment=rs.getString("hdcomment");				
				
				hdcomm=new HDComm();
				hdcomm.setHdno(hdno);
				hdcomm.setHdcontent(hdcontent);
				hdcomm.setHdcomment(hdcomment);				
			}
			return hdcomm;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	// 문의사항글쓰기
	public HPDesk hpdeskinsert(Connection conn, HPDesk hpdesk) {
		PreparedStatement pstmt = null;
		String sql = "insert into hpdesk(hdtitle,ename,hddate,isshow,hdcontent,hdcheck,empno) "
				+ "values (?,?,?,'Y',?,1,?)";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hpdesk.getHdtitle()); // 게시글제목
			pstmt.setString(2, hpdesk.getHpdeskWriter().getEname());// 게시글작성자
			pstmt.setTimestamp(3, toTimestamp(hpdesk.getHddate()));// 게시글작성일
			pstmt.setString(4, hpdesk.getHdcontent()); // 게시글내용
			pstmt.setInt(5, hpdesk.getHpdeskWriter().getEmpno());// 사원번호
			int cnt = pstmt.executeUpdate();

			if (cnt > 0) { // hpdesk테이블에 insert성공
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select last_insert_id() from hpdesk");
				if (rs.next()) {
					Integer newHdNo = rs.getInt(1);
					return new HPDesk(newHdNo, hpdesk.getHdtitle(), hpdesk.getHpdeskWriter(), hpdesk.getHddate(), "Y",
							hpdesk.getHdcontent(), 1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
		return null;

	}

	private Timestamp toTimestamp(java.util.Date hddate) {
		return new Timestamp(hddate.getTime());
	}

	// 댓글쓰기 '관리자만 입력가능합니다'
	public HDComm hdcomminsert(Connection conn, HDComm hdcomm) {
		PreparedStatement pstmt = null;
		
		String sql = null;
		if(hdcomm.getHdcomment() != null)
			sql = "insert into hdcomm(hdno,hdcontent,hdcomment) " + "values(?,?,?)"; 
		else
			sql = "insert into hdcomm(hdno,hdcontent) " + "values(?,?)";
		Statement stmt=null;
		ResultSet rs=null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, hdcomm.getHdno());
			pstmt.setString(2, hdcomm.getHdcontent());
			if(hdcomm.getHdcomment() != null)
				pstmt.setString(3, hdcomm.getHdcomment());
			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select last_insert_id() from hdcomm");
			}
			if (rs.next()) {
					Integer newHdNo = rs.getInt(1);
					return new HDComm(newHdNo, hdcomm.getHdcontent(),"관리자만 등록 가능합니다.","Y");
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
		return null;

	}

	 //제목,내용 수정 -유저모드
	 //댓글,확인체크 수정 -관리자모드
	 //파라미터 int hdno : 글번호 String hdtitle:수정할 제목, String hdcontent: 수정할 내용, String hdcomment(hdcomm) : 수정할 댓글 int hdcheck(hpdesk):수정할 체크 
	 public void update(Connection conn, int hdcheck, String hdcomment, int hdno, String hdtitle, String hdcontent) {
		 PreparedStatement stmt = null; 
		 String sql = "update hpdesk d,hdcomm c " + 
				  		"set d.hdtitle=?, d.hdcontent=?,d.hdcheck=?, c.hdcomment=? " + 
				  		"where d.hdno=c.hdno and d.hdno=?";	  
		 try { 
			 stmt = conn.prepareStatement(sql); 							 
					 stmt.setString(1, hdtitle);
					 stmt.setString(2, hdcontent);
					 stmt.setInt(3, hdcheck);
					 stmt.setString(4, hdcomment);
					 stmt.setInt(5, hdno);
					 int cnt = stmt.executeUpdate();
					 } catch (SQLException e) {
					 e.printStackTrace(); 
					 } finally {
							JdbcUtil.close(stmt);
						}
		}

	 //문의사항글+댓글삭제 :파라미터 int no : 삭제할 글번호 리턴타입 int : 삭제된 행 수. 1이면삭제성공,0이면 삭제실패 
	 public int delete(Connection conn, int hdno) {
		 PreparedStatement stmt = null; 
		 String sql = "DELETE hpdesk, hdcomm " + 
					 		"FROM hpdesk " + 
					 		"INNER JOIN hdcomm ON hpdesk.hdno = hdcomm.hdno " + 
					 		"WHERE hpdesk.hdno=? "; 
		 
		 int cnt = 0;
		 try { 
			 stmt = conn.prepareStatement(sql); 
				 stmt.setInt(1, hdno); 
				 cnt =	 stmt.executeUpdate(); 
				 System.out.println("hpdesk 삭제된 행 수="+cnt);
				 return cnt; 
		 }catch(SQLException e) {
			 e.printStackTrace(); 
		 }finally { 
			 JdbcUtil.close(stmt); 
			 } return cnt; 
		}

}
