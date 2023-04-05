package offday.Service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import offday.dao.OffDayDAO;

public class DeleteDayOffService {

	private OffDayDAO offdayDAO = new OffDayDAO();

	// 삭제(delete용)
	/*
	 * 파라미터 int no : 삭제할 글번호 리턴타입 int : 삭제(delete)된 행 수. 1이면 삭제성공,0이면 삭제실패
	 */
	public int deleteDayOff(int offdayno) throws dayoffdeletNotFoundException {
		Connection conn = null;
		int cnt = 0;

		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);// auto커밋false설정
			cnt=offdayDAO.deleteDayOff(conn, offdayno);
			System.out.println("서비스 1=="+cnt);
			if (cnt == 0) {
				throw new dayoffdeletNotFoundException();
			}

			/*
			 * if(cnt == 1) { cnt = offdayDAO.deleteDayOff(conn, offdayno);// article테이블에서
			 * 삭제 }   //한개 삭제목적이라  이건 주석처리한다.
			 */
			System.out.println("서비스 22=="+cnt);
			conn.commit();// 커밋
			return cnt;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
		} finally {
			JdbcUtil.close(conn);
		}
		return cnt;

	}

	/*
	 * //삭제(update용) 파라미터 int no : 삭제할 글번호 리턴타입 int : 삭제(update)된 행 수. 1이면 삭제성공,0이면
	 * 삭제실패 public void updateOffNoti(String offnotice,int offdayno) { Connection
	 * conn = null; int cnt = 0;
	 * 
	 * try { conn = ConnectionProvider.getConnection(); cnt =
	 * OffDayDAO.updateOffNoti(conn,offnotice, offdayno); return cnt; } catch
	 * (SQLException e) { e.printStackTrace(); }finally { JdbcUtil.close(conn); }
	 * return cnt;
	 * 
	 * }
	 */

}
