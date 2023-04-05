package hpdesk.command;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.LoginService;
import emp.model.Emp;
import hpdesk.model.HPDeskWriter;
import hpdesk.service.InsertHPDeskRequest;
import hpdesk.service.InsertHPDeskService;
import mvc.command.CommandController;

//이 클래스는 쓰기폼보여주기 요청 및 쓰기 처리요청 담당 컨트롤러
//요청주소 http://localhost/hpdesk/inserthpdesk.aa
public class InsertHPDeskController implements CommandController {
	
	private static final String FORM_VIEW="/view/hpdesk/insertHPDeskForm.jsp"; 
	
	private InsertHPDeskService insertHPDeskService=new InsertHPDeskService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("InsertHPDeskController.process()호출");
		
		//폼의 요청방식에 따라 쓰기폼보여줘 요청과 쓰기처리요청을 구분
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response); //쓰기폼 보여줘			
		}else if(request.getMethod().equalsIgnoreCase("POST")){
			return processSubmit(request,response);//쓰기처리요청
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); //405에러
			return null;
		}

	}

	//쓰기폼으로 이동
	private String processForm(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//로그인한 유저정보는 세션에서 받자
		Emp user=loginedUser(request);
		request.setAttribute("EMP_USER",user);
		request.setAttribute("rowSize", 5); //1페이지당 보여줄 게시글수
		return FORM_VIEW;
	}

	//로그인한 유저정보
	private Emp loginedUser(HttpServletRequest request) {
		//empno ename grade
		Emp user = (Emp)request.getSession().getAttribute("EMP_USER");
		return user;
	}
	//쓰기처리요청
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String rowSize=request.getParameter("rowSize");//1페이지당 보여줄 게시글수
		System.out.println("rowSize="+rowSize);
		//로그인한 유저정보
		Emp user=loginedUser(request);
		//유효성 검사
		Map<String,Boolean> errors=new HashMap<String,Boolean>();
		request.setAttribute("errors", errors);
		InsertHPDeskRequest insertHPDeskRequest=createInsertHPDeskRequest(user,request);
		insertHPDeskRequest.validate(errors);

		if(!errors.isEmpty()) { 
			return FORM_VIEW;			
		}
		//파라미터 HPDeskRequest  (Integer hdno, String hdtitle, HPDeskWriter hpdeskWriter, Date hddate, String isshow, String hdcontent, int hdcheck)
		//리턴타입 Integer: hpdesk테이블에 입력된 글번호	
		int newHPDeskNo =insertHPDeskService.insertHPDesk(insertHPDeskRequest);
		request.setAttribute("newHPDeskNo", newHPDeskNo);
		request.setAttribute("rowSize", rowSize);
		return "/view/hpdesk/Success.jsp"; //입력성공시 
	}
	private InsertHPDeskRequest createInsertHPDeskRequest(Emp user, HttpServletRequest request) {
		return new InsertHPDeskRequest(new HPDeskWriter(user.getEname(),user.getEmpno()),
				request.getParameter("hdtitle"),
				request.getParameter("hdcontent")
				);
	}
}
