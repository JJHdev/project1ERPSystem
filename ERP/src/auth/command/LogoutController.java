package auth.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.command.CommandController;

public class LogoutController implements CommandController{
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
			System.out.println("LogoutHandler process() 호출");
			
		HttpSession session = request.getSession(false);
			if(session!=null) {
				session.invalidate();
			}
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			return null;
	}//process()끝

}
