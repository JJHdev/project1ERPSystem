package mypage.Service;

import java.sql.Connection;
import java.sql.SQLException;

import emp.dao.EmpDAO;
import emp.model.Emp;
import emp.model.MypageDbEmp;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;
import mypage.Error.InvalidException;
import mypage.Error.MemberNotFoundException;

public class UpdateService {
	private EmpDAO empDAO = new EmpDAO();	

	public void updateDname(int empNo, int curDno, int newDno) {
		Connection conn = null;
		MypageDbEmp mypageDbEmp = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//autoCommit 설정x
			mypageDbEmp = empDAO.MypageselectByEmpno(empNo,conn);
			if(mypageDbEmp==null) { //id와 일치하는 회원이 존재하지 않으면
				JdbcUtil.rollback(conn);//rollback()처리
				throw new MemberNotFoundException(); //특정회원존재x 예외발생
			}
			//일치하면 true리턴, 불일치하면 false
			if(!mypageDbEmp.matchDno(curDno)) {
				throw new InvalidException(); //비번불일치예외 발생
			}
			//3.비번변경p621 28라인
			mypageDbEmp.setDeptno(newDno); //새비번을 Member클래스의 필드memberpwd의 값으로 설정
			empDAO.updateDno(conn, mypageDbEmp);
			conn.commit();//커밋해라
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
	public void updatePwd(int empNo, String curPwd, String newPwd) {
		Connection conn = null;
		MypageDbEmp mypageDbEmp = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//autoCommit 설정x
			
			//로그인한 회원임을 확인
			//1.user가 입력한 id와 기존  회원의 id와 일치
			//user가 입력한 id를 사용하는 기존member정보가 담긴  Member객체받는다
			mypageDbEmp = empDAO.MypageselectByEmpno(empNo,conn);
			if(mypageDbEmp==null) { //id와 일치하는 회원이 존재하지 않으면
				JdbcUtil.rollback(conn);//rollback()처리
				throw new MemberNotFoundException(); //특정회원존재x 예외발생
			}
			//2.user가 입력한 (변경전 현재)비번과 기존  회원의 비번과 일치
			//일치하면 true리턴, 불일치하면 false
			if(!mypageDbEmp.matchPassword(curPwd)){
				throw new InvalidException(); //비번불일치예외 발생
			}
			//3.비번변경p621 28라인
			mypageDbEmp.setEmppwd(newPwd); //새비번을 Member클래스의 필드memberpwd의 값으로 설정
			empDAO.updateEmppwd(conn,mypageDbEmp);
			conn.commit();//커밋해라
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}

	public void updateGrade(int empNo, int curGrade, int newGrade) {
		Connection conn = null;
		MypageDbEmp mypageDbEmp = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//autoCommit 설정x
			//로그인한 회원임을 확인
			//1.user가 입력한 id와 기존  회원의 id와 일치
			//user가 입력한 id를 사용하는 기존member정보가 담긴  Member객체받는다
			mypageDbEmp = empDAO.MypageselectByEmpno(empNo,conn);
			if(mypageDbEmp==null) { //id와 일치하는 회원이 존재하지 않으면
				JdbcUtil.rollback(conn);//rollback()처리
				throw new MemberNotFoundException(); //특정회원존재x 예외발생
			}
			
			//2.user가 입력한 (변경전 현재)비번과 기존  회원의 비번과 일치
			//일치하면 true리턴, 불일치하면 false
			if(!mypageDbEmp.matchGrade(curGrade)){
				throw new InvalidException(); //비번불일치예외 발생
			}
			//3.비번변경p621 28라인
			mypageDbEmp.setGrade(newGrade); //새비번을 Member클래스의 필드memberpwd의 값으로 설정
			empDAO.updateGrade(conn,mypageDbEmp);
			conn.commit();//커밋해라
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}

	public void updateEmail(int empNo, String curEmail, String newEmail) {
		Connection conn = null;
		MypageDbEmp mypageDbEmp = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//autoCommit 설정x
			mypageDbEmp = empDAO.MypageselectByEmpno(empNo,conn);
			if(mypageDbEmp==null) { //id와 일치하는 회원이 존재하지 않으면
				JdbcUtil.rollback(conn);//rollback()처리
				throw new MemberNotFoundException(); //특정회원존재x 예외발생
			}
			//일치하면 true리턴, 불일치하면 false
			if(!mypageDbEmp.matchEmail(curEmail)){
				throw new InvalidException(); //비번불일치예외 발생
			}
			//3.비번변경p621 28라인
			mypageDbEmp.setEmail(newEmail); //새비번을 Member클래스의 필드memberpwd의 값으로 설정
			empDAO.updateEmail(conn,mypageDbEmp);
			conn.commit();//커밋해라
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
	public void updateLevel(int empNo, String curLevel, String newLevel) {
		Connection conn = null;
		MypageDbEmp mypageDbEmp = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//autoCommit 설정x
			//user가 입력한 id를 사용하는 기존member정보가 담긴  Member객체받는다
			mypageDbEmp = empDAO.MypageselectByEmpno(empNo,conn);
			if(mypageDbEmp==null) { //id와 일치하는 회원이 존재하지 않으면
				JdbcUtil.rollback(conn);//rollback()처리
				throw new MemberNotFoundException(); //특정회원존재x 예외발생
			}
			//일치하면 true리턴, 불일치하면 false
			if(!mypageDbEmp.matchLevel(curLevel)){
				throw new InvalidException(); //비번불일치예외 발생
			}
			//3.비번변경p621 28라인
			mypageDbEmp.setLevel(newLevel); //새비번을 Member클래스의 필드memberpwd의 값으로 설정
			empDAO.updateLevel(conn,mypageDbEmp);
			conn.commit();//커밋해라
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}

	public void updateSal(int empNo, int curSal, int newSal) {
		Connection conn = null;
		MypageDbEmp mypageDbEmp = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//autoCommit 설정x
			//user가 입력한 id를 사용하는 기존member정보가 담긴  Member객체받는다
			mypageDbEmp = empDAO.MypageselectByEmpno(empNo,conn);
			if(mypageDbEmp==null) { //id와 일치하는 회원이 존재하지 않으면
				JdbcUtil.rollback(conn);//rollback()처리
				throw new MemberNotFoundException(); //특정회원존재x 예외발생
			}
			//일치하면 true리턴, 불일치하면 false
			if(!mypageDbEmp.matchSal(curSal)){
				throw new InvalidException(); //비번불일치예외 발생
			}
			//3.비번변경p621 28라인
			mypageDbEmp.setSal(newSal); //새비번을 Member클래스의 필드memberpwd의 값으로 설정
			empDAO.updateSal(conn,mypageDbEmp);
			conn.commit();//커밋해라
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}

	public void updateTel(int empNo, String curTel, String newTel) {
		Connection conn = null;
		MypageDbEmp mypageDbEmp = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//autoCommit 설정x
			//user가 입력한 id를 사용하는 기존member정보가 담긴  Member객체받는다
			mypageDbEmp = empDAO.MypageselectByEmpno(empNo,conn);
			if(mypageDbEmp==null) { //id와 일치하는 회원이 존재하지 않으면
				JdbcUtil.rollback(conn);//rollback()처리
				throw new MemberNotFoundException(); //특정회원존재x 예외발생
			}
			//일치하면 true리턴, 불일치하면 false
			if(!mypageDbEmp.matchTel(curTel)){
				throw new InvalidException(); //비번불일치예외 발생
			}
			//3.비번변경p621 28라인
			mypageDbEmp.setTel(newTel); //새비번을 Member클래스의 필드memberpwd의 값으로 설정
			empDAO.updateTel(conn,mypageDbEmp);
			conn.commit();//커밋해라
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		}finally {
			JdbcUtil.close(conn);
		}
	}
}
