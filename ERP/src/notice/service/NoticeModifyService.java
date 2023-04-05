package notice.service;

import java.sql.Connection;
import java.sql.SQLException;

import emp.model.Emp;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import notice.dao.NoticeDAO;
import notice.exception.NoticeNotFoundException;
import notice.exception.PermissionDeniedException;
import notice.model.Notice;

public class NoticeModifyService {
	
	NoticeDAO noticeDAO= new NoticeDAO();
	
	public Notice noticeModify(Notice modReq,int grade) {
		
		System.out.println("서비스로 온 modReq"+modReq);
		Connection conn = null;
		try {
			conn= ConnectionProvider.getConnection();
			//auto커밋 false설정
			conn.setAutoCommit(false);
			//특정 글번호에 해당하는 (변경전) 데이터 가져오기
			Notice notice = noticeDAO.selectByNo(conn,modReq.getNotice_no());
			if(notice==null) {
				throw new RuntimeException();
			}
			if(!canModify(modReq.getEmpno(),notice)) {
				if(grade==999) {
					canModify(modReq.getEmpno(),notice);
				}else {
				throw new PermissionDeniedException();
			}
		
			}	
				noticeDAO.updateNotice(conn,modReq.getNotice_no(),modReq.getTitle(),modReq.getContent());
				conn.commit();
		}catch(SQLException e) {
				
				JdbcUtil.rollback(conn);
				throw new RuntimeException(e);
			
		}catch(PermissionDeniedException e) {
				JdbcUtil.rollback(conn);
				throw new RuntimeException(e);
				
			}finally {
				JdbcUtil.close(conn);
			}
			return null;
		}
	//String modifyingUserId : 수정하려는 사용자 id
	//Article article: 특정글번호에 해당하는 DB데이터 (변경전)
	public boolean canModify(Integer modifyingUserEmpno, Notice notice){
		
		Integer id = notice.getEmpno();
		
		//DB데이터에서 작성자id를 가져오고 modifyingUserid를 가져와서 같은지 확인한다
		//DB데이터작성자id.equals("수정하려는 id");
		return id.equals(modifyingUserEmpno);
	}
	
		
		
}
