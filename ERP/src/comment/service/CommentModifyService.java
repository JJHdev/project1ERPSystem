package comment.service;

import java.sql.Connection;
import java.sql.SQLException;

import comment.dao.CommentDAO;
import comment.model.Comment;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import notice.exception.PermissionDeniedException;

public class CommentModifyService {
	//필드
	CommentDAO commentDAO = new CommentDAO();
	
	public Comment getComment(int cno) throws Exception{
		Connection conn =null;
	
	try {
		conn =ConnectionProvider.getConnection();
		
		
		Comment commentInfo = commentDAO.selectByCno(conn,cno);
		if(commentInfo==null) {
			throw new Exception();
		}
		
		return commentInfo;
	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
	
	
}//getNocie()끝
	
	
	public Comment commentModify(Comment comReq,int a) {
		Connection conn = null;
		try {
			conn= ConnectionProvider.getConnection();
			//auto커밋 false설정
			conn.setAutoCommit(false);
			//특정 글번호에 해당하는 (변경전) 데이터 가져오기
			Comment comment = commentDAO.selectByCno(conn,comReq.getComment_no());
			if(comment==null) {
				throw new RuntimeException();
			}
			if(!canModify(comReq.getC_writer(),comment)) {
				if(a==999) {
					canModify(comReq.getC_writer(),comment);
				}else
				throw new PermissionDeniedException();
			}
			
			commentDAO.updateComment(conn,comReq.getComment_no(),comReq.getC_content());
			//커밋
			conn.commit();
			
		}catch(SQLException e) {
				
				JdbcUtil.rollback(conn);
				throw new RuntimeException(e);
			}catch(PermissionDeniedException e) {
				
				JdbcUtil.rollback(conn);
				throw new RuntimeException();
				
			}finally {
				JdbcUtil.close(conn);
			}
			return null;
		}
	//String modifyingUserId : 수정하려는 사용자 id
	//Article article: 특정글번호에 해당하는 DB데이터 (변경전)
	public boolean canModify(int modifyingUserEmpno, Comment comment){
		Integer id = comment.getC_writer();
		
		//DB데이터에서 작성자id를 가져오고 modifyingUserid를 가져와서 같은지 확인한다
		//DB데이터작성자id.equals("수정하려는 id");
		return id.equals(modifyingUserEmpno);
	}
	
		
		
}