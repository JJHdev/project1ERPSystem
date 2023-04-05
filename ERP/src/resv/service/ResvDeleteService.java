package resv.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import resv.dao.ResvDAO;


public class ResvDeleteService {

	private ResvDAO resvDAO = new ResvDAO();

	public int deleteResv(int resvno) throws ResvNotFoundException {
		Connection conn = null;
		int cnt=0;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			cnt = resvDAO.deleteResv(conn, resvno);
			
			if(cnt==0) {//삭제할 값 없을때
				throw new ResvNotFoundException();
			}
			
			if(cnt==1) {//예약삭제
				cnt = resvDAO.deleteResv(conn, resvno);
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
		}finally {
			JdbcUtil.close(conn);
		}
		
		return cnt;
		
	}
}
