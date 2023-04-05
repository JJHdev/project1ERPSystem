package resv.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import resv.dao.ResvDAO;
import resv.model.Resv;


public class ResvCheckService {
	
	private ResvDAO resvDAO = new ResvDAO();
	
	public List<Resv> getResvList(int empno) {
		Connection conn = null;
		List<Resv> resvlist = new ArrayList<Resv>();
		try {
		conn = ConnectionProvider.getConnection();
		resvlist = resvDAO.selectByEmpno(conn, empno);
		} catch(SQLException e) {
			e.printStackTrace();
				}
		return resvlist;
	
	}
	
	public Resv getResv(int resvno) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			Resv resv = resvDAO.selectByResvNo(conn, resvno);
			
			if(resv==null) {
				throw new ResvNotFoundException();
			}
			
		return null;
		
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
