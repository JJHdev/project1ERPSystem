package surv.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emp.model.Emp;
import mvc.command.CommandController;
import surv.service.SurvArticleContentNotFoundException;
import surv.service.SurvResultArticleService;
import surv.service.SurvResultData;

public class SurvResultController implements CommandController {
	private SurvResultArticleService resultArticleService
	= new SurvResultArticleService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
	//로그인한 유저정보는 세션에서 받자
		/*--session에 저장된 로그인한 user정보
		class User {
		   private int    memberno;	//회원번호
		   private String memberid;	//id
		   private String membername;	//이름
		   private int    grade;   //회원등급.기본1. 1(정상),2(강퇴),3(탈퇴),4(휴면),999(관리자) }
		session.setAttribute("AUTHUSER",user);*/
		Emp authUser = (Emp)request.getSession().getAttribute("EMP_USER");
		System.out.println("processForm 접근");
		//로그인한 유저정보는 세션에서 받자
		request.setAttribute("EMP_USER", authUser);
		
		String resultValue = request.getParameter("survey1");
		String strNo = request.getParameter("no");
		
		int no = Integer.parseInt(strNo);
		int pageNo = 1;   
		
		//2.비즈니스로직<->Service<->DAO<->DB
		
		SurvResultData survResultData = resultArticleService.getResult(no,true);
		//3.Model
		String rowSize=request.getParameter("rowSize");//1페이지당 보여줄 게시글수
		
		//로그인한 유저정보는 세션에서 받자
		
		//4.View
		request.setAttribute("survResultData", survResultData);
	
		return "/view/survArticle/survResultReponse.jsp";
	}

}
