package surv.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandController;
import surv.service.SurvArticlePage;
import surv.service.SurvListArticleService;

public class SurvListArticleController implements CommandController {
	private SurvListArticleService listService = new SurvListArticleService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
			String strPageNo = request.getParameter("pageNo");
			int pageNo = 1;
			if(strPageNo !=null) {
				pageNo = Integer.parseInt(strPageNo);
						
			}
			String strRowSize = request.getParameter("rowSize"); //한 페이지당 보여줄 게시물수
			int rsize = 3;
			if(strRowSize!=null) {
				rsize = Integer.parseInt(strRowSize);			
			}
			//2.비즈니스로직수행<->Service<->DAO<->DB
					//p652 22라인
					//ArticlePage: 목록+페이징처리 관련 내용
			SurvArticlePage survArticlePage = listService.getArticlePage(pageNo,rsize);
			request.setAttribute("survArticlePage",survArticlePage);
			request.setAttribute("rsize",rsize);
			return "/view/survArticle/survListArticle.jsp";
		}

	}


