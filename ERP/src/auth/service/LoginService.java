package auth.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import emp.dao.EmpDAO;
import emp.model.Emp;

public class LoginService {

	//필드
	private EmpDAO empDAO= new EmpDAO();
	
	//메서드
	//리턴타입은 User : 로그인에 성공한 회원정보
	public Emp login(int no,String pwd){
		
		Connection conn =null;
		try {
			conn = ConnectionProvider.getConnection();
		//1. user가 입력한 id를 사용하는 회원정보 조회
		
			Emp emp = empDAO.selectByEmpno(no, conn);
		
		
		//2. 해당회원의 비밀번호와 유저가 입력한 비밀번호 일치 조회
		
		if(emp == null) {
			throw new LoginFailException();
		}
		boolean result = emp.matchPassword(pwd);
			if(!result) {
				throw new LoginFailException();
		}
		
		//3. User를 만들어서 리턴
		Emp empUser =new Emp(emp.getEmpno(),
							emp.getEname(),
							emp.getEmppwd(),
							emp.getDeptno(),
							emp.getDeptname(),
							emp.getErprank(),
							emp.getEmail(),
							emp.getTel(),
							emp.getHiredate(),
							emp.getSal(),
							emp.getGrade());
		
			return empUser;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	
	}//login() 끝;
	
	
	
	
}//LoginService끝