package resv.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import resv.dao.ResvDAO;
import resv.model.Resv;

//예약입력
public class ResvService {
	
	private ResvDAO resvDAO = new ResvDAO();
	
	
	//파라미터 Resv reservation : empno,resvdate,roomname,resvmemo,resvname,resvtel,resvemail
	//리턴타입 
	public void resv(Resv newResv) {
		Connection conn = null;
		Resv resv = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Resv requestResv = new Resv(0,newResv.getEmpno(), newResv.getResvdate(),
					newResv.getRoomname(),newResv.getResvmemo(),newResv.getResvname(),
					newResv.getResvtel(),newResv.getResvemail());
			
			resvDAO.insert(requestResv, conn);
			
			conn.commit();
			
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
		
	}	
	
	
}
