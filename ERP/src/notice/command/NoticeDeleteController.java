package notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandController;
import notice.service.NoticeDeleteService;

public class NoticeDeleteController implements CommandController {

		NoticeDeleteService noticeDeleteService = new NoticeDeleteService();
		@Override
		public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
			String strno=request.getParameter("no");
			int no =Integer.parseInt(strno);
			String pageNoStr=request.getParameter("pageNo");
			int pageNo=Integer.parseInt(pageNoStr);
			
			int cnt = noticeDeleteService.deleteNotice(no);
			
			//3.Model
			request.setAttribute("cnt", cnt);
			request.setAttribute("no", no);
			request.setAttribute("pageNo", pageNo);
			
			
			
			return "/view/notice/noticeDelete.jsp";
		}

	}