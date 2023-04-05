package mypage.message.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandController;
import mypage.message.dao.MessageDAO;
import mypage.message.dto.MessageDTO;

public class MessageDeleteController implements CommandController {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MessageDTO messageDTO = (MessageDTO) request.getSession().getAttribute("dto");
		MessageDAO MessageDAO = new MessageDAO();
		int cnt = MessageDAO.deleteMessage(messageDTO.getReceiveempno());
		request.setAttribute("cnt",cnt);
		if(cnt>0) {
			System.out.println("메세지 삭제 성공");
		}else {
			System.out.println("메세지 삭제 실패");
		}
		return "/view/message/mypagemessage.jsp";
	}
}
