package emp.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import emp.model.SelectEmp;
import emp.service.SelectService;
import mvc.command.CommandController;

public class SelectEmp_DetailController implements CommandController {
	private SelectService selectService = new SelectService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		String empnoStr= request.getParameter("empno");
		int empno = Integer.parseInt(empnoStr) ;
		SelectEmp SelectEmp = selectService.select_detail(empno);
		
		HttpSession session = request.getSession();
		session.setAttribute("AUTHUSER",SelectEmp);
		
		request.setAttribute("SelectEmp",SelectEmp);
		
//		for(int i=0; i<selectFrm1.length; i++) {
	//		System.out.println("selectFrm1 : "+selectFrm1[i]);
	//	}
		System.out.println("SelectEmp"+SelectEmp);
		return "view/emp/select_DetailForm.jsp";
	}

}
