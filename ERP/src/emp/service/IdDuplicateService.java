package emp.service;

import java.sql.Connection;
import java.sql.SQLException;

import emp.dao.EmpDAO;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class IdDuplicateService {
	
	public boolean idDuplicate(String userid) {
		EmpDAO empDAO = new EmpDAO();
		Connection conn = null;
		boolean idduplicate = false;
		try {
			conn = ConnectionProvider.getConnection();
			idduplicate =empDAO.idDuplicate(userid,conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn);
		}
		return idduplicate;
	}
}
