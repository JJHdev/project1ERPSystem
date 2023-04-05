package surv.command;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import surv.service.PermissionDeniedException;
import surv.service.SurvArticleContentNotFoundException;
import surv.service.SurvArticleData;
import emp.model.Emp;
import mvc.command.CommandController;
import surv.service.SurvArticleNotFoundException;
import surv.service.SurvModifyArticleService;
import surv.service.SurvModifyRequest;
import surv.service.SurvReadArticleService;

public class SurvModifyArticleController implements CommandController {

	private static final String FORM_VIEW = "/view/survArticle/survModifyForm.jsp";
	
	//상세보기 위한 서비스
	private SurvReadArticleService survReadArticleService = new SurvReadArticleService();
	
	//수정처리를위한 서비스
	
	private SurvModifyArticleService survModifyArticleService = new SurvModifyArticleService();
	
	
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//폼의 요청방식에 따라 수정폼보여줘 요청과  수정처리요청을 구분
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response);//수정폼보여줘
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response);//수정처리요청
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


	//(해당게시글의 정보가 출력되어있는)수정폼으로 이동-p669 38라인
	private String processForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException, SurvArticleContentNotFoundException {
		System.out.println("modify processForm접근");
		try {
			String strNo = request.getParameter("no");//글번호
			if(strNo==null) {
				throw new RuntimeException();
			}
			int no = Integer.parseInt(strNo);//상세조회할글번호
			System.out.println("no="+no);
			
			//만약 파라미터pageNo(즉,요청페이지)가 null이면 요청페이지를 1로 설정
			String strPageNo = request.getParameter("pageNo"); //보고싶은페이지
			int pageNo = 1;   
			if(strPageNo!=null) {
				pageNo = Integer.parseInt(strPageNo);			
			}
			
			//만약 파라미터rowSize(페이지당게시글수)가 null이면 페이지당게시글수를 3으로 설정
			String strRowSize = request.getParameter("rowSize"); //한 페이지당 보여줄 게시물수
			int rsize = 3;
			if(strRowSize!=null) {
				rsize = Integer.parseInt(strRowSize);			
			}	
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//no=상세조회할글번호&pageNo=요청페이지&rowSize=1페이지당게시글수
			//만약 파라미터no(즉, 상세조회할글번호)가 null이면 RuntimeException발생
			
			String mordiTitle = request.getParameter("sTitle");//수정할 제목
			String mordiContent = request.getParameter("sContent");//수정할 질문 제목
			//String mordiResdate = request.getParameter("surv_resdate");//수정할 마감 날짜
			Date mordiResdate = (Date) request.getAttribute("surv_resdate");
			
			System.out.println(mordiResdate);
			//2.비즈니스로직수행<->Service<->DB
			/*파라미터 
			  int no : 상세조회할 글번호
		  	  boolean increseReadCount:true(이면 조회수증가)
		  	  여기에서는 수정을 위해 상세보기를 진행하므로 조회수 증가를 하지않기 위해 false넘긴다
			 *리턴유형 
			  SurvArticleData: survarticle테이블과 survqust테이블 관련 데이터  */  
			SurvArticleData articleData = survReadArticleService.getArticle(no,false);
			
			
			//로그인한 회원은 자신의 글에 한하여 내용을 수정할 수 있어야 한다.
			//조건1)로그인했니?
			//로그인한 유저정보는 세션에서 받자
			/*--session에 저장된 로그인한 user정보
			class User {
			   private int    memberno;	//회원번호
			   private String memberid;	//id
			   private String membername;	//이름
			   private int    grade;   //회원등급.기본1. 1(정상),2(강퇴),3(탈퇴),4(휴면),999(관리자) }
			session.setAttribute("EMP_USER",user);*/
			Emp empUser = (Emp)request.getSession().getAttribute("EMP_USER");
			
			/*조건2)로그인한 회원은 자신의 글이니?
			    =>로그인한 user의 id와 작성자의 id가 일치?*/
			/*if(!canModify(authUser, articleData)) {//수정불가이면
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}*/
			/*참고 
			 * HttpServletResponse.SC_FORBIDDEN: 403에러
			 서버가 허용하지 않는 웹 페이지나 미디어를 사용자가 요청할 때 
			 웹 서버가 반환하는 HTTP 상태 코드이다. 
			 다시 말해, 클라이언트가 서버에 도달할 수 있어도 
			 서버가 페이지 접근 허용을 거부했다는 것을 뜻한다*/
			//
			//아래 ModifyRequest(아이디,번호, 작성자명, 제목, 질문 제목 ,마감날짜)
			SurvModifyRequest modReq =	new SurvModifyRequest(empUser.getEmpno(), 
							no,
							articleData.getArticle().getSurv_writer(),
							articleData.getArticle().getSurv_tit(), 
							articleData.getSurvContent().getSurv_content(),
							articleData.getArticle().getSurv_resdate());
			System.out.println("modReq"+modReq);
			//3.Model & 4.View -p670 53라인
			request.setAttribute("modReq", modReq);
			//4.View
			return FORM_VIEW;
		}catch(SurvArticleNotFoundException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);//404에러
			return null;
		}
	}


	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws SurvArticleNotFoundException, IOException, ParseException {
		request.setCharacterEncoding("UTF-8");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//no=상세조회할글번호&pageNo=요청페이지&rowSize=1페이지당게시글수
			//만약 파라미터no(즉, 상세조회할글번호)가 null이면 RuntimeException발생
			String strNo = request.getParameter("no");//글번호
			if(strNo==null) {
				throw new RuntimeException();
			}
			int no = Integer.parseInt(strNo);//상세조회할글번호
			System.out.println("no="+no);
			
			//만약 파라미터pageNo(즉,요청페이지)가 null이면 요청페이지를 1로 설정
			String strPageNo = request.getParameter("pageNo"); //보고싶은페이지
			int pageNo = 1;   
			if(strPageNo!=null) {
				pageNo = Integer.parseInt(strPageNo);			
			}
			
			//만약 파라미터rowSize(페이지당게시글수)가 null이면 페이지당게시글수를 3으로 설정
			String strRowSize = request.getParameter("rowSize"); //한 페이지당 보여줄 게시물수
			int rsize = 3;
			if(strRowSize!=null) {
				rsize = Integer.parseInt(strRowSize);			
			}		
			request.setCharacterEncoding("UTF-8");
			String title = request.getParameter("title");//수정할 제목
			String content = request.getParameter("content");//수정할 내용
			String mordiResdate = request.getParameter("surv_resdate");//수정할 마감 날짜
			Date resDate =sdf.parse(mordiResdate);
			//로그인한 유저정보는 세션에서 받자
			/*--session에 저장된 로그인한 user정보
			class User {
			   private int    memberno;	//회원번호
			   private String memberid;	//id
			   private String membername;	//이름
			   private int    grade;   //회원등급.기본1. 1(정상),2(강퇴),3(탈퇴),4(휴면),999(관리자) }
			session.setAttribute("EMP_USER",user);*/
			Emp authUser = (Emp)request.getSession().getAttribute("EMP_USER");
			
					
			//아래 ModifyRequest(로그인한userid,글번호,db의작성자명,db의title,db의내용)
			SurvModifyRequest modReq = 
					new SurvModifyRequest(authUser.getEmpno(), 
							no,
							authUser.getEname(),
							title, 
							content,resDate);
			System.out.println();
			//3.Model & 4.View -p670 53라인
			request.setAttribute("modReq", modReq);
			
			//유효성검사-P670 77라인
			Map<String,Boolean> errors = new HashMap<String,Boolean>();
			request.setAttribute("errors",errors);//p670 78라인
			modReq.validate(errors);
			if(!errors.isEmpty()) {
				return FORM_VIEW;
			}
		
			try {//P670 83라인
				//3.비즈니스로직수행=>db에 update진행해라
				//파라미터 ModifyRequest modReq: 글 수정을 위한    수정하는 사용자id,수정할 글번호,수정할 제목,수정할 내용
				survModifyArticleService.modfiy(modReq);
				
				//4.View-P670 85라인
				return "/view/survArticle/modifySuccess.jsp";  //수정성공페이지
			}catch(SurvArticleNotFoundException e) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);//404에러
				return null;
			}

		}//processSubmit()끝	

	}





