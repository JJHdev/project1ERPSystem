package surv.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import surv.dao.SurvArticleDAO;
import surv.model.SurvArticle;

//P650
public class SurvListArticleService {
	//필드
	private SurvArticleDAO articleDAO = new SurvArticleDAO();
	//private int size = 3; //p651 14라인
	//생성자
	
	//메서드
	
	//목록+페이징처리 관련 내용-p651 16라인 
	/*파라미터 
	   int pageNo:보고싶은페이지(요청페이지) 
	   int size: 1페이지당 출력게시물
	 *리턴타입  ArticlePage: 목록+페이징처리 관련 내용 */
	public SurvArticlePage getArticlePage(int pageNo,int size) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			System.out.println("연결1");
			//페이징처리와 관련하여
			//limit 시작행인덱번호,1페이지당 출력게시물수를 제시
			/*int startRow:시작행인덱번호, 가장 첫 번째 행은 0부터 시작
			   int size:    1페이지당출력게시물*/
			int total = articleDAO.selectCount(conn);//전체게시물수
			List<SurvArticle> articleList = articleDAO.select(conn,(pageNo-1)*size,size);
			
			return new SurvArticlePage(total, pageNo, 
					   size, articleList);//p651 21라인
			
		}catch(SQLException e) {
			
			throw new RuntimeException();
		}finally {
			
			JdbcUtil.close(conn);
		}
	}
}












