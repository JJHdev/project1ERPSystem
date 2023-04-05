package offday.Service;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import jdbc.conn.ConnectionProvider;
import offday.dao.OffDayDAO;
import offday.model.OffDay;

public class OffDayService {

	
	public OffDay insertOffDay(int empno,int deptno,Date startday, Date endday,String offnotice) {
		int cnt =0;
		OffDayDAO offdayDAO = new OffDayDAO();
		OffDay offday =null;
		try {
			Connection conn = ConnectionProvider.getConnection();
			
			cnt = OffDayDAO.insertOffDay(conn, empno,deptno,startday,endday,offnotice);//커넥트와,todaynoti(특이사항사유),empno(사원번호)를 
			System.out.println("cnt"+cnt);  //실행되면 1아니면 0이된다.
			
			
			offday  =offdayDAO.selectOffDay(conn,empno,deptno);
			System.out.println("offday="+offday);
		}catch(SQLException e) {
			
			throw new RuntimeException();
		}
		return offday;
		
	}
	//////////////////////셀렉문/////////////////////////
	public OffDay selectOffday(int empno ,int deptno) {
		OffDayDAO offdayDAO = new OffDayDAO();
		OffDay offday =null;
	
		try {
			Connection conn = ConnectionProvider.getConnection();
			
			offday  = offdayDAO.selectOffDay(conn, empno,deptno);
			
					
			System.out.println("offday="+offday);
			
		}catch(SQLException e) {
			
			throw new RuntimeException();
		}
		return offday;
		
	}
	
	//////////////////////셀렉문/////////////////////////
	public OffDay selectOffDayHis(int empno) {
		OffDayDAO offdayDAO = new OffDayDAO();
		
	
		try {
			Connection conn = ConnectionProvider.getConnection();
			
			List<OffDay> offdayhis = offdayDAO.selectOffDayHis(conn, empno);
			return new OffDay(offdayhis);
			
			
		}catch(SQLException e) {
			
			throw new RuntimeException();
		}
		
	}
	
	
	
	
	
}
