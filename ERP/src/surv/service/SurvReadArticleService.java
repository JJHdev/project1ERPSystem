package surv.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import surv.dao.SurvArticleContentDAO;
import surv.dao.SurvArticleDAO;
import surv.model.SurvArticle;
import surv.model.SurvArticleContent;

public class SurvReadArticleService {

	private SurvArticleDAO survarticleDAO = new SurvArticleDAO();
	private SurvArticleContentDAO survcontentDAO = new SurvArticleContentDAO();

	public SurvArticleData getArticle(int no,boolean increseReadCount) throws SurvArticleNotFoundException, SurvArticleContentNotFoundException {
		Connection conn = null;
		
		try {
			conn= ConnectionProvider.getConnection();
			
			SurvArticle article = survarticleDAO.selectById(conn, no);
			if(article==null) { //article테이블에서 특정글번호에 해당하는 레코드존재x
				throw new SurvArticleNotFoundException();
			}
			
			SurvArticleContent survContent = survcontentDAO.selectById(conn, no);
			if(survContent==null) { //article_content테이블에서 특정글번호에 해당하는 레코드존재x
				throw new SurvArticleContentNotFoundException();
				
			}
			//특정글번호 응답종료시  조회수 증가관련-p659 27라인
			if(increseReadCount) {
				survarticleDAO.increaseReadCount(conn, no);
			}
			return new SurvArticleData(article, survContent);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
			
		}
		
	}

	
}


