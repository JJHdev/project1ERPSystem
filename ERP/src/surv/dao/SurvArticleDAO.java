package surv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;
import surv.model.SurvArticle;
import surv.model.SurvArticleContent;
import surv.service.SurvModifyRequest;

//P647
//이 클래스는 survArticle, survArticle_content테이블과 관련된 DB작업실행 클래스이다
public class SurvArticleDAO {
	// 필드

	// 생성자

	// 메서드
	// 목록조회-p647 15라인
	/*
	 * 파라미터 int startRow:시작행인덱번호, 가장 첫 번째 행은 0부터 시작 int size: 1페이지당출력게시물
	 */
	public List<SurvArticle> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement stmt = null;
		String sql = "select surv_no,surv_tit,surv_writer,survReq_cnt,result_count, "
				+ "       surv_regdate,surv_resdate,surv_isshow " + "from  survarticle " + "where surv_isshow='Y' "
				+ "order by surv_no desc limit ?,?";

		ResultSet rs = null;
		List<SurvArticle> survArticleList = new ArrayList<SurvArticle>();
		System.out.println("list1");
		try {// limit 시작행인덱번호,1페이지당출력게시물수
			System.out.println("list2");
			System.out.println(startRow);
			System.out.println(size);
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, startRow);
			stmt.setInt(2, size);
			rs = stmt.executeQuery();
			System.out.println("list3");
			while (rs.next()) {// p647 26
				System.out.println("list4");
				SurvArticle survarticle = converArticle(rs);
				System.out.println("DAO의 article=" + survarticle);/* 확인용-콘솔출력=> 삭제하세요 */
				survArticleList.add(survarticle);
			}
			System.out.println("list5");

			return survArticleList;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	// Article객체로 변환하기-p647 36라인
	private SurvArticle converArticle(ResultSet rs) throws SQLException {

		return new SurvArticle(rs.getInt("surv_no"), rs.getString("surv_tit"), rs.getString("surv_writer"),rs.getInt("survReq_cnt"),
				rs.getInt("result_count"),
				 toDate(rs.getTimestamp("surv_regdate")),
				toDate(rs.getTimestamp("surv_resdate")), rs.getString("surv_isshow"));
	}

