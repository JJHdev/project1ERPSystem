package notice.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.model.Comment;
import comment.service.CommentReadService;
import mvc.command.CommandController;
import notice.model.Notice;
import notice.service.NoticeReadService;

public class NoticeReadController implements CommandController{
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//필드
		NoticeReadService noticeReadService = new NoticeReadService();
		CommentReadService commentReadService = new CommentReadService();
		//1파라미터 받기
		//상세조회할 글번호
		//만약 파라미터 no(즉,상세조회할 글번호)가 null이면 RuntimeException발생
		String strno = request.getParameter("no");//상세조회할 페이지
		if(strno==null) {
	
		}
		int no =Integer.parseInt(strno);
		
		//만약 파라미터 pageNo(요청페이지)가 null이면 요청페이지 주소를 1로 설정
		String strPageNo=request.getParameter("pageNo");
		int pageNo=1;
		if(strPageNo!=null) {
			pageNo=Integer.parseInt(strPageNo);
		}
		//만약 파라미터 rowSize(페이지당게시글수)가  null이면 페이지당게시글수를 3으로 설정
		String strRowSize = request.getParameter("rowSize");
		int rowSize=3;
		if(strRowSize!=null) {
			rowSize=Integer.parseInt(strRowSize);
		}
		
		Notice noticeRead=noticeReadService.getNotice(no,true);
		Comment commentRead = commentReadService.getComment(no);
		//3.모델
		//릴레이용 pageNo=요청페이지&rowSize=1 페이지당게시글수
		request.setAttribute("noticeRead", noticeRead);//article데이터
		request.setAttribute("pageNo", pageNo);//요청페이지
		request.setAttribute("rowSize",rowSize);//1페이지당 게시글수
		//상세보기 페이지에 댓글이 함께보이기위해 Comment는 따로 ReadController만들지않고 함께제공
		request.setAttribute("commentRead",commentRead);
		
		return "/view/notice/noticeRead.jsp";
	}

}