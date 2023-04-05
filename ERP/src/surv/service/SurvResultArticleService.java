package surv.service;

import java.sql.Connection;
import java.sql.SQLException;

import emp.model.Emp;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import surv.dao.SurvArticleContentDAO;
import surv.dao.SurvArticleDAO;
import surv.dao.SurvResultDAO;
import surv.model.SurvArticle;
import surv.model.SurvArticleContent;
import surv.model.SurvResult;

public class SurvResultArticleService {
	//필드
	private SurvArticleDAO articleDAO = new SurvArticleDAO();
	private SurvResultDAO survResultDAO = new SurvResultDAO();
	
	public SurvResultData getResult(int no,boolean increaseResult) throws SurvArticleContentNotFoundException {
		Connection conn = null;
		try {
			conn= ConnectionProvider.getConnection();
			//특정글번호 응답종료시  조회수 증가관련-p659 27라인
			
			if(increaseResult) {
				articleDAO.increaseResult(conn, no);
			}
			
			//SurvResult result = survResultDAO.insert(conn, result);
			if(survResultDAO==null) { //article_content테이블에서 특정글번호에 해당하는 레코드존재x
				throw new SurvArticleContentNotFoundException();
				
			}
			return null;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}

	public Integer write(SurvResult survRe) {
		Connection conn = null;
		try {
			conn= ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			//특정글번호 응답종료시  조회수 증가관련-p659 27라인
			SurvResult savedResult = survResultDAO.insert(conn, survRe);
			if(savedResult==null) {
				throw new RuntimeException("article테이블에 insert 실패");
			}
			
			conn.commit();//커밋-p639 38라인
			
			return savedResult.getAnswer_no();//p639 40라인
		
			
		}catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);//p639 41라인
			
		}catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}finally{//p639 44라인
			JdbcUtil.close(conn);
		}
		return null;
	}//write()끝

}	
