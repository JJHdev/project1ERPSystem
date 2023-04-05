package comment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import comment.model.Comment;
import jdbc.JdbcUtil;
import notice.model.Notice;

public class CommentDAO {
	//메서드
	//파라미터 
	//int startRow : 시작행인덱스번호, 가장 첫 번쨰는 행은 0부터 시작
	//int size : 1페이지당 출력게시물
	public List<Comment> selectComment(Connection conn, int no) throws SQLException {
		
		PreparedStatement pstmt=null;
		String sql= null;
		ResultSet rs = null;
		
		List<Comment> commentList= new ArrayList<>();
		
		sql ="select comment_no,notice_no,c_writer,c_content,c_regdate,c_moddate,isshow "+
			 "from comment "+
			 "where isshow='Y' and notice_no=? "+
			 "order by comment_no desc"; 
		
		try {
		pstmt =conn.prepareStatement(sql);
		pstmt.setInt(1,no);
		rs =pstmt.executeQuery();
		while(rs.next()) {//p635
			Comment comment= converComment(rs);
			commentList.add(comment);
			
		}//while문 끝
		
		return commentList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}//selectComment()끝
	
	private Comment converComment(ResultSet rs) throws SQLException {
		
		
		return  new Comment(rs.getInt("comment_no"),
				rs.getInt("notice_no"),rs.getInt("c_writer"),
				rs.getString("c_content"),
				toDate(rs.getTimestamp("c_regdate")),
				toDate(rs.getTimestamp("c_moddate")),
				rs.getString("isshow"));
	}//converComment()끝
	
	//Timestamp >>Date객체로 변환하기
		private Date toDate(Timestamp timestamp) {
			return new Date(timestamp.getTime());
		}//toDate()끝
	
		//파라미터 int cno :상세조회할 댓글번호
		//리턴유형 Comment : 글번호,작성자,내용
		public Comment selectByCno(Connection conn, int cno) throws SQLException {
			
			PreparedStatement pstmt= null;
			ResultSet rs =null;
			String sql=null;
			Comment comment= null;
			
			
			sql="select comment_no ,notice_no, c_writer ,c_content ,c_regdate ,c_moddate ,isshow "+
				"from comment " +
				"where isshow='Y' and comment_no=?";
			
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, cno);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					comment= converComment(rs);
				}
				return comment;
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
		}//상세조회 selectById()의 끝
		
		public void updateComment(Connection conn,int commentNo,String content) {
			String sql = null;
			PreparedStatement pstmt = null;
			
			 sql ="update comment " +
				  "set c_content=?,c_moddate=now() " +
				  "where isshow='Y' and comment_no =?";
			 
			 try {
				 pstmt = conn.prepareStatement(sql);
				 
				 pstmt.setString(1, content);
				 pstmt.setInt(2, commentNo);
				
				 int cnt= pstmt.executeUpdate();
				 
			 }catch(SQLException e ) {
				 e.printStackTrace();
				 
			 }
		
		
		
		}//updateComment()끝
		
		public int deleteComment(Connection conn, int cno) {
			PreparedStatement pstmt= null;
				int cnt=0;
				
				String sql ="delete from comment " +
						 	"where comment_no =?";
				
				try {
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1,cno);
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

		public Comment insertComment(Connection conn,Comment comment) {
			
			ResultSet rs =null;
			PreparedStatement pstmt = null;//인서트용
			Statement stmt= null;//셀렉트용
			String sql= null;
			
			sql="insert into comment(notice_no, c_writer, c_content, c_regdate, c_moddate, isshow) "+
				"values(?,?,?,now(),now(),'Y') "; 
			
			try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,comment.getNotice_no());
			pstmt.setInt(2,comment.getC_writer());
			pstmt.setString(3,comment.getC_content());
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				sql="select last_insert_id() from comment";
				stmt=conn.createStatement();
				rs=pstmt.executeQuery(sql);
				if(rs.next()) {
					int newNum=rs.getInt(1);//int로하고 오토박싱도 가능햐~
					return new Comment(newNum,comment.getNotice_no(),
							comment.getC_writer(),comment.getC_content(),comment.getC_regdate(),comment.getC_moddate(),"Y");
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
		
		/*private Timestamp toTimestamp(Date date) {
			return new Timestamp(date.getTime());
		}*/
		
	
}
