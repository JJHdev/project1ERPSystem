package hpdesk.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import hpdesk.dao.HPDeskDAO;
import hpdesk.model.HPDesk;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

//게시글 목록을 제공하는 서비스 클래스
public class ListHPDeskService {
	private HPDeskDAO hpdeskDAO = new HPDeskDAO();
	
	//파라미터 int pageNo(요청페이지), int size(1페이지당 출력할 게시물)
	//리턴 타입 HPDeskPage: 페이징처리+목록관련 내용
	
	public HPDeskPage getHPDeskPage(int pageNo,int size){
		Connection conn=null;
		try {
		conn = ConnectionProvider.getConnection();
		//페이징처리와 관련하여
		//limit: 시작행인덱스번호,1페이지당출력게시물수를 제시
		/*int startRow:시작행인덱번호, 가장 첫 번째 행은 0부터 시작
		   int size:    1페이지당출력게시물*/
		int total =hpdeskDAO.selectCount(conn);//전체게시물수
		List<HPDesk> hpdeskList = hpdeskDAO.select(conn,(pageNo-1)*size,size);
		return new HPDeskPage(total, pageNo,size, hpdeskList);//페이징처리한 클래스 객체만들기
		}catch(SQLException e) {
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
