package resv.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import resv.dao.ResvDAO;
import resv.model.Resv;

public class ResvUpdateService {
	
	private ResvDAO resvDAO = new ResvDAO();
	
	public void update(ResvUpdateRequest resvUpReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//auto커밋 false설정
			
			Resv resv = resvDAO.selectByResvNo(conn, resvUpReq.getResvno());
			
			if(resv==null) {
				throw new ResvNotFoundException();
			}
			resvDAO.update(conn, resvUpReq.getResvdate(), resvUpReq.getRoomname(), resvUpReq.getResvmemo(), resvUpReq.getResvname(), resvUpReq.getResvtel(), resvUpReq.getResvemail(), resvUpReq.getResvno());
			
			if(!canUpdate(resvUpReq.getEmpno(),resv)){
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

	private boolean canUpdate(int updateEmpno, Resv resv) {
		int empno = resv.getEmpno();
		
		return empno==updateEmpno;
	}
	
	
}
