package hpdesk.service;

import java.sql.Connection;
import java.sql.SQLException;

import hpdesk.dao.HPDeskDAO;
import hpdesk.model.HPDesk;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

//관리자모드 수정 서비스
public class ModifyAdminService {

private HPDeskDAO hpdeskDAO =new HPDeskDAO();

	//ModifyHPDeskRequest
	public void modifyHPDesk(ModifyHPDeskRequest modifyHPDeskReq) throws HPDeskNotFoundException {
		Connection conn=null;
		try {
			conn=ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			//특정글번호에 해당하는 변경전 db가져오기
			HPDesk hpdesk=hpdeskDAO.selectByIdHPDesk(conn, modifyHPDeskReq.getHdno());
			if(hpdesk==null) {
				throw new HPDeskNotFoundException();
			}
			hpdeskDAO.update(conn,modifyHPDeskReq.getHdcheck(),modifyHPDeskReq.getHdcomment(),modifyHPDeskReq.getHdno(),
					modifyHPDeskReq.getHdtitle(),modifyHPDeskReq.getHdcontent());

			conn.commit();			
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}  catch (PermissionDeniedException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally {
			JdbcUtil.close(conn);
		}
		
	}

}
