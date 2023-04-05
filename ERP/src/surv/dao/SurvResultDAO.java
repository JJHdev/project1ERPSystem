package surv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import surv.model.SurvArticle;
import surv.model.SurvResult;

public class SurvResultDAO {

	
	// 전체 응답 수-p646
		public int selectCount(Connection conn) throws SQLException {
			PreparedStatement stmt = null;
			String sql = "select count(answer_no) " +
						"from surv_answer" ;
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
		//전체 응답목록
		public List<SurvResult> select(Connection conn, int startRow, int size) throws SQLException {
			PreparedStatement stmt = null;
			String sql = "select answer_no,surv_no,empno,answer "+
						 "from surv_answer " + 
						 "order by surv_no desc limit ?,?";

			ResultSet rs = null;
			List<SurvResult> survResultList = new ArrayList<SurvResult>();
			try {// limit 시작행인덱번호,1페이지당출력게시물수
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, startRow);
				stmt.setInt(2, size);
				rs = stmt.executeQuery();
				while (rs.next()) {// p647 26
					SurvResult survResult = converResult(rs);
					survResultList.add(survResult);
				}

				return survResultList;
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(stmt);
			}
		}

		private SurvResult converResult(ResultSet rs) throws SQLException {
			return new SurvResult(rs.getInt("answer_no"),
									rs.getInt("surv_no"),
									rs.getInt("empno"),
									rs.getInt("answer"));
		}
		
			
			public SurvResult insert(Connection conn,SurvResult result){ 
				  PreparedStatement stmt = null; // insert용 
				  Statement stmt2 = null; //select용 
				  String sql = "insert into surv_answer(surv_no,empno,answer) " + 
				  			"values(?,?,?)"; 
				  ResultSet rs = null; 
				  try {
					  System.out.println("insert() article="+result);
					  stmt =  conn.prepareStatement(sql); 
					  stmt.setInt(1,result.getSurv_no());
				  stmt.setInt(2,result.getEmpno());
				  stmt.setInt(3,result.getAnswer());
				  int cnt = stmt.executeUpdate();
				  System.out.println("insert결과행수"+cnt);
			  
			 
				  if(cnt>0) { //입력이 되었다면 
				  stmt2 = conn.createStatement(); 
				  rs = stmt2.executeQuery("select last_insert_id() from surv_answer");
				  if(rs.next()) {//p635 34라인
					  Integer newNum = rs.getInt(1); 
					  return new SurvResult(newNum, result.getSurv_no(), result.getEmpno(),
							  result.getAnswer()); 
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
}
