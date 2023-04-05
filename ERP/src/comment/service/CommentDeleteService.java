package comment.service;

import java.sql.Connection;
import java.sql.SQLException;

import comment.dao.CommentDAO;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import notice.exception.CommentContentNotFoundException;


public class CommentDeleteService {

	//필드
	CommentDAO commentDAO = new CommentDAO();
	
	
	public int deleteComment(int cno){
		Connection conn = null;
		int cnt = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//auto커밋false설정
			
			cnt = commentDAO.deleteComment(conn,cno);//article_content테이블에서 삭제
			if(cnt==0) {
				throw new CommentContentNotFoundException();
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
