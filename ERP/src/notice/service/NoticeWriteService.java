package notice.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;


import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import notice.dao.NoticeDAO;
import notice.model.Notice;

//글쓰기 수행로직을 위한 클래스이다 이곳에서 DAO를 보내고 다시 받아 핸들러에 리턴할 예정~~
//리턴타입 Integer : article테이블에 입력된 글번호
public class NoticeWriteService {
	
	//필드
	private NoticeDAO noticeDAO = new NoticeDAO();
	
	//메서드
	public Integer write(Notice noticeReq) {
		Connection conn= null;
		
		try {
			conn=ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Notice notice =toNotice(noticeReq);
			
			Notice savedNotice=noticeDAO.insertNotice(conn,notice);//Article테이블에 인서트
			
			if(savedNotice==null) {
				throw new RuntimeException("notice테이블에 insert실패");
			}

			conn.commit();
			return savedNotice.getNotice_no();
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

	private Notice toNotice(Notice noticeReq) {
		Date now=new Date();
		
		return new Notice(noticeReq.getEmpno(),noticeReq.getTitle(),noticeReq.getContent(),
				now,now,0,"Y");
	}
	
	
	
}
