package surv.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import surv.dao.SurvArticleContentDAO;
import surv.dao.SurvArticleDAO;

//삭제처리관련 Service클래스
public class SurvDeleteArticleService {

	//필드
	private SurvArticleDAO articleDAO = new SurvArticleDAO();
	private SurvArticleContentDAO articleContentDAO = new SurvArticleContentDAO();
	
	//생성자
	
	//메서드
	//삭제(delete용)
	/*파라미터  int no : 삭제할 글번호
	 *리턴타입  int : 삭제(delete)된 행 수. 1이면 삭제성공,0이면 삭제실패*/
	public int deleteArticle(int no) throws SurvArticleContentNotFoundException {
		Connection conn = null;
		int cnt = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//auto커밋false설정
			cnt = articleContentDAO.deleteArticle(conn,no);//article_content테이블에서 삭제
			
			if(cnt==0) {
				throw new SurvArticleContentNotFoundException();
			}
			
			if(cnt==1) {
				cnt = articleDAO.deleteArticle(conn,no);//article테이블에서 삭제
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




