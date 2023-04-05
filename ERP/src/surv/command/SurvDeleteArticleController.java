package surv.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandController;
import surv.service.SurvDeleteArticleService;

public class SurvDeleteArticleController implements CommandController {

	private SurvDeleteArticleService delArticleService
			= new SurvDeleteArticleService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1. 파라미터받기
		String strNo = request.getParameter("no");
		int no = Integer.parseInt(strNo);
		//2.비즈니스로직<->Service<->DAO<->DB
		
		int cnt = delArticleService.deleteArticle(no);
		//3.Model
		request.setAttribute("cnt", cnt);
		//4.View
		
		return "/view/survArticle/survDeleteArticle.jsp";
	}

}
