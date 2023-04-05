package resv.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import resv.service.ResvDeleteService;
import mvc.command.CommandController;

//예약삭제 컨트롤러
public class ResvDeleteController implements CommandController {

	private ResvDeleteService resvDeleteService = new ResvDeleteService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터 받기
		String strResvno = request.getParameter("resvno");
		int resvno = Integer.parseInt(strResvno);
		
		//2.비즈니스로직수행
		int cnt = resvDeleteService.deleteResv(resvno);
		//3.모델
		request.setAttribute("cnt", cnt);
		//4.뷰
		
		return "/view/resv/resvDelete.jsp";
	}

}
