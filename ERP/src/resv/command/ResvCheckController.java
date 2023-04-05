package resv.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emp.model.Emp;
import mvc.command.CommandController;
import resv.model.Resv;
import resv.service.ResvCheckService;

//나의 예약조회하는 컨트롤러 
public class ResvCheckController implements CommandController {
	
	private ResvCheckService resvCheckService = new ResvCheckService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//로그인유저 세션에서 받기
		Emp user = (Emp)request.getSession().getAttribute("EMP_USER");
		
		//파라미터얻기
		int empno = user.getEmpno();
		
		//비즈니스로직수행
		List<Resv> resvList = resvCheckService.getResvList(empno);
		
		//모델&뷰
		request.setAttribute("resvList", resvList);
		
		return "view/resv/resvCheck.jsp";
	}

}
