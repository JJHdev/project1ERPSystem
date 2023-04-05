package worktime.Service;

import java.sql.Connection;


import java.sql.SQLException;
import java.util.List;

import jdbc.conn.ConnectionProvider;
import worktime.dao.Work_In_DAO;
import worktime.model.Work_In;

public class WorkTimeService {


	public Work_In insertWorkintime(String todaynoti, int empno) {
		int cnt =0;
		Work_In_DAO work_In_DAO = new Work_In_DAO();
		Work_In workin =null;
		try {
			Connection conn = ConnectionProvider.getConnection();
			
			cnt = Work_In_DAO.insertWorkinTime(conn, todaynoti, empno);//커넥트와,todaynoti(특이사항사유),empno(사원번호)를 
			System.out.println("cnt"+cnt);  //실행되면 1아니면 0이된다.
			
			
			workin  =work_In_DAO.selectWorkinTime(conn, empno);
			System.out.println("workin="+workin);
		}catch(SQLException e) {
			
			throw new RuntimeException();
		}
		return workin;
		
	}
	// 출근체크 버튼 누른 후 DB로부터 저장된 todaynoti, empno을 받아온다.
	public Work_In selectWorkintime(String todaynoti, int empno) {
		Work_In_DAO work_In_DAO = new Work_In_DAO();
		Work_In workin =null;
	
		try {
			Connection conn = ConnectionProvider.getConnection();
			
			workin  =work_In_DAO.selectWorkinTime(conn, empno);
			
					
			System.out.println("workin="+workin);
			
		}catch(SQLException e) {
			
			throw new RuntimeException();
		}
		return workin;
		
	}
	
}
