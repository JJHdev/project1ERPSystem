package mypage.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import emp.dao.EmpDAO;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import mypage.deletemodel.MypageDeletePage;
import mypage.deletemodel.MypageEmp;

public class DeleteShowService {
	//필드
		private EmpDAO empDAO = new EmpDAO();
		
		public MypageDeletePage getDeletePage(int pageNo,int size) {
			Connection conn= null;
			try {
				conn = ConnectionProvider.getConnection();
				//페이징처리와 관련하여
				//limit 시작행인덱번호,1페이지당 출력게시물수를 제시
				/*int startRow:시작행인덱번호, 가장 첫 번째 행은 0부터 시작
				   int size:    1페이지당출력게시물*/
				int total = empDAO.selectCount(conn);//전체게시물수
				List<MypageEmp> mypageDeleteList = empDAO.select(conn,(pageNo-1)*size,size);
				return new MypageDeletePage(total, pageNo, 
						   					size, mypageDeleteList);//p651 21라인
			}catch(SQLException e) {
				throw new RuntimeException();
			} finally {
				JdbcUtil.close(conn);
			}
		}
		
		public MypageDeletePage getDeleteSearchEno(int pageNo,int size, int eno) {
			Connection conn= null;
			try {
				conn = ConnectionProvider.getConnection();
				int total = 1;//전체게시물수
				List<MypageEmp> mypageDeleteList = empDAO.selecteno(conn,(pageNo-1)*size,size,eno);
				return new MypageDeletePage(total, pageNo, 
						   					1, mypageDeleteList);//p651 21라인
			}catch(SQLException e) {
				throw new RuntimeException();
			} finally {
				JdbcUtil.close(conn);
			}
		}
}
