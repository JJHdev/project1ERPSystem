package mypage.message.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import emp.model.MypageDbEmp;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import mypage.message.dao.MessageDAO;
import mypage.message.dto.MessageDTO;
import mypage.message.dto.MessagePage;

public class ListMessageService {

	private MessageDAO messageDAO = new MessageDAO();
	
	public MessagePage getMessagePage(int recveno, int pageNo,int size) {
			Connection conn =null;
		try {
			conn = ConnectionProvider.getConnection();
			int total = messageDAO.selectCount(conn,recveno);//전체게시물수
			List<MessageDTO> articleList = messageDAO.select(conn,recveno,(pageNo-1)*size,size);
			return new MessagePage(total,pageNo,size,articleList);//p651 21라인
		}catch(SQLException e) {
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
	public MessagePage getMessageSearchPage(int recveno, int pageNo,int size,int searchEno) {
		Connection conn =null;
		try {
			conn = ConnectionProvider.getConnection();
			int total = messageDAO.select2Count(conn,recveno,searchEno);//전체게시물수
			List<MessageDTO> articleList = messageDAO.selectEno(conn,recveno,(pageNo-1)*size,size,searchEno);
			return new MessagePage(total, pageNo, size, articleList,searchEno);//p651 21라인
		}catch(SQLException e) {
			throw new RuntimeException();
		}
	}

}
