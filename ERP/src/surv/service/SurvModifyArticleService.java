package surv.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import surv.dao.SurvArticleContentDAO;
import surv.dao.SurvArticleDAO;
import surv.model.SurvArticle;

public class SurvModifyArticleService {

	//필드
	private SurvArticleDAO articleDAO = new SurvArticleDAO();
	private SurvArticleContentDAO contentDAO = new SurvArticleContentDAO();
	
	//생성자
	
	//메서드
	//수정처리
	//파라미터
	public void modfiy(SurvModifyRequest modReq) throws SurvArticleNotFoundException {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//auto커밋false설정
			
			//특정글번호에 해당하는 (변경전)db데이터가져오기-p667 23라인
			SurvArticle article = articleDAO.selectById(conn,modReq.getArticleNumber());
			if(article==null) {
				throw new SurvArticleNotFoundException();
			}
			
			//수정불가시 커미션거부예외처리로 넘긴다
			//파라미터:수정하려는 사용자id, 특정글번호에 해당하는 (변경전)db데이터
			//리턴유형:db데이터작성자id와 수정하려는사용자id가 동일하면 true리턴, 그렇지않으면 false리턴
			/*
			 * if(!canModify(modReq.getUserId(),article)) {//p667 28라인 throw new
			 * PermissionDeniedException(); }
			 */
			//article테이블의 수정처리-p668 31라인
			articleDAO.update(conn, modReq);
			
			//article_content테이블의 수정처리-p668 33라인
			contentDAO.update(conn, modReq);
			
			conn.commit();//커밋
		}catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}

	
}
