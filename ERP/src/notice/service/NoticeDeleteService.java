package notice.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import notice.dao.NoticeDAO;
import notice.exception.NoticeContentNotFoundException;

public class NoticeDeleteService {
	//필드
	NoticeDAO noticeDAO = new NoticeDAO();
	
	
	public int deleteNotice(int no){
		Connection conn = null;
		int cnt = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//auto커밋false설정
			
			cnt = noticeDAO.deleteNotice(conn,no);//article_content테이블에서 삭제
			if(cnt==0) {
				throw new NoticeContentNotFoundException();
			}
			if(cnt==1) {
				conn.commit();//커밋
			}
			return cnt;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
		}finally {
			JdbcUtil.close(conn);
		}
		return cnt;
		
	}
}
