package mypage.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandController;
import mypage.Service.DeleteEmpService;

public class MypageDeleteController implements CommandController {

	private DeleteEmpService deleteEmpService = new DeleteEmpService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//1. 파라미터받기
		 String[] strdelempno =request.getParameterValues("delempno");
		 
		 int[] delempno = new int[strdelempno.length];
		 int i = 0;
	        for (String var: strdelempno) {
	        	delempno[i] = Integer.parseInt(var);
	        	i++;
	        }
		//2. 비즈니스로직
		int cnt = deleteEmpService.deleteEmp(delempno);
		//3. modle
		request.setAttribute("cnt",cnt);
		//4.view
		return "/view/deletemypage/mypagedeleteresult.jsp";
	}
}
