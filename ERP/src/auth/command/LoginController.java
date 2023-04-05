package auth.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.service.LoginFailException;
import auth.service.LoginService;
import emp.model.Emp;
import mvc.command.CommandController;

public class LoginController implements CommandController {

	//필드
	private static final String FORM_VIEW = "view/emp/empLoginForm.jsp";
	
	//로그인컨트롤러에서 로그인서비스로 보내기위해 필요한 객체생성
	private LoginService loginservice = new LoginService(); 
	//생성자
	//메서드
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)throws Exception{
			System.out.println("LoginController의 process()진입~~");

			//폼의 요청방식에 따라 회원가입폼보여줘 요청과  가입처리요청을 구분
			if(request.getMethod().equalsIgnoreCase("GET")) {
				return processForm(request,response);//회원가입폼보여줘
			
			}else if(request.getMethod().equalsIgnoreCase("POST")) {
				return processSubmit(request,response);//가입처리요청
			
			}else {
				response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
				return null;
			}
		}//process()
		
		//회원가입폼보여줘 요청.p598 31라인
		private String processForm(HttpServletRequest request, HttpServletResponse response) {
			return FORM_VIEW;
		}
		
		private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException {
			System.out.println("processSubmit()-post방식-진입");
			//1. 핸들러의 파라미터 얻기
			
			String empno1=trim(request.getParameter("empno"));
			String emppwd=trim(request.getParameter("emppwd"));
			
			Map<String, Boolean> errors = new HashMap<>();
			request.setAttribute("errors", errors);
			
			if(empno1==null||empno1.isEmpty()) {
				errors.put("empno",Boolean.TRUE);
			} 
			if(emppwd==null||emppwd.isEmpty()){
				errors.put("emppwd",Boolean.TRUE);
			}
			if(!errors.isEmpty()) {
				
				return FORM_VIEW;
			}
		try {
			int empno= Integer.parseInt(empno1);
			
			Emp User = loginservice.login(empno,emppwd);
			HttpSession session = request.getSession();
			session.setAttribute("EMP_USER",User);
			response.sendRedirect(request.getContextPath()+"/notice/list.aa");
			return null;
			//로그인에 성공하면 로그인한 회원의 정보를 session에 담는다
			//model처리 >> 
			//request객체.setAttribute("속성값",Object value)
			//session객체.setAttribute("속성값",Object value)
		}catch(Exception e){
			errors.put("idOrPwNotMatch",Boolean.TRUE);
			return FORM_VIEW;
		}
		}//processSubmit 끝
		
		// 문자열의 좌우공백제거 -p607 64라인
		private String trim(String str) {
			return (str==null)? null:str.trim();
		}//trim 끝
	}

