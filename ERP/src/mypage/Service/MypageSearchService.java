package mypage.Service;

import java.sql.Connection;
import java.sql.SQLException;

import emp.dao.EmpDAO;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class MypageSearchService {
	public boolean mypageenoSearch(String usereno){
		EmpDAO empDAO = new EmpDAO();
		Connection conn =null;;
		boolean mypageSearch = false;
		try {
			conn = ConnectionProvider.getConnection();
			mypageSearch =empDAO.searchEno(usereno,conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		return mypageSearch;
	}
}
