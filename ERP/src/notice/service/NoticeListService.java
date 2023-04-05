package notice.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import notice.dao.NoticeDAO;
import notice.model.Notice;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class NoticeListService {

	//필드
	private NoticeDAO noticeDAO = new NoticeDAO();
	
	//private int size = 3;
	//생성자
	
	//메서드
	//목록조회 + 페이징처리 관련~
	//int pageNo = 보고싶은페이지(요청페이지)
	//int size = 1페이지당 출력게시물 수
	//리턴타입 ArticlePage : 목록 + 페이징처리 관련 내용
	public Notice getNoticePage(int pageNo,int rowSize) {
		Connection conn=null;
		try {
		
			 conn =ConnectionProvider.getConnection();
			
			//int startRow : 시작행인덱스번호, 가장 첫 번쨰는 행은 0부터 시작
			//int size : 1페이지당 출력게시물
			//페이징처리와 관련하여 Limit 시작행인덱스번호,1페이지당 출력게시물수를 제시해야한다.
			int total = noticeDAO.selectCount(conn);
			
			List<Notice> noticeList = noticeDAO.select(conn,(pageNo-1)*rowSize,rowSize);
			
			
			return new Notice(total,pageNo,rowSize,noticeList);
			
			}catch(SQLException e) {
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}//getArticlePage()끝
	
	
}
