package mypage.message.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import emp.model.Emp;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import mypage.message.dto.MessageContent;
import mypage.message.dto.MessageDTO;

public class MessageDAO {
	
	public int insertMessage(int sendEmpno,int receiveEmpno, String title,String message,Emp empName) {
		String sql = "insert into erpdb.message(sendeno, recveno, send_time , title, message, sendempname) "+
					"values (?,?,now(),?,?,?)";
		PreparedStatement stmt =null;
		int cnt = 0;
		
		try {
			Connection conn = ConnectionProvider.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, sendEmpno);
			stmt.setInt(2, receiveEmpno);
			stmt.setString(3, title);
			stmt.setString(4, message);
			stmt.setString(5, empName.getEname());
			cnt = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
			JdbcUtil.close(stmt);
		}
		return cnt;
	}
	
	public int deleteMessage(int recveno) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		String sql = "delete from erpdb.message "+
					 " where recveno =? ";
		int cnt = 0;
			try {
				Connection conn = ConnectionProvider.getConnection();
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, recveno);
				cnt = stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(stmt);
			}
		return cnt;
	}
	public int select2Count(Connection conn,int recveno, int searchEno) throws SQLException {
		PreparedStatement stmt = null;
		String sql ="select count(messageno) "+ 
					"from erpdb.message "+
					"where recveno=? and sendeno=? ";
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,recveno);
			stmt.setInt(2,searchEno);
			rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	
	public int selectCount(Connection conn,int recveno) throws SQLException {
		PreparedStatement stmt = null;
		String sql ="select count(messageno) "+ 
					"from erpdb.message "+
					"where recveno=? ";
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,recveno);
			rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	public List<MessageDTO> select(Connection conn,int recveno ,int startRow, int size) throws SQLException {
		PreparedStatement stmt = null;
		String sql = 
				"select messageno,sendeno,recveno,send_time,title,sendempname "+
				"from  erpdb.message "+
				"where recveno=? "+
				"order by messageno desc limit ?,? ";
		ResultSet rs = null;
		List<MessageDTO> messageList = new ArrayList<MessageDTO>();
		try {//limit 시작행인덱번호,1페이지당출력게시물수
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, recveno);
			stmt.setInt(2, startRow);
			stmt.setInt(3, size);
			rs = stmt.executeQuery();
			while(rs.next()) {//p647 26
				MessageDTO messageDTO = converMessage(rs);
				messageList.add(messageDTO);
			}
			return messageList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	public List<MessageDTO> selectEno(Connection conn,int recveno ,int startRow, int size,int searchEno) throws SQLException {
		PreparedStatement stmt = null;
		String sql = 
				"select messageno,sendeno,recveno,send_time,title,sendempname "+
				"from  erpdb.message "+
				"where recveno=? and sendeno=? "+
				"order by messageno desc limit ?,? ";
		ResultSet rs = null;
		List<MessageDTO> messageList = new ArrayList<MessageDTO>();
		try {//limit 시작행인덱번호,1페이지당출력게시물수
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, recveno);
			stmt.setInt(2, searchEno);
			stmt.setInt(3, startRow);
			stmt.setInt(4, size);
			rs = stmt.executeQuery();
			while(rs.next()) {//p647 26
				MessageDTO messageDTO = converMessage(rs);
				messageList.add(messageDTO);
			}
			return messageList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	private MessageDTO converMessage(ResultSet rs) throws SQLException {
		return new MessageDTO(
								rs.getInt("messageno"), 
								rs.getInt("sendeno"), 
								rs.getInt("recveno"), 
								toDate(rs.getTimestamp("send_time")),
								rs.getString("title"),
								rs.getString("sendempname")
								);
	}
	//Date타입을 Timestamp타입으로 변환-p635 52라인
		private Timestamp toTimestamp(Date date){
			return new Timestamp(date.getTime());
		}
	//Timestamp->Date객체로 변환하기:p648 47라인
	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}
	public MessageDTO messageselectById(Connection conn, int no) throws SQLException {
		PreparedStatement stmt = null;
		String sql = "select messageno,sendeno,recveno, " + 
					 "       send_time,title,sendempname " + 
					 "from  erpdb.message " + 
					 "where messageno=? ";
		
		ResultSet rs = null;
		MessageDTO messageDTO= null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);//상세조회할 글 번호
			rs = stmt.executeQuery();
			if(rs.next()) {
				messageDTO = converMessage(rs);
			}
			return messageDTO;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	public MessageContent messageContentselectById(Connection conn, int no) throws SQLException {
			PreparedStatement stmt = null;
			String sql = "select messageno,message " + 
						 "from  erpdb.message " + 
						 "where messageno=?";
			ResultSet rs = null;
			MessageContent messageContent = null;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, no);//상세조회할 글 번호
				rs = stmt.executeQuery();
				if(rs.next()) {
					messageContent = new MessageContent(	
							rs.getInt("messageno"),
							rs.getString("message")
						  	);
				}
				return messageContent;
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(stmt);
			}
	}
}
