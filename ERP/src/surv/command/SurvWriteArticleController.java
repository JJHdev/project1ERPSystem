package surv.command;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emp.model.Emp;
import mvc.command.CommandController;
import surv.model.SurvArticle;
import surv.model.SurvArticleContent;
import surv.service.SurvWriteArticleService;
import surv.service.SurvWriteRequest;

public class SurvWriteArticleController implements CommandController {
	private static final String FORM_VIEW = "/view/survArticle/survWriteArticle.jsp";
	private SurvWriteArticleService survWriteArticleService = new SurvWriteArticleService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8"); 
		//폼의 요청방식에 따라 쓰기폼보여줘 요청과  쓰기처리요청을 구분
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response);//쓰기폼보여줘
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			System.out.println("POST요청");
			return processSubmit(request,response);//쓰기처리요청
		}else {
			/* 참고. 
			 * 상태코드 => SC_OK
			 * 200(성공): 서버가 요청을 제대로 처리했다는 뜻이다. 
			 * 이는 주로 서버가 요청한 페이지를 제공했다는 의미로 쓰인다.
			 * 
			 * 상태코드 => SC_METHOD_NOT_ALLOWED
			 * 405(허용되지 않는 메소드): 
			 * 요청에 지정된 방법을 사용할 수 없다. 
			 * 예를 들어 POST 방식으로 요청을 받는 서버에 GET 요청을 보내는 경우, 
			 * 또는 읽기 전용 리소스에 PUT 요청을 보내는 경우에 이 코드를 제공한다.*/
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}//process끝
	//쓰기폼으로 이동-p641 31라인
		private String processForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
			//로그인한 유저정보는 세션에서 받자
			Emp authUser = loginedUser(request);
			request.setAttribute("EMP_USER", authUser);
			System.out.println("authUser"+authUser);
			return FORM_VIEW;
		}	

		//로그인한 유저정보는 세션에서 받자
		public Emp loginedUser(HttpServletRequest request) {
			/*--session에 저장된 로그인한 user정보
			class User {
			   private int    memberno;	//회원번호
			   private String memberid;	//id
			   private String membername;	//이름
			   private int    grade;   //회원등급.기본1. 1(정상),2(강퇴),3(탈퇴),4(휴면),999(관리자) }
			session.setAttribute("AUTHUSER",user);*/
			Emp authUser = (Emp)request.getSession().getAttribute("EMP_USER");
			return authUser;
		}
		
		//쓰기처리-p641 35라인
		private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			//1.파라미터받기
			//String writer_name = request.getParameter("sName");//작성자명
			String rowSize=request.getParameter("rowSize");//1페이지당 보여줄 게시글수
			
			//로그인한 유저정보는 세션에서 받자
			Emp authUser = loginedUser(request);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println("authUser="+authUser);
			//유효성검사-P641 41라인
			String writeTitle = request.getParameter("sTitle");
			String writeContent = request.getParameter("sContent");
			String writeToday = request.getParameter("today");
			String writeResdate = request.getParameter("surv_resdate");
			String writeName = request.getParameter("sName");
			
			Date regDate = sdf.parse(writeToday);
			Date resDate = sdf.parse(writeResdate);
			
			SurvArticle writeSurv = new SurvArticle();
			
			writeSurv.setSurv_tit(writeTitle);
			writeSurv.setSurv_writer(writeName);
			writeSurv.setSurv_regdate(regDate);
			writeSurv.setSurv_resdate(resDate);
			writeSurv.setSurv_content(writeContent);
			writeSurv.setSurvWriter_no(authUser.getEmpno());
			
			SurvArticleContent writeSurvCont = new SurvArticleContent();
			writeSurvCont.setSurvWriter_no(authUser.getEmpno());
			writeSurvCont.setSurv_tit(writeTitle);
			writeSurvCont.setSurv_content(writeContent);
			writeSurvCont.setSurv_resdate(resDate);
			writeSurvCont.setSurv_desc(writeContent);
		
			
			Map<String,Boolean> errors = new HashMap<String,Boolean>();
			request.setAttribute("errors",errors);//p641 37라인
			
			writeSurv.validate(errors);
			System.out.println("writeSurv="+writeSurv);
			
			if(!errors.isEmpty()) {
				System.out.println("문제발생");
				return FORM_VIEW;
			}

			//2.비즈니스로직<->Service<->DAO<->DB
			//파라미터  WriteRequest:Writer(로그인한유저id,로그인한유저명),입력제목,입력내용
			//리턴타입 Integer : article테이블에 입력된 글번호
			
			int newArticleNo = survWriteArticleService.write(writeSurv,writeSurvCont);
			request.setAttribute("newArticleNo", newArticleNo);
			request.setAttribute("rowSize", rowSize);//?????
			
			return "/view/survArticle/newArticleSuccess.jsp";
			
		}//processSubmit()끝
		
		//리턴유형 WriteRequest:Writer(로그인한유저id,로그인한유저명),입력제목,입력내용
		
		
}



