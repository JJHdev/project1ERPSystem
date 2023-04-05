package emp.command;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import emp.model.SelectEmp;
import emp.service.SelectService;
import mvc.command.CommandController;

public class SelectEmp_ModifyController implements CommandController {
	
	private static final String FORM_VIEW="/view/emp/select_DetailForm.jsp";
	private SelectService selectService = new SelectService();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("SelectController3컨트롤의 -processSubmit()");

		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("get");
			return processForm(request,response);//회원가입폼보여줘
		
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			System.out.println("post");
			return processSubmit(request,response);//가입처리요청
		
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
		
	public String processForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("SelectEmpController3의 processForm 진입");
		request.setCharacterEncoding("UTF-8");
		String empnoStr= request.getParameter("empno");
		int empno = Integer.parseInt(empnoStr) ;
		System.out.println("empno="+empno);
		
		SelectEmp SelectEmp = selectService.select_detail(empno);
		request.setAttribute("SelectEmp",SelectEmp);
		System.out.println("3의 selectEmp="+SelectEmp);
		 
		return "view/emp/select_ModifyForm.jsp";
	}
	public String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("post진입");
		//1.파라미터 받기
		request.setCharacterEncoding("UTF-8");
		String empnoStr= request.getParameter("empno");
		String emppwd= request.getParameter("emppwd");
		String deptnoStr= request.getParameter("deptno");
		String ename= request.getParameter("ename");
		String erprank= request.getParameter("erprank");
		String email= request.getParameter("email");
		String tel= request.getParameter("tel");
		String hiredateStr= request.getParameter("hiredate");
		String salStr= request.getParameter("sal");
		String gradeStr= request.getParameter("grade");
		int empno = Integer.parseInt(empnoStr) ;
		int deptno = Integer.parseInt(deptnoStr) ;
		int sal = Integer.parseInt(salStr) ;
		int grade = Integer.parseInt(gradeStr) ;
		//Date hiredate = formatter.parse(hiredateStr);
		
		//2.비스니즈로직수행
		
		selectService.modify(empno,deptno,ename,
				erprank,email,tel,hiredateStr,sal,grade,emppwd);
		
//		return "/view/emp/selectForm.jsp";
		return "/selectEmp.aa";
	}

}
