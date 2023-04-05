package emp.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import emp.dao.SelectEmpDAO;
import emp.model.SelectEmp;
import emp.model.SelectEmpPage;
import jdbc.JdbcUtil;
import jdbc.conn.ConnectionProvider;

public class SelectService {
	
	//부서,직급 조회
	public List<SelectEmp> select_deptname() throws SQLException {
		Connection conn = null;
		List<SelectEmp> selectEmp = null;
		try {
			conn = ConnectionProvider.getConnection();
			selectEmp = SelectEmpDAO.select_deptname(conn);
			return selectEmp;
	}finally {
		JdbcUtil.close(conn);
	}
	}
	public List<SelectEmp> select_erprank() throws SQLException {
		Connection conn = null;
		List<SelectEmp> selectEmp = null;
		try {
			conn = ConnectionProvider.getConnection();
			selectEmp = SelectEmpDAO.select_erprank(conn);
			return selectEmp;
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	
	//기본조회
	public  SelectEmpPage select_simple(String deptname,String erprank,String ename,int pageNo, int size) throws Exception{
		Connection conn = null;
		int total = 0;
		try {
			conn = ConnectionProvider.getConnection();
		
		if(deptname.equals("all")) {
			if(erprank.equals("all")) {
				if(ename.isEmpty()) {
					total = SelectEmpDAO.selectEx08_Simple_Count(conn);
					List<SelectEmp> SelectEmpList = SelectEmpDAO.selectEx08_Simple(conn,(pageNo-1)*size,size);
					return new SelectEmpPage(total, pageNo,size, SelectEmpList);
				}else {
					total = SelectEmpDAO.selectEx04_Simple_Count(ename,conn);
					List<SelectEmp> SelectEmpList = SelectEmpDAO.selectEx04_Simple(ename,conn,(pageNo-1)*size,size);
					return new SelectEmpPage(total, pageNo,size, SelectEmpList);
				}
			}else if(ename.isEmpty() ) {
				total = SelectEmpDAO.selectEx07_Simple_Count(erprank,conn);
				List<SelectEmp> SelectEmpList = SelectEmpDAO.selectEx07_Simple(erprank,conn,(pageNo-1)*size,size);
				return new SelectEmpPage(total, pageNo,size, SelectEmpList);
			}else {
				total = SelectEmpDAO.selectEx02_Simple_Count(erprank,ename,conn);
				List<SelectEmp> SelectEmpList = SelectEmpDAO.selectEx02_Simple(erprank,ename,conn,(pageNo-1)*size,size);
				return new SelectEmpPage(total, pageNo,size, SelectEmpList);
			}
		}else if(erprank.equals("all")) {
			if(ename.isEmpty() ) {
				total = SelectEmpDAO.selectEx06_Simple_Count(deptname,conn);
				List<SelectEmp> SelectEmpList = SelectEmpDAO.selectEx06_Simple(deptname,conn,(pageNo-1)*size,size);
				return new SelectEmpPage(total, pageNo,size, SelectEmpList);
			}else if(deptname.equals("all")) {
				total = SelectEmpDAO.selectEx04_Simple_Count(ename,conn);
				List<SelectEmp> SelectEmpList = SelectEmpDAO.selectEx04_Simple(ename,conn,(pageNo-1)*size,size);
				return new SelectEmpPage(total, pageNo,size, SelectEmpList);
			}else {
				total = SelectEmpDAO.selectEx03_Simple_Count(deptname,ename,conn);
				List<SelectEmp> SelectEmpList = SelectEmpDAO.selectEx03_Simple(deptname,ename,conn,(pageNo-1)*size,size);
				return new SelectEmpPage(total, pageNo,size, SelectEmpList);
			}
		}else if(ename.isEmpty() ) {
			if(deptname.equals("all")) {
				total = SelectEmpDAO.selectEx07_Simple_Count(erprank,conn);
				List<SelectEmp> SelectEmpList = SelectEmpDAO.selectEx07_Simple(erprank,conn,(pageNo-1)*size,size);
				return new SelectEmpPage(total, pageNo,size, SelectEmpList);	
			}else if(erprank.equals("all")){
				total = SelectEmpDAO.selectEx06_Simple_Count(deptname,conn);
				List<SelectEmp> SelectEmpList = SelectEmpDAO.selectEx06_Simple(deptname,conn,(pageNo-1)*size,size);
				return new SelectEmpPage(total, pageNo,size, SelectEmpList);
			}else {
				total = SelectEmpDAO.selectEx05_Simple_Count(deptname,erprank,conn);
				List<SelectEmp> SelectEmpList = SelectEmpDAO.selectEx05_Simple(deptname,erprank,conn,(pageNo-1)*size,size);
				return new SelectEmpPage(total, pageNo,size, SelectEmpList);
			}
		}else { 
			total = SelectEmpDAO.selectEx01_Simple_Count(deptname,erprank,ename,conn);
			List<SelectEmp> SelectEmpList = SelectEmpDAO.selectEx01_Simple(deptname,erprank,ename,conn,(pageNo-1)*size,size);
			return new SelectEmpPage(total, pageNo,size, SelectEmpList);
		}
		}finally {
			JdbcUtil.close(conn);
		}
	}
	
	//상세조회
	public  SelectEmp select_detail(int eno) throws Exception{
		Connection conn = null;
		SelectEmp selectEmp = null;
		try {
			conn = ConnectionProvider.getConnection();
			selectEmp =SelectEmpDAO.selectDetail(eno,conn);
			System.out.println("selectEmp2="+selectEmp);
		}catch (SQLException e) {
			e.printStackTrace();
		}
			return selectEmp;
		
	}
	
	//수정
	public void modify(int empno,int deptno,String ename,
			String erprank,String email,String tel,String hiredate,
			int sal,int grade,String emppwd) throws Exception{
		Connection conn = null;
		SelectEmp selectEmp = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//auto커밋 false설정

			SelectEmpDAO.modify(empno,deptno,ename,erprank,
					email,tel,hiredate,sal,grade,emppwd,conn);
			System.out.println("selectEmp="+selectEmp);
			
			
			conn.commit();
			//커밋
		}catch (SQLException e) {
			JdbcUtil.rollback(conn);
		}finally {
			JdbcUtil.close(conn);
			
		}
		
	}
	
	
	//삭제
	public void delete(int empno) throws Exception{
		Connection conn = null;
		SelectEmp selectEmp = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);//auto커밋 false설정

			SelectEmpDAO.delete(empno,conn);
			
			
			conn.commit();
			//커밋
		}catch (SQLException e) {
			JdbcUtil.rollback(conn);
		}finally {
			JdbcUtil.close(conn);
			
		}
		
	}
	
	
}//selectService
