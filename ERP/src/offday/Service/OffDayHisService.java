package offday.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import offday.dao.OffDayDAO;
import offday.model.OffDay;


//ResvCheckService 와 같은역활이다.
public class OffDayHisService {
				
	private OffDayDAO offdayDAO = new OffDayDAO();
	
			//////////////////////His셀렉문/////////////////////////
		// **	public OffDay 였다가 바꿧다. his셀렉 오류나면 이거 확인
		public OffDay selectOffDayHis(int empno) {
				OffDayDAO offdayDAO = new OffDayDAO();
				Connection conn =null;
			try {
				 conn = ConnectionProvider.getConnection();
				
				List<OffDay> offdayhis  = offdayDAO.selectOffDayHis(conn, empno);
				
				System.out.println("offdayhis="+offdayhis);
				return new OffDay(offdayhis); 
			}catch(SQLException e) {
			
				throw new RuntimeException();
			}finally {
				JdbcUtil.close(conn);
			}
			
			}
			/*
			 * //ResvCheckService 의 Resv getResv 역활이다. public OffDay updateOffDayHis(int
			 * offdayno) { OffDayDAO offdayDAO = new OffDayDAO();
			 * 
			 * try { Connection conn = ConnectionProvider.getConnection();
			 * 
			 * List<OffDay> offdayhis = offdayDAO.selectOffDayHis(conn, offdayno);
			 * 
			 * System.out.println("offdayhis="+offdayhis); return new OffDay(offdayhis);
			 * }catch(SQLException e) {
			 * 
			 * throw new RuntimeException(); }
			 * 
			 * }
			 */
			
		//ResvCheckService 의 Resv getResv 역활이다. 
		//연차번호로 대상 연차기록 찾기  //throws OffdayNONotFoundException 원본에는없었다
		public OffDay getoffno(int offdayno) throws OffdayNONotFoundException {
			Connection conn = null;
			
			try {
				conn = ConnectionProvider.getConnection();
				OffDay offday = offdayDAO.upselectOffDay(conn, offdayno);
				
				if(offday==null) {
					throw new OffdayNONotFoundException();
				}
				
			return null;
			
			}catch(SQLException e) {
				System.out.println("getoffno SQL문제발생 Exception발생");
				throw new RuntimeException(e);
			}
		}
		
			
			
	
	
}
