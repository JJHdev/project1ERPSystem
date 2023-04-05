package emp.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import emp.dao.EmpDAO;
import emp.model.Emp;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;


public class RegisterService {
	//필드
	
	private EmpDAO empDAO = new EmpDAO();
	

	public void register(Emp empReq) throws DuplicatedIdException {
		System.out.println("RegisterService에 register() 진입 레지스터컨트롤러에서 받아온 empReq의 저장된 내용 = "+ empReq);
		
		Connection conn = null;
		Emp emp = null;
		System.out.println("1번");
		try {
			System.out.println("2번");
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//autoCommit기능 해제
			emp = empDAO.selectByEmpno(empReq.getEmpno(),conn);
			
			System.out.println("3번");
			if(emp!=null) {
				System.out.println("4번");
				JdbcUtil.rollback(conn);
				throw new DuplicatedIdException();
			}
			
			System.out.println("5번");
			
			Emp newEmp = 
					new Emp(empReq.getEmpno(),empReq.getEname(),empReq.getEmppwd(),empReq.getDeptno(),
							empReq.getErprank(),empReq.getEmail(),empReq.getTel(),empReq.getHiredate(),empReq.getSal(),empReq.getGrade());
			
			System.out.println("6번");
			empDAO.insert(newEmp,conn);//(회원되고싶은 유저가 입력한 회원정보);
			System.out.println("7번");
			conn.commit(); //DB commit()처리
			System.out.println("8번");
			
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);//DB rollback()처리
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
		
	}
	
}

