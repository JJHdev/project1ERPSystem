package hpdesk.service;

import java.sql.Connection;
import java.sql.SQLException;


import emp.model.Emp;
import hpdesk.dao.HPDeskDAO;
import hpdesk.model.HPDesk;
import hpdesk.model.HPDeskWriter;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

//수정작업 서비스
public class ModifyHPDeskService {
	
	private HPDeskDAO hpdeskDAO =new HPDeskDAO();

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
	
			//hpdesk&hdcomm 수정처리Connection conn, int hdcheck, String hdcomment, int hdno, String hdtitle, String hdcontent
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
