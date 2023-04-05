package offday.Service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import offday.dao.OffDayDAO;
import offday.model.OffDay;
import offday.model.OffDayUpdate;

//업데이트를 위한 서비스 이다.
public class OffDayUpdateService {

	
	private OffDayDAO offdayDAO= new OffDayDAO();
	
	public void update(OffDayUpdate OffDayUpdate) throws ResvNotFoundException, PermissionDeniedException {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//auto커밋 false설정
			System.out.println("OffDayUpdateService");
			
			OffDay offday = offdayDAO.upselectOffDay(conn, OffDayUpdate.getOffdayno());
			
			if(offday==null) {
				throw new ResvNotFoundException();
			}
			offdayDAO.offdayupdate(conn,OffDayUpdate.getStartday(), OffDayUpdate.getEndday(), OffDayUpdate.getOffnotice(), OffDayUpdate.getOffdayno());
			//여기서 .update는 예약수정을 위한 것
			if(!canUpdate(OffDayUpdate.getEmpno(),offday)){
				throw new PermissionDeniedException();
			}
			
			
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (PermissionDeniedException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
	}
	


	public OffDay getoffno(int offdayno) throws OffdayNONotFoundException {
			Connection conn = null;
			
			try {
				conn = ConnectionProvider.getConnection();
				OffDay offday = offdayDAO.upselectOffDay(conn, offdayno);
				
				if(offday==null) {
					throw new OffdayNONotFoundException();
				}
				
			return null;
			
			}catch(SQLException e) {
				System.out.println("getoffno SQL문제발생 Exception발생");
				throw new RuntimeException(e);
			}
		}
		
	
////int updateEmpno 가 뭔지 모름 일단 복사해씀
	private boolean canUpdate(int updateEmpno,OffDay offday ) {
		int empno = offday.getEmpno();
		
		return empno==updateEmpno;
	}
	
}
