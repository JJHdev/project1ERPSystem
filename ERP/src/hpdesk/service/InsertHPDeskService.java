package hpdesk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import hpdesk.dao.HPDeskDAO;
import hpdesk.model.HDComm;
import hpdesk.model.HPDesk;
import hpdesk.model.HPDeskWriter;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

//이 클래스는 글쓰기 기능을 제공할 클래스
public class InsertHPDeskService {
	private HPDeskDAO hpdeskDAO=new HPDeskDAO();
	
	public Integer insertHPDesk(InsertHPDeskRequest insertHPDeskReq) {
		Connection conn=null;
		try {
			conn=ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//오토커밋설정해제
			
			HPDesk hpdesk=toHPDesk(insertHPDeskReq);
			
			HPDesk savedHPDesk=hpdeskDAO.hpdeskinsert(conn, hpdesk);//hpdesk테이블에 insert
			if(savedHPDesk == null) {
				throw new RuntimeException();
			}
			//String hdtitle, Integer hdno, String hdcontent, String hdcomment
			HDComm hdcomm=new HDComm(savedHPDesk.getHdtitle(),
					savedHPDesk.getHdno(),
					savedHPDesk.getHdcontent(),
					insertHPDeskReq.getHdcomment());
			
			HDComm savedHDComm=hpdeskDAO.hdcomminsert(conn, hdcomm); //hdcomm테이블에서 insert
			//hpdesk테이블에 insert및 hpdesk테이블 마지막 insert한 id가져오기
			if(savedHPDesk==null) {
				throw new RuntimeException();
			}		
			conn.commit(); 
			return savedHPDesk.getHdno();
		}catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally{
			JdbcUtil.close(conn);
		}
		
	}

	//문의사항글입력시 필요한 data를 세팅
	//Integer hdno, String hdtitle, HPDeskWriter hpdeskWriter, Date hddate, String isshow, String hdcontent, int hdcheck
	//HPDeskRequest에 작성일 추가
	private HPDesk toHPDesk(InsertHPDeskRequest insertHPDeskReq) {
		Date now= new Date();		
		return new HPDesk(null,insertHPDeskReq.getHdtitle(),insertHPDeskReq.getHpdeskWriter(),now,"Y",
				insertHPDeskReq.getHdcontent(),1);
	}

}
