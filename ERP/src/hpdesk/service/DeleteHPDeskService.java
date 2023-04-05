package hpdesk.service;

import java.sql.Connection;
import java.sql.SQLException;

import hpdesk.dao.HPDeskDAO;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

//삭제 서비스
public class DeleteHPDeskService {
	
	private HPDeskDAO hpdeskDAO=new HPDeskDAO();

	//삭제 cnt=1이면 삭제성공 0이면 실패
	public int delete(int hdno) {
		Connection conn = null;
		int cnt = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//auto커밋false설정
			cnt = hpdeskDAO.delete(conn,hdno);//hdcomm테이블에서 삭제			
			if(cnt==0) {
				throw new HDCommNotFoundException();
			}

			conn.commit();
			return cnt;
		} catch (SQLException | HDCommNotFoundException e) {
			JdbcUtil.rollback(conn);
		}finally {
			JdbcUtil.close(conn);
		}
		return cnt;
	}

}
