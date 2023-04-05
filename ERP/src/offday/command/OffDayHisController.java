package offday.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emp.model.Emp;
import mvc.command.CommandController;
import offday.Service.OffDayService;
import offday.model.OffDay;

public class OffDayHisController implements CommandController {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
			System.out.println("OffDayController.process()호출");
		
		Emp user =(Emp) request.getSession().getAttribute("EMP_USER");
		user.getEmpno(); //로그인한 유저사원번호
	     
		OffDayService offdayService = new OffDayService(); //DB로갈때
		
		OffDay offday =offdayService.selectOffDayHis(user.getEmpno());  
		
		
		request.setAttribute("offday", offday);
		
		
		return "/view/worktime/offlist.jsp";
	}
		

}