	// Timestamp->Date객체로 변환하기:p648 47라인
	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}

	// 전체 게시글 수-p646
	public int selectCount(Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		String sql = "select count(surv_no) " + "from survarticle " + "where surv_isshow='Y'";
		ResultSet rs = null;
		int cnt = 0;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt(1);
			}
			return cnt;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	// 상세조회-p655
	public SurvArticle selectById(Connection conn, int no) throws SQLException {
		PreparedStatement stmt = null;
		String sql = "select surv_no,surv_tit,surv_writer, " +
				"       survReq_cnt,surv_regdate,surv_resdate,surv_isshow "	+
				"from  survarticle "+
				"where surv_isshow='Y' and surv_no=?";
		ResultSet rs = null;
		SurvArticle article = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);// 상세조회할 글 번호
			rs = stmt.executeQuery();
			if (rs.next()) {
				article = new SurvArticle(rs.getInt("surv_no"),
						rs.getString("surv_tit"),
						rs.getString("surv_writer"),
						rs.getInt("survReq_cnt"),
						toDate(rs.getTimestamp("surv_regdate")),
						toDate(rs.getTimestamp("surv_resdate")), 
						rs.getString("surv_isshow"));
			}
			return article;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}// 상세조회 selectById()끝
	//조회수증가
		public int increaseReadCount(Connection conn, int no) {
			PreparedStatement stmt = null;
			String sql = "update survarticle " + 
						 "set result_count=result_count+1 " + 
						 "where surv_isshow='Y' and surv_no=?";
			
			int cnt = 0;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, no);
				cnt = stmt.executeUpdate();
				System.out.println("응답자 증가된 행 수="+cnt);
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return cnt;
		}
		//응답자 수 증가
		public int increaseResult(Connection conn, int no) {
			PreparedStatement stmt = null;
			String sql = "update survarticle " + 
						 "set survReq_cnt=survReq_cnt+1 " + 
						 "where surv_isshow='Y' and surv_no=?";
			
			int cnt = 0;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, no);
				cnt = stmt.executeUpdate();
				System.out.println("응답자 증가된 행 수="+cnt);
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return cnt;
		}
	// 글수정
	
	  public void update(Connection conn,SurvModifyRequest modReq) {
		  PreparedStatement stmt = null;
		  String sql = "update survarticle "+
				  		"set surv_tit=?, surv_resdate=? "+
				  		"where surv_isshow='Y' and surv_no=?";
		  try { 
			  stmt = conn.prepareStatement(sql); 
			  stmt.setString(1,modReq.getTitle());
			  stmt.setTimestamp(2, toTimestamp(modReq.getRes_date()));
			  stmt.setInt(3, modReq.getArticleNumber());
			  int cnt = stmt.executeUpdate();
		  	  System.out.println("article update된 행 수="+cnt); 
		  
		  }catch(SQLException e) {
			  e.printStackTrace(); 
		  } 
	 
	  }
	 
	 
		
		
		
	//글삭제-delete 파라미터 int no : 삭제할 글번호 리턴타입 int : 삭제(delete)된 행 수. 1이면 삭제성공,0이면
	 // 삭제실패
		public int deleteArticle(Connection conn, int no) {
			PreparedStatement stmt = null;
			String sql = "delete from survarticle " + 
						 "where surv_no=?";
			
			int cnt = 0;//삭제(delte)된 행 수를 저당할 변수
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, no);
				cnt = stmt.executeUpdate();
				System.out.println("article 삭제(delete)된 행 수="+cnt);
				return cnt;
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				JdbcUtil.close(stmt);
			}
			return cnt;
		}
	  //응답자수 증가
			public int increaseResultCount(Connection conn, int no) {
				PreparedStatement stmt = null;
				String sql = "update survarticle " + 
							 "set survReq_cnt=survReq_cnt+1 " + 
							 "where surv_isshow='Y' and surv_no=?";
				int cnt = 0;
				try {
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1, no);
					cnt = stmt.executeUpdate();
					System.out.println("응답증가된 행 수="+cnt);
				}catch(SQLException e) {
					e.printStackTrace();
				}finally {
					JdbcUtil.close(stmt);
				}
				return cnt;
			}
	  
	  //글쓰기
	  
		
		  public SurvArticle insert(Connection conn,SurvArticle article){ 
			  PreparedStatement stmt = null; // nsert용 
			  Statement stmt2 = null; //select용 
			  String sql = "insert into survarticle(surv_tit,surv_writer,survReq_cnt,result_count,surv_regdate,surv_resdate,surv_isshow) "  + 
					  		"values(?,?,0,0,?,?,'Y')"; 
			  ResultSet rs = null; 
			  try {
				  System.out.println("insert() article="+article);
				  stmt =  conn.prepareStatement(sql); 
				  stmt.setString(1,article.getSurv_tit());
			  stmt.setString(2,article.getSurv_writer());
			  stmt.setTimestamp(3,toTimestamp(article.getSurv_regdate())); //입력일
			  stmt.setTimestamp(4,toTimestamp(article.getSurv_resdate())); //마지막정일
			  int cnt = stmt.executeUpdate();
			  System.out.println("insert결과행수"+cnt);
		  
		 
			  if(cnt>0) { //입력이 되었다면 
			  stmt2 = conn.createStatement(); 
			  rs = stmt2.executeQuery("select last_insert_id() from survarticle");
			  if(rs.next()) {//p635 34라인
				  Integer newNum = rs.getInt(1); 
				  return new SurvArticle(newNum, article.getSurv_tit(), article.getSurv_writer(), 0,0,
						  article.getSurv_regdate(), article.getSurv_resdate(), "Y"); 
			  }
			  }
		}catch(SQLException e) {
			  e.printStackTrace();
		  }finally { 
			  JdbcUtil.close(rs); 
			
			  JdbcUtil.close(stmt); 
		  	}
			return null;
			 
		  }  
	//Date타입을 Timestamp타입으로 변환-p635 52라인
		private Timestamp toTimestamp(Date date){
			return new Timestamp(date.getTime());
			
		}
}

	 


