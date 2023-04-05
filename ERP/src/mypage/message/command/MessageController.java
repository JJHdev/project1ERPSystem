package mypage.message.command;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.coyote.Request;

import emp.model.Emp;
import mvc.command.CommandController;
import mypage.message.dao.MessageDAO;
import mypage.message.dto.MessageDTO;

public class MessageController implements CommandController {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String title = request.getParameter("title");
		String strsendEmpno = request.getParameter("sendEmpno");
		int sendEmpno = 1;   
		if(strsendEmpno!=null) {
			sendEmpno = Integer.parseInt(strsendEmpno);			
		}
		String strreceiveEmpno = request.getParameter("receiveEmpno");
		int receiveEmpno = 0;
		try {
			if(strreceiveEmpno!=null) {
				receiveEmpno = Integer.parseInt(strreceiveEmpno);			
			}
		}catch(NumberFormatException e) {
			return "/view/message/mypageMessageSendResult.jsp";
		}
		String message = request.getParameter("message");
		MessageDAO messageDAO = new MessageDAO();
		Emp empname = (Emp) request.getSession().getAttribute("EMP_USER");
		int cnt = messageDAO.insertMessage(sendEmpno,receiveEmpno,title,message,empname);
		request.setAttribute("cnt",cnt);
		if(cnt > 0) {
			System.out.println("메세지 전송 성공");
		}else {
			System.out.println("메세지 전송 실패");
		}
		return "/view/message/mypageMessageSendResult.jsp";
	}

}
