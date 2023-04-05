package comment.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import comment.dao.CommentDAO;
import comment.model.Comment;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import notice.model.Notice;

public class CommentWriteService {
		
		//필드
		private CommentDAO commentDAO = new CommentDAO();
		
		//메서드
		public Integer write(Comment commentReq,int no) {
			Connection conn= null;
			
			try {
				conn=ConnectionProvider.getConnection();
				conn.setAutoCommit(false);
				
				Comment comment =toComment(commentReq,no);
				
				Comment savedComment=commentDAO.insertComment(conn,comment);//Article테이블에 인서트
				
				if(savedComment==null) {
					throw new RuntimeException("notice테이블에 insert실패");
				}

				conn.commit();
				return savedComment.getComment_no();
			} catch (SQLException e) {
				JdbcUtil.rollback(conn);
				throw new RuntimeException();
			} catch(RuntimeException e) {
				JdbcUtil.rollback(conn);
				throw e;
			}finally {
				JdbcUtil.close(conn);
			}
			
			
			
			
		}//write() 끝

		private Comment toComment(Comment commentReq,int no) {
			Date now=new Date();
			
			return new Comment(commentReq.getComment_no(),no,commentReq.getC_writer(),commentReq.getC_content(),
					now,now,"Y");
		}
		
		
		
	}