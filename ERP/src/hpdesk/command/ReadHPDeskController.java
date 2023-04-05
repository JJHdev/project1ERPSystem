package hpdesk.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emp.model.Emp;
import hpdesk.service.HPDeskData;
import hpdesk.service.ReadHPDeskService;
import mvc.command.CommandController;
//상세조회 담당 컨트럴러
//요청주소 http://localhost/hpdesk/readhpdesk.aa
public class ReadHPDeskController implements CommandController {
	private ReadHPDeskService readHPDeskService=new ReadHPDeskService();
	private HPDeskData readReq;
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//no=상세조회할글번호&pageNo=요청페이지&rowSize=1페이지당게시글수
		//만약 파라미터no(즉, 상세조회할글번호)가 null이면 RuntimeException발생
		String strNo = request.getParameter("hdno");
		if(strNo==null) {
			throw new RuntimeException();
		}
		int hdno = Integer.parseInt(strNo);//상세조회할글번호
		String nempno=request.getParameter("empno");//사원번호
		int empno=Integer.parseInt(nempno);
		//만약 파라미터pageNo(즉,요청페이지)가 null이면 요청페이지를 1로 설정
		String strPageNo = request.getParameter("pageNo"); //보고싶은페이지
		int pageNo = 1;   
		if(strPageNo!=null) {
			pageNo = Integer.parseInt(strPageNo);			
		}
		//만약 파라미터rowSize(페이지당게시글수)가 null이면 페이지당게시글수를 5으로 설정
		String strRowSize = request.getParameter("rowSize"); //한 페이지당 보여줄 게시물수
		int rsize = 5;
		if(strRowSize!=null) {
			rsize = Integer.parseInt(strRowSize);			
		}
		//파라미터 int no
		//리턴유형  ArticleData: article테이블과 article_content테이블 관련 데이터  
		HPDeskData hpdeskData = readHPDeskService.getHPDesk(hdno);
		//로그인한 유저정보
		Emp user = (Emp)request.getSession().getAttribute("EMP_USER");

	    //로그인한 유저의 사원번호와 작성자의 사원번호가 일치 | 관리자인지
		if(!canRead(user, hpdeskData)) {
			if(user.getGrade()==999) {
				canRead(user, hpdeskData);
			}else {
				return request.getContextPath()+"/view/hpdesk/fail.jsp";
			}
		}
		//릴레이용 pageNo=요청페이지&rowSize=1페이지당게시글수
		request.setAttribute("hpdeskData", hpdeskData);//article테이블과 article_content테이블 관련 데이터
		request.setAttribute("pageNo", pageNo);//요청페이지
		request.setAttribute("rowSize", rsize);//1페이지당게시글수
		return "/view/hpdesk/readHPDeskForm.jsp";
	}
		private boolean canRead(Emp user,  HPDeskData hpdeskData) {
			//로그인한 유저정보에서 empno가져오기(사원번호)
			int empno = user.getEmpno();
			//작성자정보에서 empno가져오기
			int readempno=hpdeskData.getHpdesk().getHpdeskWriter().getEmpno();
			//로그인한 empno와 작성자 readempno가 동일
			return empno==readempno;
		}
}
