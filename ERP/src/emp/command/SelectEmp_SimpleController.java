package emp.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import emp.model.SelectEmp;
import emp.model.SelectEmpPage;
import emp.service.SelectService;
import mvc.command.CommandController;

public class SelectEmp_SimpleController implements CommandController {
	
	private static final String FORM_VIEW="/view/emp/selectForm.jsp";
	private SelectService selectService = new SelectService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		
		
		//1.파라미터 받기
		
		String strPageNo = request.getParameter("pageNo"); //보고싶은페이지
		int pageNo = 1;
		if(strPageNo != null) {
			pageNo = Integer.parseInt(strPageNo);			
		}
		
		String strRowSize = request.getParameter("rowSize"); //페이지당 볼수있는 게시글수
		int size = 5;
		if(strRowSize==null) {
			 size = 5;
		}else {
			 size = Integer.parseInt(strRowSize);			
		}
		
		
		request.setCharacterEncoding("UTF-8");
		String deptname= request.getParameter("deptname");
		String erprank= request.getParameter("erprank");
		String ename= request.getParameter("ename");
		System.out.println("deptname="+deptname);
		System.out.println("erprank="+erprank);
		System.out.println("ename="+ename);
		request.setAttribute("deptname",deptname);
		request.setAttribute("erprank",erprank);
		request.setAttribute("ename",ename);
		
		//2.비스니즈로직수행
		
		SelectEmpPage selectEmpPage = selectService.select_simple(deptname,erprank,ename,pageNo,size);
		request.setAttribute("selectEmpPage",selectEmpPage);
		request.setAttribute("size",size);
		/*
		 * request.setAttribute("SelectEmp",SelectEmp);
		 */		
		
		return "/view/emp/select_SimpleForm.jsp";
	}

}
