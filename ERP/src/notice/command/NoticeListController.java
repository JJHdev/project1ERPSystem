package notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandController;
import notice.model.Notice;
import notice.service.NoticeListService;

//이 클래스는 article테이블의 모든 목록보기요청에 따라 호출되는 Controller이다
//요청주소 http://localhost/article/list.do
public class NoticeListController implements CommandController {
	
	private NoticeListService noticeListService= new NoticeListService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//1.파라미터얻기
		String strPageNo=request.getParameter("pageNo");
		int pageNo=1;
		
		if(strPageNo!=null) {
			pageNo=Integer.parseInt(strPageNo);
		}else {
			pageNo=1;
		}
			
		String strRowSize = request.getParameter("rowSize");
		int rowSize=5;
		if(strRowSize!=null) {
			rowSize=Integer.parseInt(strRowSize);
		}else {
			rowSize=5;
		}
		
		//2.비즈니스로직수행 <-> service<-> DAO <-> DB
			//향후 페이징추가작업예정~~리턴유형에 변화를 줄 예정이다.
			Notice noticePage = noticeListService.getNoticePage(pageNo,rowSize);
		//3.모델
			
			request.setAttribute("noticePage", noticePage);
			request.setAttribute("rowSize",rowSize);
			
		
		//4.뷰전달
		return "/view/notice/noticeList.jsp";
	}

}
