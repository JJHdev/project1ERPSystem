package offday.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandController;
import offday.Service.DeleteDayOffService;

public class DeleteDayOffController implements CommandController {

	
	private DeleteDayOffService deldayoffService
	= new DeleteDayOffService();
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터 받기
		String offdayno1 = request.getParameter("offdayno");//글번호
		int offdayno = Integer.parseInt(offdayno1);
		
		//2.비즈니스로직<->Service<->DAO<->DB
		/*파라미터  int no : 삭제할 글번호
		 *리턴타입  int cnt: 삭제(update)된 행 수. 1이면 삭제성공,0이면 삭제실패*/
		int cnt = deldayoffService.deleteDayOff(offdayno);
		System.out.println("컨트롤러 =="+cnt);
		//3.Model
		request.setAttribute("cnt", cnt);
		
		//4.View
		return "/view/worktime/offupdateSuccess.jsp";
	}
}
