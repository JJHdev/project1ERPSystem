package emp.command;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emp.dao.EmpDAO;
import emp.model.Emp;
import emp.model.MypageDbEmp;
import emp.service.IdDuplicateService;
import jdbc.conn.ConnectionProvider;
import mvc.command.CommandController;

public class MypageInfoController implements CommandController {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MypageInfoController");
		Emp user = null;
		int empNo = 0;
		String strSubmit = request.getParameter("submit");
		String strSearch = request.getParameter("search");
		try {
			if(strSubmit.equals("사원번호찾기")) {
				String strEmpNo = request.getParameter("search");
				empNo =Integer.parseInt(strEmpNo);
			}else {
				user = (Emp)request.getSession().getAttribute("EMP_USER");
				empNo = user.getEmpno();
			}
		}catch(NullPointerException e) {
				user = (Emp)request.getSession().getAttribute("EMP_USER");
				empNo = user.getEmpno();
		}catch(NumberFormatException e) {
				user = (Emp)request.getSession().getAttribute("EMP_USER");
				empNo = user.getEmpno();
		}
		Connection conn = ConnectionProvider.getConnection();
		EmpDAO mypageInfo = new EmpDAO();
		
		MypageDbEmp empdb = mypageInfo.MypageselectByEmpno(empNo, conn);
		int deptNo = empdb.getDeptno();
		MypageDbEmp deptdb = mypageInfo.MypageselectByDeptno(deptNo, conn);
		List<MypageDbEmp> searcheno = mypageInfo.MypageAllSelect(conn);
		request.setAttribute("empdb",empdb);
		request.setAttribute("deptdb",deptdb);
		request.setAttribute("searcheno",searcheno);
		
		return "view/mypage/mypageInfor.jsp";
	}
	
	public String searchView (HttpServletRequest request, HttpServletResponse response,int empno) throws SQLException {
		Emp user = null;
			
		Connection conn = ConnectionProvider.getConnection();
		EmpDAO mypageInfo = new EmpDAO();
		
		MypageDbEmp empdb = mypageInfo.MypageselectByEmpno(empno, conn);
		List<MypageDbEmp> searcheno = mypageInfo.MypageAllSelect(conn);
		int deptNo = empdb.getDeptno();
		MypageDbEmp deptdb = mypageInfo.MypageselectByDeptno(deptNo, conn);
		request.setAttribute("empdb",empdb);
		request.setAttribute("deptdb",deptdb);
		request.setAttribute("searcheno",searcheno);
		
		return "view/mypage/mypageInfor.jsp";
	}

}
