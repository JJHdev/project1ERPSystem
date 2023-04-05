package emp.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandController;
import emp.model.SelectEmp;
import emp.service.SelectService;

public class SelectEmpController implements CommandController {
	private SelectService selectService = new SelectService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SelectEmp> select_deptname = selectService.select_deptname();
		List<SelectEmp> select_erprank = selectService.select_erprank();
		request.setAttribute("select_deptname",select_deptname);
		request.setAttribute("select_erprank",select_erprank);

		return "/view/emp/selectForm.jsp";
	}

}
