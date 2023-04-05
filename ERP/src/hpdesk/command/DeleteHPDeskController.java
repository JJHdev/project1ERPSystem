package hpdesk.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hpdesk.service.DeleteHPDeskService;
import mvc.command.CommandController;
//삭제컨트롤러
//hpdesk/deletehpdesk.aa
public class DeleteHPDeskController implements CommandController {

	private DeleteHPDeskService deleteHPDeskService=new DeleteHPDeskService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String strNo=request.getParameter("hdno");//글번호
		int hdno=Integer.parseInt(strNo);
		
		int cnt =deleteHPDeskService.delete(hdno);

		request.setAttribute("cnt", cnt);

		return "/view/hpdesk/deleteHPDeskForm.jsp";
	}

}
