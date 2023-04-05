package notice.service;

import java.sql.Connection;
import java.sql.SQLException;

import notice.model.Notice;
import notice.dao.NoticeDAO;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class NoticeReadService {
	//필드
		private NoticeDAO noticeDAO = new NoticeDAO();
		
		
		//메서드
		//파라미터 boolean increaseReadCount: true (이면 조회수 증가!)
		public Notice getNotice(int no, boolean increaseReadCount) throws Exception{
				Connection conn =null;
			
			try {
				conn =ConnectionProvider.getConnection();
				
				
				Notice noticeInfo = noticeDAO.selectByNo(conn, no);
				if(noticeInfo==null) {
					throw new Exception();
				}
				//특정글번호 상세조회시 조회수 증가관련 p659 27라인~
				if(increaseReadCount) {
					noticeDAO.increaseReadCount(conn, no);
				}
				return noticeInfo;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}finally {
				JdbcUtil.close(conn);
			}
			
			
		}//getNocie()끝
	}