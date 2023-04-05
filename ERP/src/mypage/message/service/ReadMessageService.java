package mypage.message.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import mypage.Error.MessageContentNotFoundException;
import mypage.Error.MessageNotFoundException;
import mypage.message.dao.MessageDAO;
import mypage.message.dto.MessageContent;
import mypage.message.dto.MessageDTO;
import mypage.message.dto.MessageData;

public class ReadMessageService {

	private MessageDAO messageDAO = new MessageDAO();
	
	public MessageData getMessage(int no) {
			Connection conn = null;
		try {
			conn= ConnectionProvider.getConnection();
			MessageDTO messageDTO = messageDAO.messageselectById(conn, no);
			if(messageDTO==null) { //article테이블에서 특정글번호에 해당하는 레코드존재x
				throw new MessageNotFoundException();
			}
			MessageContent messagecontent = messageDAO.messageContentselectById(conn, no);
			if(messagecontent==null) { //article_content테이블에서 특정글번호에 해당하는 레코드존재x
				throw new MessageContentNotFoundException();
			}
			return new MessageData(messageDTO, messagecontent);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
