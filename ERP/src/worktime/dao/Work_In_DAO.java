package worktime.dao;
  
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

import worktime.model.Work_In;

  //이 클래스는 work_in,work_out테이블의
  //ArticleDAO 를 모방하여 만든다.
 
public class Work_In_DAO {
 
// 메서드 // 목록조회-p647 15라인
 
 //파라미터 int startRow:시작행인덱번호, 가장 첫 번째 행은 0부터 시작 int size: 1페이지당출력게시물
 //1.드라이버로드->2.conn얻기->3.객체준비->4.실행->5.자원해제
 //Date타입을 Timestamp타입으로 변환-p635 52라인
 
		//출근 관련 내용 인서트문
		public static int insertWorkinTime(Connection conn, String todaynoti, int empno) {
			PreparedStatement stmt = null;  //insert용 
			String sql = "insert into work_in(empno, work_in_time, work_in_notice) "+
						 "values(?,now(),?)";
			int cnt = 0;
			ResultSet rs = null;
			try {
				stmt = conn.prepareStatement(sql);
				
				stmt.setInt(1,empno); //
				stmt.setString(2,todaynoti);
				
				
			    cnt = stmt.executeUpdate();
				System.out.println("insert결과행수"+cnt);
		} catch(SQLException e){
			e.printStackTrace();
		}finally {
			
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			
		}
			return cnt;
	}
		
		

		//Timestamp->Date객체로 변환하기:p648 47라인
				private Date toDate(Timestamp timestamp) {
					return new Date(timestamp.getTime());
				}
				
		
		public Work_In selectWorkinTime(Connection conn, int empno) throws SQLException {
			PreparedStatement stmt = null;
			String sql = "select empno, work_in_time, work_in_notice " + 
					     "from work_in "+
					     "where empno=? "+
					     "order by work_in_time desc limit 0,1";
			
			ResultSet rs = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, empno);
				rs = stmt.executeQuery();
				while(rs.next()) {
					Work_In work_In =new Work_In( 
									rs.getInt("empno"), 
									toDate(rs.getTimestamp("work_in_time")), 
									rs.getString("work_in_notice")
									);
					return work_In;
				}
			}finally {
				JdbcUtil.close(conn);
				JdbcUtil.close(rs);
				JdbcUtil.close(stmt);
			}
			return null;
		}

}
