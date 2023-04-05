package worktime.Service;

import java.sql.Connection;



import java.sql.SQLException;

import jdbc.conn.ConnectionProvider;
import worktime.dao.Work_In_DAO;
import worktime.dao.Work_Out_DAO;
import worktime.model.Work_Out;

public class WorkTimeOutService {

	//퇴근 관련 정보 인서트문 
	public Work_Out insertWorkOutTime(String todayoutnoti, int empno) {
		int cnt=0;
		Work_Out_DAO work_Out_DAO = new Work_Out_DAO();
		Work_Out workout =null;
		try {
			Connection conn = ConnectionProvider.getConnection();
			
			cnt = work_Out_DAO.insertWorkOutTime(conn, todayoutnoti, empno);//커넥트와,todayoutnoti(특이사항사유),empno(사원번호)를 
			System.out.println("cnt"+cnt);  //실행되면 1아니면 0이된다.
			
			workout =work_Out_DAO.selectWorkOutTime(conn, empno);
			System.out.println("workout1="+workout);
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		return workout;
	}
	
	// 퇴근 체크 버튼 누른 후 DB로부터 저장된 todayoutnoti(work_out_notice), empno을  받아온다.
		public Work_Out selectWorkOutTime(String todayoutnoti, int empno) {
			Work_Out_DAO work_Out_DAO = new Work_Out_DAO();
			Work_Out workout =null;
		
			try {
				Connection conn = ConnectionProvider.getConnection();
				
				workout  =work_Out_DAO.selectWorkOutTime(conn, empno);
				
						
				System.out.println("workout2="+workout);
				
			}catch(SQLException e) {
				
				throw new RuntimeException();
			}
			return workout;
		}
	
}
