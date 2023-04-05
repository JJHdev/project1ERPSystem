package emp.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import emp.dao.EmpDAO;
import emp.model.Emp;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;


public class RegisterService {
	private EmpDAO empDAO = new EmpDAO();

	public void register(Emp empReq) throws DuplicatedIdException {
		
		Connection conn = null;
		Emp emp = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//autoCommit기능 해제
			emp = empDAO.selectByEmpno(empReq.getEmpno(),conn);
			
			if(emp!=null) {
				JdbcUtil.rollback(conn);
				throw new DuplicatedIdException();
			}
			
			Emp newEmp = 
					new Emp(empReq.getEmpno(),empReq.getEname(),empReq.getEmppwd(),empReq.getDeptno(),
							empReq.getErprank(),empReq.getEmail(),empReq.getTel(),empReq.getHiredate(),empReq.getSal(),empReq.getGrade());
			
			empDAO.insert(newEmp,conn);//(회원되고싶은 유저가 입력한 회원정보);
			conn.commit(); //DB commit()처리
			
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);//DB rollback()처리
			e.printStackTrace();
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
		
	}
	
}

