package comment.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.service.CommentDeleteService;
import mvc.command.CommandController;


public class CommentDeleteController implements CommandController{

	CommentDeleteService commentDeleteService = new CommentDeleteService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strno=request.getParameter("cno");
		int cno =Integer.parseInt(strno);
		String rowSizeStr = request.getParameter("rowSize");//작성자명
		int rowSize=Integer.parseInt(rowSizeStr);
		String strNoo =request.getParameter("no");
		int no = Integer.parseInt(strNoo);
		String strPageNo =request.getParameter("pageNo");
		int pageNo=Integer.parseInt(strPageNo);
		
		int cnt = commentDeleteService.deleteComment(cno);
		
		//3.Model
		request.setAttribute("cnt", cnt);
		request.setAttribute("no",no);
		request.setAttribute("rowSize",rowSize);
		request.setAttribute("pageNo",pageNo);
		
		
		
		return "/view/comment/commentDelete.jsp";
	}

}