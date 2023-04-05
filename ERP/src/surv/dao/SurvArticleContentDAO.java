package surv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import emp.model.Emp;

import java.util.Date;

import jdbc.JdbcUtil;
import surv.model.SurvArticle;
import surv.model.SurvArticleContent;
import surv.service.SurvModifyRequest;

public class SurvArticleContentDAO {
	//필드
	
		//생성자
		
		//메서드
	//내용조회
		/*파라미터 int no:상세조회할 글 번호 
		 *리턴유형 ArticleContent: 글번호,내용    */
	public SurvArticleContent selectById(Connection conn,int no) throws SQLException{
		PreparedStatement stmt = null;
		String sql = "select surv_no,empno,surv_title,surv_desc,surv_resdate,surv_isshow " + 
					 "from  survqust " + 
					 "where surv_no=?";
		ResultSet rs = null;
		SurvArticleContent content = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);//상세조회할 글 번호
			rs = stmt.executeQuery();
			if(rs.next()) {
			  content = new SurvArticleContent(	
					 new SurvArticle(rs.getInt("surv_no")),
					 new Emp(rs.getInt("empno")) ,
					 new SurvArticle(rs.getString("surv_title")),
					  	rs.getString("surv_desc"),
						rs.getDate("surv_resdate"),
						rs.getString("surv_isshow"))
						;//마지막정일
					 
			}
			return content;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	//글삭제-delete
	/*파라미터  int no : 삭제할 글번호
	 *리턴타입  int : 삭제(delete)된 행 수. 1이면 삭제성공,0이면 삭제실패*/
	public int deleteArticle(Connection conn, int no) {
		PreparedStatement stmt = null;
		String sql = "delete from survqust " + 
					 "where surv_no=?";
		
		int cnt = 0;//삭제(delte)된 행 수를 저당할 변수
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);
			cnt = stmt.executeUpdate();
			System.out.println("survqust 삭제(delete)된 행 수="+cnt);
			return cnt;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(stmt);
		}
		return cnt;
	}
	

	//내용입력
	//파라미터 ArticleContent content: article테이블에 입력된 마지막 글번호,입력한 내용
	//리턴타입 ArticleContent content: article테이블에 입력된 마지막 글번호,입력한 내용
	public SurvArticleContent insert(Connection conn,SurvArticleContent content) {
		PreparedStatement stmt = null;
		String sql = "insert into erpdb.survqust(surv_no,empno,surv_title,surv_desc,surv_resdate,surv_isshow) " + 
					"value(?,?,?,?,?,'Y')";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,content.getNum());
			stmt.setInt(2,content.getSurvWriter_no());
			stmt.setString(3,content.getSurv_tit());
			stmt.setString(4,content.getSurv_content());
			stmt.setTimestamp(5,toTimestamp(content.getSurv_resdate()));
			System.out.println(content.getNum()+content.getSurv_desc());
			int cnt = stmt.executeUpdate();
			System.out.println("articlecontent테이블에 insert결과행수"+cnt);
			if(cnt>0) {  //articlecontent테이블에 insert성공
				return content;
			}else {
				return null;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			JdbcUtil.close(stmt);
		}
	}
	private Timestamp toTimestamp(Date date){
		return new Timestamp(date.getTime());
		
	}
	
	//글내용수정
		/*파라미터  
		  int articleNumber : 글번호
		  String content : 수정할 내용*/
	public void update(Connection conn,SurvModifyRequest modReq) {
		PreparedStatement stmt = null;
		String sql = "update survqust " + 
					 "set surv_title=?, surv_desc=?,surv_resdate=? " + 
					 "where  surv_no=?";
	
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,modReq.getTitle());
			stmt.setString(2, modReq.getContent());  
			stmt.setTimestamp(3, toTimestamp(modReq.getRes_date()));
			  stmt.setInt(4, modReq.getArticleNumber());
			int cnt = stmt.executeUpdate();
			System.out.println("article_content update된 행 수="+cnt);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
