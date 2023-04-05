package notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import notice.model.Notice;
import jdbc.JdbcUtil;

//이 클래스는 article, article_content 테이블과 연관된 DB작업실행을 위한 클래스이다
// 요구사항계획서 참고하면서 만들어봅시당.
public class NoticeDAO {
	//메서드
	//파라미터 
	//int startRow : 시작행인덱스번호, 가장 첫 번쨰는 행은 0부터 시작
	//int size : 1페이지당 출력게시물
	public List<Notice> select(Connection conn, int startRow, int rowSize) throws SQLException {
		PreparedStatement pstmt=null;
		String sql= null;
		ResultSet rs = null;
		
		List<Notice> noticeList= new ArrayList<>();
		
		sql ="select notice_no, empno, writer, title, content, regdate, moddate, readcnt, isshow " +
			 "from notice " +
			 "where isshow='Y' "+	
			 "order by notice_no desc limit ?,? ";
		
		//모델 유형 4가지? 로직수행결과를 말한다
		//1.select ename from emp where empno= 7782; 컬럼이 1개, 결과 행 1개
		//2.select ename,empno from emp where empno= 7782; 컬럼이 2개 ,결과 행1개
		//3.select ename from emp; 컬럼 1개, 결과 행 여러 개
		//4.select ename,empno,job,hiredate from emp; 컬럼4개, 결과 행 여러개  
		
		try {
		pstmt =conn.prepareStatement(sql);
		pstmt.setInt(1,startRow);
		pstmt.setInt(2,rowSize);
		rs =pstmt.executeQuery();
		while(rs.next()) {//p635
			Notice notice= converNotice(rs);
			noticeList.add(notice);
			
		}//while문 끝
		
		return noticeList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}//select()끝
	

	private Notice converNotice(ResultSet rs) throws SQLException {
		
		
		return  new Notice(rs.getInt("notice_no"),
				rs.getInt("empno"),
				rs.getString("title"),rs.getString("content"),
				toDate(rs.getTimestamp("regdate")),
				toDate(rs.getTimestamp("moddate")),
				rs.getInt("readcnt"),
				rs.getString("isshow"));
	}//converNotice()끝
	
	
	//Timestamp >>Date객체로 변환하기
	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}//toDate()끝
	
	//상세조회
	//전체 게시글수를 조회하는 메서드이다
	public int selectCount(Connection conn) throws SQLException {
		PreparedStatement pstmt=null;
		String sql= null;
		ResultSet rs = null;
		
		sql="select count(notice_no) from notice where isshow='Y'";
		;
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
	
	
	//파라미터 int no :상세조회할 글번호
	//리턴유형 Aritcle : 글번호,작성자(Writer:writer_id,writer_name),제목,
	public Notice selectByNo(Connection conn, int no) throws SQLException {
		
		PreparedStatement pstmt= null;
		ResultSet rs =null;
		String sql=null;
		Notice notice= null;
		
		
		sql="select notice_no, empno, writer, title, content, regdate, moddate, readcnt, isshow "+
			"from notice " +
			"where isshow='Y' and notice_no=?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				notice= converNotice(rs);
			}
			return notice;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}//상세조회 selectById()의 끝
	
	
	//조회수증가
	public void increaseReadCount(Connection conn, int no){
		PreparedStatement pstmt= null;
		
		String sql ="update notice " +
				 "set readcnt= readcnt+1 " +
				 "where isshow='Y' and notice_no = ? ";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			int cnt=  pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
	
	
	
	}

	//글수정
	//파라미터 int articleNumber: 수정할 글번호
	//파라미터 String title: 수정할 제목
	public void updateNotice(Connection conn,int noticeNo,String title,String content) {
		System.out.println(noticeNo);
		System.out.println(title);
		System.out.println(content);
		String sql = null;
		PreparedStatement pstmt = null;
		
		 sql ="update notice " +
			  "set title=?,content=?,moddate=now() " +
			  "where isshow='Y' and notice_no =?";
		 
		 try {
			 pstmt = conn.prepareStatement(sql);
			 
			 pstmt.setString(1, title);
			 pstmt.setString(2, content);
			 pstmt.setInt(3, noticeNo);
			
			 int cnt= pstmt.executeUpdate();
			 
		 }catch(SQLException e ) {
			 e.printStackTrace();
			 
		 }
	
	
	
	}
	
	
	
	//글삭제 - delete버전
	//파라미터 int no :삭제할 글번호
	//리턴타입 int: 삭제된 행수
	public int deleteNotice(Connection conn, int no) {
	PreparedStatement pstmt= null;
		int cnt=0;
		
		String sql ="delete from notice " +
				 	"where notice_no =?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			//결과행수 리턴
			cnt=  pstmt.executeUpdate();
			return cnt;
		
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
		}
		return cnt;
	}
	
	//글삭제 - update버전
	//파라미터 int no = 삭제할 글번호
	//리턴타입 int: 삭제된 행수 1이면 삭제성공 0이면 삭제 실패
	public int deleteNotice111(Connection conn, int no) {
	PreparedStatement pstmt= null;
		
		String sql ="update article " +
				 	"set isshow='N' " +
				 	"where article_no = ? ";
		int cnt=0;
		
		try {
		
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			//결과행수 리턴
			cnt=  pstmt.executeUpdate();
			return cnt;
		
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			JdbcUtil.close(pstmt);
		}
		return cnt;
	}
	
	public Notice insertNotice(Connection conn,Notice notice) {
		
		ResultSet rs =null;
		PreparedStatement pstmt = null;//인서트용
		Statement stmt= null;//셀렉트용
		String sql= null;
		
		sql="insert into notice(empno, writer,title, content, regdate, moddate, readcnt, isshow) " +
			"values(?,?,?,?,?,?,0,'Y')"; 
		
		try {
		pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1,notice.getEmpno());
		pstmt.setInt(2,notice.getEmpno());
		pstmt.setString(3,notice.getTitle());
		pstmt.setString(4,notice.getContent());
		pstmt.setTimestamp(5,toTimestamp(notice.getRegdate()));//입력일
		pstmt.setTimestamp(6, toTimestamp(notice.getModdate()));//마지막수정일
		int cnt = pstmt.executeUpdate();
		
		if(cnt>0) {
			sql="select last_insert_id() from notice";
			stmt=conn.createStatement();
			rs=pstmt.executeQuery(sql);
			if(rs.next()) {
				int newNum=rs.getInt(1);//int로하고 오토박싱도 가능햐~
				return new Notice(newNum,notice.getEmpno(),notice.getTitle(),
						notice.getContent(),notice.getRegdate(),notice.getModdate(),0,"Y");
			}
			
		}
		
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	return null;
	}//insert()끝
	
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
	
	
	}
	
	
	
	
