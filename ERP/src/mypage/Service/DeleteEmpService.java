package mypage.Service;

import java.sql.Connection;
import java.sql.SQLException;

import emp.dao.EmpDAO;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import mypage.Error.EmpNoSelectNotFoundException;

public class DeleteEmpService {

	private EmpDAO empDAO = new EmpDAO();

	public int deleteEmp(int[] no) {
		Connection conn = null;
		int cnt = 0;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//auto커밋false설정
			cnt = empDAO.deleteEmpNo(conn,no);//article_content테이블에서 삭제
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
