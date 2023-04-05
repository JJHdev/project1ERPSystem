package emp.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emp.service.IdDuplicateService;
import mvc.command.CommandController;

public class IdduplicateController implements CommandController {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		
			String empno =request.getParameter("Txtempno");
			
			IdDuplicateService idDuplicateService = new IdDuplicateService();
			boolean idduplicate = idDuplicateService.idDuplicate(empno);
			
			request.setAttribute("empno", empno);
			request.setAttribute("idduplicate", idduplicate);
		
		return "view/mypage/idDuplicate.jsp";
	}


}
