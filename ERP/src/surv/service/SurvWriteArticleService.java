package surv.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import surv.dao.SurvArticleContentDAO;
import surv.dao.SurvArticleDAO;
import surv.model.SurvArticle;
import surv.model.SurvArticleContent;
//글쓰기 기능을 제공하는 서비스클래스
public class SurvWriteArticleService {
	//필드
	private SurvArticleDAO survArticleDAO = new SurvArticleDAO();
	private SurvArticleContentDAO survArticleContentDAO = new SurvArticleContentDAO();
	//생성자
	
	//메서드
	//글입력
	/*파라미터  WriteRequest writeReq:Writer(로그인한유저id,로그인한유저명),입력제목,입력내용*/
	//리턴타입 Integer : article테이블에 입력된 글번호
	public Integer write(SurvArticle writeSurv,SurvArticleContent writeSurvCont) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//autocommit 설정해제
			SurvArticle article = toArticle(writeSurv);//p639 25라인
			//파라미터  Article article:Writer(로그인한유저id,로그인한유저명),입력제목,입력내용, 입력일,마지막수정일 추가
			//Article테이블에 insert 및 Article테이블에 마지막insert한 id가져오기
			SurvArticle savedArticle = survArticleDAO.insert(conn,article);//Article테이블에 insert//p639 26라인
			if(savedArticle==null) {
				throw new RuntimeException("article테이블에 insert 실패");
			}
			//p639 30라인
			
		
			SurvArticleContent content = toContent(savedArticle,writeSurvCont);
			//p639 33라인
			SurvArticleContent savedContent = survArticleContentDAO.insert(conn,content);//Article_content테이블에 insert
			if(savedContent==null) {
				throw new RuntimeException("articleContent테이블에 insert 실패");
			}
			
			conn.commit();//커밋-p639 38라인
			
			return savedArticle.getSurv_no();//p639 40라인
		
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

	/*
	 * private SurvArticleContent toContent(SurvArticle toArticle) {
	 * 
	 * return new SurvArticleContent(toArticle.getSurv_no(),
	 * toArticle.getSurv_tit(), toArticle.getSurv_content());
	 * 
	 * }
	 */

	private SurvArticleContent toContent(SurvArticle savedArticle,SurvArticleContent writeSurvCont) {
		System.out.println("toContent="+writeSurvCont);
		return new SurvArticleContent(savedArticle.getSurv_no(),
				savedArticle.getSurv_tit(), writeSurvCont.getSurv_content(),writeSurvCont.getSurvWriter_no(),writeSurvCont.getSurv_resdate());
	}

	//p639 52라인
	//글입력시 필요한 data를 세팅 :파라미터에 입력일,마지막수정일 추가
	/*파라미터  WriteRequest writeReq:Writer(로그인한유저id,로그인한유저명),입력제목,입력내용*/
	//리턴유형 Article:WriteRequest에 입력일,마지막수정일 추가
	private SurvArticle toArticle(SurvArticle writeSurv) {
		//입력일은 현재 날짜시간정보 설정
		System.out.println("toArticle() writeSurv="+writeSurv);
		//마지막수정일은 입력일과 동일하게 현재 날짜시간정보 설정
		return new SurvArticle(writeSurv.getSurv_tit(),
				writeSurv.getSurv_writer(),
				writeSurv.getSurv_regdate(),writeSurv.getSurv_resdate(),writeSurv.getSurv_content());
	}
	
}
