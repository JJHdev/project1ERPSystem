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

import worktime.model.Work_Out;

  //이 클래스는 work_in,work_out테이블의
  //ArticleDAO 를 모방하여 만든다.
 
public class Work_Out_DAO {
 
// 메서드 // 목록조회-p647 15라인
 
 //파라미터 int startRow:시작행인덱번호, 가장 첫 번째 행은 0부터 시작 int size: 1페이지당출력게시물
 //1.드라이버로드->2.conn얻기->3.객체준비->4.실행->5.자원해제
 //Date타입을 Timestamp타입으로 변환-p635 52라인
 
		//퇴근 관련 내용 인서트문
				public int insertWorkOutTime(Connection conn, String todayoutnoti, int empno) {
					PreparedStatement stmt = null;  //insert용 
					String sql = "insert into work_out(empno, work_out_time, work_out_notice) "+
								 "values(?,now(),?)";
					int cnt = 0;
					ResultSet rs = null;
					try {
						stmt = conn.prepareStatement(sql);
						
						stmt.setInt(1,empno);
						stmt.setString(2,todayoutnoti);
						
					    cnt = stmt.executeUpdate();
						System.out.println("퇴근insert결과행수"+cnt);
						System.out.println("퇴근insert//todayoutnoti"+todayoutnoti);
						System.out.println("퇴근insert결과empno"+empno);
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
				
		////퇴근 관련 셀렉트문
				public Work_Out selectWorkOutTime(Connection conn, int empno) throws SQLException {
					PreparedStatement stmt = null;
					String sql = "select a.empno AS empno, a.work_out_time AS work_out_time, a.work_out_notice AS work_out_notice, b.work_in_time AS work_in_time,b.work_in_notice AS work_in_notice " + 
								 "from work_out a, work_in b  " + 
								 "where a.empno=b.empno " + 
								 "and a.empno=? "+
								 "order by work_out_time desc limit 0,1";
					Work_Out work_out =null;
					System.out.println("1");
					ResultSet rs = null;
					try {
						System.out.println("2");
						stmt = conn.prepareStatement(sql);
						stmt.setInt(1, empno);
						rs = stmt.executeQuery();
						System.out.println("3");
						System.out.println("DAO selectWorkOutTime");
					while(rs.next()) {
						System.out.println("4");
						work_out =new Work_Out( 
											rs.getInt("empno"), 
											toDate(rs.getTimestamp("work_out_time")), 
											rs.getString("work_out_notice"),
											toDate(rs.getTimestamp("work_in_time")),
											rs.getString("work_in_notice")
									);
						System.out.println("5");
							System.out.println("work_out3"+work_out);
							return work_out;
						}
					}finally {
						JdbcUtil.close(conn);
						JdbcUtil.close(rs);
						JdbcUtil.close(stmt);
					}
					return work_out;
				}
}