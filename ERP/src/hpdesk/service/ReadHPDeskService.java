package hpdesk.service;

import java.sql.Connection;
import java.sql.SQLException;

import hpdesk.dao.HPDeskDAO;
import hpdesk.model.HDComm;
import hpdesk.model.HPDesk;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import notice.exception.PermissionDeniedException;

//상세조회 담당 서비스
public class ReadHPDeskService {
	
	private HPDeskDAO hpdeskDAO =new HPDeskDAO();

	public HPDeskData getHPDesk(int hdno ) throws HPDeskNotFoundException {
		Connection conn = null;		
		try {
			conn= ConnectionProvider.getConnection();
			HPDesk hpdesk = hpdeskDAO.selectByIdHPDesk(conn, hdno);
			if(hpdesk==null) { 
				throw new HPDeskNotFoundException();
			}

			HDComm hdcomm=hpdeskDAO.selectByIdHDComm(conn, hdno);
			return new HPDeskData(hpdesk, hdcomm); 
			}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}

