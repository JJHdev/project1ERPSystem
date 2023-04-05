package emp.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import emp.service.SelectService;
import mvc.command.CommandController;

public class SelectEmp_DeleteController implements CommandController {
	
	private static final String FORM_VIEW="/view/emp/select_DetailForm.jsp";
	private SelectService selectService = new SelectService();
	
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("진입");
		//1.파라미터 받기
		request.setCharacterEncoding("UTF-8");
		String empnoStr= request.getParameter("empno");
		int empno = Integer.parseInt(empnoStr) ;
		
		System.out.println("왔니??");
		//2.비스니즈로직수행
		
		selectService.delete(empno);
		
		return "/selectEmp.aa";
	}

}
