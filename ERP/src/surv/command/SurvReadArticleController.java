package surv.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandController;
import surv.service.SurvArticleData;
import surv.service.SurvReadArticleService;

//p659
//이 클래스는 상세조회를 담당하는 컨트롤러이다
//요청주소  http:/localhost/article/read.do?no=상세조회할글번호&pageNo=요청페이지&rowSize=1페이지당게시글수
public class SurvReadArticleController implements CommandController {
       
	private SurvReadArticleService readService =
			new SurvReadArticleService();
	
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//컨트롤러가 해야할 일
		//1.파라미터받기
		//no=상세조회할글번호&pageNo=요청페이지&rowSize=1페이지당게시글수
		//만약 파라미터no(즉, 상세조회할글번호)가 null이면 RuntimeException발생
		String strNo = request.getParameter("no");
		if(strNo==null) {
			throw new RuntimeException();
		}
		int no = Integer.parseInt(strNo);//상세조회할글번호
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
		
		//2.비즈니스로직 -> Service->DAO->DB
		/*파라미터 
		  int no : 상세조회할 글번호
	  	  boolean increseReadCount:true(이면 조회수증가)
		*리턴유형 
		  ArticleData: article테이블과 article_content테이블 관련 데이터  */  
		SurvArticleData articleData = readService.getArticle(no,true);
		//3.Model
		//릴레이용 pageNo=요청페이지&rowSize=1페이지당게시글수
		request.setAttribute("articleData", articleData);//article테이블과 article_content테이블 관련 데이터
		request.setAttribute("pageNo", pageNo);//요청페이지
		request.setAttribute("rowSize", rsize);//1페이지당게시글수
			
		//4.View
		return "/view/survArticle/survReadArticle.jsp";
	}

}
