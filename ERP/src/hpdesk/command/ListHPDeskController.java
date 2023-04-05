package hpdesk.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emp.model.Emp;
import hpdesk.model.HPDesk;
import hpdesk.model.HPDeskWriter;
import hpdesk.service.HPDeskPage;
import hpdesk.service.InsertHPDeskRequest;

import hpdesk.service.ListHPDeskService;
import mvc.command.CommandController;

//이 클래스는 hpdesk테이블의 모든 목록보기 요청에 따라 호출되는 controller이다
//요청주소 http://localhost/hpdesk/listHPDesk.aa
public class ListHPDeskController implements CommandController {
	private static final String FORM_VIEW="/view/hpdesk/listHPDeskForm.jsp"; 
	private ListHPDeskService listHPDeskService =new ListHPDeskService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ListHPDeskService-process()진입");

				String strPageNo=request.getParameter("pageNo"); //보고싶은페이지
				int pageNo=1;
				if(strPageNo!=null) {
					pageNo =Integer.parseInt(strPageNo);		
				} 
				String strRowSize=request.getParameter("rowSize"); //한 페이지당 보여줄게
				int rsize=5;
				if(strRowSize!=null) {
					rsize =Integer.parseInt(strRowSize); // int로 형변환			
				}
				//HPDeskPage: 목록+페이징처리 관련 내용
				HPDeskPage hpdeskPage= listHPDeskService.getHPDeskPage(pageNo, rsize); 
				//로그인
				Emp user = (Emp)request.getSession().getAttribute("EMP_USER");
				request.setAttribute("hpdeskPage",hpdeskPage);
				request.setAttribute("rsize",rsize);

		return FORM_VIEW;
	}
}
