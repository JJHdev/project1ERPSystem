package comment.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.model.Comment;
import comment.service.CommentWriteService;
import emp.model.Emp;
import mvc.command.CommandController;
import notice.model.Notice;
import notice.service.NoticeWriteService;

public class CommentWriteController implements CommandController {


	private static String FORM_VIEW= "/view/comment/commentWrite.jsp";
	private CommentWriteService commentWriteService = new CommentWriteService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		
		//폼의 요청방식에 따라 수정폼보여줘 요청과  수정처리요청을 구분
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response);//쓰기폼보여줘
		
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response);//쓰기폼요청
		
		}else {
	
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}//process끝
	
	
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		String rowSizeStr = request.getParameter("rowSize");//작성자명
		int rowSize=Integer.parseInt(rowSizeStr);
		String strNo =request.getParameter("no");
		int no = Integer.parseInt(strNo);
		String strPageNo =request.getParameter("pageNo");
		int pageNo=Integer.parseInt(strPageNo);
		
		Emp empUser = logingUser(request);
		request.setAttribute("EMP_USER", empUser);
		request.setAttribute("no", no);
		request.setAttribute("rowSize", rowSize);
		request.setAttribute("pageNo", pageNo);
		
		
		return FORM_VIEW;
	}//processForm()끝
	
	public Emp logingUser(HttpServletRequest request) {
		
		Emp empUser = (Emp)request.getSession().getAttribute("EMP_USER");
		return empUser;
	}
	
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.파라미터받
		String rowSizeStr = request.getParameter("rowSize");//작성자명
		int rowSize=Integer.parseInt(rowSizeStr);
		String strNo =request.getParameter("no");
		int no = Integer.parseInt(strNo);
		String strPageNo =request.getParameter("pageNo");
		int pageNo=Integer.parseInt(strPageNo);
		Emp empUser = logingUser(request);
		
		
		//2.비즈니스로 <-> Service <-> DAO <-> DB
		
		//유효성검사
		Map<String,Boolean> errors = new HashMap<String,Boolean>();
		request.setAttribute("errors",errors);//p670 78라인
		
		Comment commentReq=createWriteRequest(empUser,request);
		commentReq.validate2(errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		int newCommentNo = commentWriteService.write(commentReq,no);
		
		
		
		//3.모델처리
		request.setAttribute("cnt", newCommentNo);
		request.setAttribute("rowSize", rowSize);
		request.setAttribute("no",no);
		request.setAttribute("pageNo", pageNo);
		
		return "/view/comment/commentWriteS.jsp";
	}//processSubmit()끝	
	
	//리턴유형 WriteRequest 는 Writer(로그인한 유저아이디,유저이름),유저가 입력한 제목, 유저가 입력한 내용을 담고있다
	private Comment createWriteRequest(Emp empUser, HttpServletRequest request) {
		
		return new Comment(empUser.getEmpno(),//Writer객체
						request.getParameter("c_content")); //WriteRequest객체 리턴~
		//위에 1번에서 파라미터 받기대신 여기서 다이렉트로 받으면서 WriteRequest 객체 만듬
	}
	

}