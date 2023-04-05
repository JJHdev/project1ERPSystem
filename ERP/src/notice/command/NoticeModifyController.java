package notice.command;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emp.model.Emp;
import mvc.command.CommandController;
import notice.exception.NoticeNotFoundException;
import notice.exception.PermissionDeniedException;
import notice.model.Notice;
import notice.service.NoticeModifyService;
import notice.service.NoticeReadService;

public class NoticeModifyController implements CommandController  {

private static final String FORM_VIEW = "/view/notice/noticeModify.jsp";
	//필드
	//상세보기위한 서비스
	private NoticeReadService noticReadService =new NoticeReadService();
	
	//수정처리를 위한 서비스
	private NoticeModifyService noticeModifyService = new NoticeModifyService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(request.getMethod().equalsIgnoreCase("GET")) {
			
			return processForm(request,response);//수정폼보여줘
		
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			
			return processSubmit(request,response);//수정처리요청
		
		}else {
		
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}//process끝
	
	private String processForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		try {
			
			String rowSizeStr = request.getParameter("rowSize");//작성자명
			int rowSize=Integer.parseInt(rowSizeStr);
			String strNo =request.getParameter("no");
			int no = Integer.parseInt(strNo);
			String strPageNo =request.getParameter("pageNo");
			int pageNo=Integer.parseInt(strPageNo);
			
		
			Emp empUser = (Emp)request.getSession().getAttribute("EMP_USER");
			Notice noticeData = noticReadService.getNotice(no,false);

			if(!canModify(empUser, noticeData)) {
				if(empUser.getGrade()==999) {
					canModify(empUser, noticeData);
				}else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;}
			}
			
			Notice modReq = 
					new Notice(empUser.getEmpno(),no,noticeData.getTitle(), noticeData.getContent());
			
		
			//3.Model & 4.View -p670 53라인
			request.setAttribute("modReq", modReq);
			request.setAttribute("no", no);
			request.setAttribute("rowSize", rowSize);
			request.setAttribute("pageNo", pageNo);
			
			//4.View
			return FORM_VIEW;
		
		}catch(NoticeNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);//404에러
			return null;
		}
	}//processForm()끝
	
	
	//수정처리-p670 66라인
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Emp empUser = (Emp)request.getSession().getAttribute("EMP_USER");

		String rowSizeStr = request.getParameter("rowSize");//작성자명
		int rowSize=Integer.parseInt(rowSizeStr);
		String strNo =request.getParameter("no");
		int no = Integer.parseInt(strNo);
		String strPageNo =request.getParameter("pageNo");
		int pageNo=Integer.parseInt(strPageNo);
	
		String title = request.getParameter("title");//수정할 제목
		String content = request.getParameter("content");//수정할 내용
		//String empno = request.getParameter("empno");//작성자명
		
		
				
		//아래 ModifyRequest(로그인한userid,글번호,db의작성자명,db의title,db의내용)
		Notice modReq = 
				new Notice(empUser.getEmpno(),no,title,content);
		
	
		//3.Model & 4.View -p670 53라인
		request.setAttribute("modReq", modReq);
		request.setAttribute("no", no);
		request.setAttribute("rowSize", rowSize);
		request.setAttribute("pageNo", pageNo);
		
		//유효성검사-P670 77라인
		Map<String,Boolean> errors = new HashMap<String,Boolean>();
		request.setAttribute("errors",errors);//p670 78라인
		modReq.validate(errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
	
		try {//P670 83라인
			//3.비즈니스로직수행=>db에 update진행해라
			noticeModifyService.noticeModify(modReq,empUser.getGrade());
			
			//4.View 지정 //업뎃후 성공화면을 해줘도되고 상세보기로 가도된다. 그건 필요에 따라 설정하자
			return "/view/notice/noticeModifyS.jsp";
			
		}catch(NoticeNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);//404에러
			return null;
		}catch (PermissionDeniedException e) {//p671 89라인
			response.sendError(HttpServletResponse.SC_FORBIDDEN);//403에러
			return null;
		}
		
	}//processSubmit()끝	

	//수정권한체크-p670 61라인
	/* 파라미터
	  Emp empUser: 로그인한유저정보
	  Notice noticeData
	 * 리턴유형
	  boolean : 수정가능하면 true리턴, 그렇지않으면 false리턴 */
	private boolean canModify(Emp empUser,Notice noticeData) {
		//로그인한유저정보에서 id를 가져오기
		int userEmpno = empUser.getEmpno();
		String userNo=String.valueOf(userEmpno);
		
		//작성자정보에서 id를 가져오기
		int writerEmpno = noticeData.getEmpno();
		String writerNo=String.valueOf(writerEmpno);
		
		//"로그인한userid".equals("작성자id")
		return userNo.equals(writerNo);
	}//canModify()끝
	
	
}
