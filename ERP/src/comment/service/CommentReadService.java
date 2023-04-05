package comment.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import comment.dao.CommentDAO;
import comment.model.Comment;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import notice.exception.NoticeInfoNotFoundException;
import notice.model.Notice;

public class CommentReadService {
	//필드
	private CommentDAO commentDAO = new CommentDAO();
	
	
	//메서드
	//파라미터 boolean increaseReadCount: true (이면 조회수 증가!)
	public Comment getComment(int no){
			Connection conn =null;
		
		try {
			conn =ConnectionProvider.getConnection();
			
			List<Comment> commentInfo =commentDAO.selectComment(conn, no);
			if(commentInfo==null) {
				throw new NoticeInfoNotFoundException();
			}
			//특정글번호 상세조회시 조회수 증가관련 p659 27라인~
			return new Comment(commentInfo);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (NoticeInfoNotFoundException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
		}
		return null;
		
	}//getNocie()끝
	
	
}