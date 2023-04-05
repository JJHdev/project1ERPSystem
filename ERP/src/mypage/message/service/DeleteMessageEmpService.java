package mypage.message.service;

import java.sql.Connection;
import java.sql.SQLException;

import emp.dao.EmpDAO;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import mypage.Error.EmpNoSelectNotFoundException;


public class DeleteMessageEmpService {
	private EmpDAO empDAO = new EmpDAO();

	public int deleteMessageEmp(int[] delemessageno) {
		Connection conn = null;
		int cnt = 0;
		for(int i=0; i<delemessageno.length ; i++) {
		}
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//auto커밋false설정
			cnt = empDAO.deleteMessageEmpNo(conn,delemessageno);//article_content테이블에서 삭제
			
			if(cnt==0) {
				throw new EmpNoSelectNotFoundException();
			}
			conn.commit();//커밋
			return cnt;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
		}finally {
			JdbcUtil.close(conn);
		}
		return cnt;

	}

}
