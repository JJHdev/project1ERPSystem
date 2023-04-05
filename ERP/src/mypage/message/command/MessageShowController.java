package mypage.message.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emp.model.Emp;
import emp.model.MypageDbEmp;
import mvc.command.CommandController;
import mypage.message.dto.MessagePage;
import mypage.message.service.DeleteMessageEmpService;
import mypage.message.service.ListMessageService;

public class MessageShowController implements CommandController {

	private static final String FORM_VIEW = "/view/message/mypageshowmessage.jsp";
	
	DeleteMessageEmpService deleteMessageEmpService =	new DeleteMessageEmpService();
	ListMessageService listMessageService=new ListMessageService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)throws Exception{
		if(request.getMethod().equalsIgnoreCase("GET")) {
			String searchEno =request.getParameter("mypagesearch");
			if(searchEno==null||searchEno.equals("0")){
				return processForm(request,response);//처음 접근, 페이지이동
			}else {
				return processSubmit(request,response);//사원번호 찾을시
			}
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			String op = request.getParameter("operate");
			if(op.equals("삭제하기")) {
				return processDelete(request,response);//삭제하기버튼 클릭시
			}else {
				return processSubmit(request,response);//사원번호 찾을시
			}//가입처리요청
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}//process()
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		Emp user = (Emp)request.getSession().getAttribute("EMP_USER");
		MessagePage messgaePage  =null;
		MypageDbEmp mypageDbEmp =null;
		//컨트롤러가 해야 할 일
		//1.파라미터받기
		String strPageNo = request.getParameter("pageNo"); //보고싶은페이지
		int pageNo = 1;   
		if(strPageNo!=null) {
			pageNo = Integer.parseInt(strPageNo);			
		}
		String strRowSize = request.getParameter("rowSize"); //한 페이지당 보여줄 게시물수
		int rsize = 3;
		if(strRowSize!=null) {
			rsize = Integer.parseInt(strRowSize);			
		}
		//2.비즈니스로직수행<->Service<->DAO<->DB
		messgaePage  = listMessageService.getMessagePage(user.getEmpno(),pageNo,rsize);		
		//3.Model
		request.setAttribute("messgaePage",messgaePage);
		request.setAttribute("rsize",rsize);
		//4.View
		return FORM_VIEW;
	}
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
					Emp user = (Emp)request.getSession().getAttribute("EMP_USER");
					MessagePage messgaePage  =null;
					MypageDbEmp mypageDbEmp =null;
					//파라미터 값 받고 형변환 하기.
					String strPageNo = request.getParameter("pageNo"); //보고싶은페이지
					int pageNo = 1;   
				if(strPageNo!=null) {
					pageNo = Integer.parseInt(strPageNo);			
				}
					String strRowSize = request.getParameter("rowSize"); //한 페이지당 보여줄 게시물수
					int rsize = 3;
				if(strRowSize!=null) {
					rsize = Integer.parseInt(strRowSize);			
				}
					String strmypagesearch = request.getParameter("mypagesearch"); //찾을 사원번호
					int mypagesearch = 0;   
				if(strmypagesearch!=null) {
					mypagesearch = Integer.parseInt(strmypagesearch);			
				}
					//비즈니스로직 수행
					messgaePage  = listMessageService.getMessageSearchPage(user.getEmpno(),pageNo,rsize,mypagesearch);		
					request.setAttribute("messgaePage", messgaePage);
					request.setAttribute("rsize",rsize);
				return FORM_VIEW;
	}
	private String processDelete(HttpServletRequest request, HttpServletResponse response) {
		 String[] strdelemessageno =request.getParameterValues("delemessageno");
		 for(int i=0 ; i>strdelemessageno.length ; i++ ) {
		 }
		 int[] delemessageno = new int[strdelemessageno.length];
		 int i = 0;
	        for (String var: strdelemessageno) {
	        	delemessageno[i] = Integer.parseInt(var);
	        	i++;
	        }
		//2. 비즈니스로직
		int cnt = deleteMessageEmpService.deleteMessageEmp(delemessageno);
		//3. modle
		request.setAttribute("cnt",cnt);
		//4.view
		return "/view/message/mypagemessagedeleteresult.jsp";
	}
}
