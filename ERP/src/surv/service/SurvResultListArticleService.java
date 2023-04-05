package surv.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import surv.dao.SurvResultDAO;
import surv.model.SurvArticle;
import surv.model.SurvResult;

public class SurvResultListArticleService {

	private SurvResultDAO survResultDAO = new SurvResultDAO();
	public SurvResultPage getResultPage(int pageNo,int size) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			//페이징처리와 관련하여
			//limit 시작행인덱번호,1페이지당 출력게시물수를 제시
			/*int startRow:시작행인덱번호, 가장 첫 번째 행은 0부터 시작
			   int size:    1페이지당출력게시물*/
			int total = survResultDAO.selectCount(conn);//전체게시물수
		List<SurvResult> survResultList = survResultDAO.select(conn,(pageNo-1)*size,size);
			
			return new SurvResultPage(total, pageNo, 
					   size, survResultList);//p651 21라인
			
		}catch(SQLException e) {
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
}

