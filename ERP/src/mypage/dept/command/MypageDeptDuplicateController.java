package mypage.dept.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emp.model.MypageDbEmp;
import mvc.command.CommandController;
import mypage.dept.service.MypageDeptService;
import mypage.dept.service.MypageSelectDeptService;

public class MypageDeptDuplicateController implements CommandController {
	private static final String FORM_VIEW = "view/dept/deptRegisterForm.jsp";
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		//폼의 요청방식에 따라 회원가입폼보여줘 요청과  가입처리요청을 구분
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response);//회원가입폼보여줘
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response);//가입처리요청
		
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	private String processForm(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		MypageSelectDeptService mypageSelectDeptService =new MypageSelectDeptService();
		List<MypageDbEmp> mypageDbEmpList = mypageSelectDeptService.deptAllSelect();
		request.setAttribute("mypageDbEmpList",mypageDbEmpList);
		return FORM_VIEW;
	}
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		MypageSelectDeptService mypageSelectDeptService =new MypageSelectDeptService();
		List<MypageDbEmp> mypageDbEmpList = mypageSelectDeptService.deptAllSelect();
		request.setAttribute("mypageDbEmpList",mypageDbEmpList);
		int resultName = 0;
		//컨트롤러가 해야 할 일
		//1.파라미터얻기
		String strNewDeptno = request.getParameter("deptno");//user가 입력한 deptno
		String newDeptName = request.getParameter("deptname");//user가 입력한 deptname
		int newDeptno = 1;   
		if(strNewDeptno!=null) {
			newDeptno = Integer.parseInt(strNewDeptno);			
		}
		//2.비즈니스로직수행<->Service<->DAO<->DB
		//Service의 메서드호출
		MypageDeptService mypageDeptService = new MypageDeptService();
		//Service참조변수.메서드명();
		int resultNo=0;;
			resultNo = mypageDeptService.deptNoDuplicate(newDeptno);
			resultName = mypageDeptService.deptNameDuplicate(newDeptName);
		//3.Model(비즈니스로직수행결과)
		request.setAttribute("resultNo",resultNo);
		request.setAttribute("resultName",resultName);
		request.setAttribute("Deptno",strNewDeptno);
		request.setAttribute("DeptName",newDeptName);
		//4.View지정
		//http://ip주소:포트번호          /컨패/idDuplicateProc.do	
		return "view/dept/deptRegisterForm.jsp";
	}
}
