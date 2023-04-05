package mypage.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandController;
import mypage.Service.DeleteEmpService;
import mypage.Service.DeleteShowService;
import mypage.Service.MypageSearchService;
import mypage.Service.UpdateService;
import mypage.deletemodel.MypageDeletePage;

public class DeleteShowController implements CommandController {

	private DeleteShowService deleteShowService =
			new DeleteShowService();
	private DeleteEmpService deleteEmpService = new DeleteEmpService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
		
		String op = request.getParameter("operate");
		try{
			if(op.equals("삭제하기")) {
				String[] strdelempno =request.getParameterValues("delempno");
				int[] delempno = new int[strdelempno.length];
				int i = 0;
				for (String var: strdelempno) {
					delempno[i] = Integer.parseInt(var);
					i++;
				}
				//2. 비즈니스로직
				int cnt = deleteEmpService.deleteEmp(delempno);
				//3. modle
				request.setAttribute("cnt",cnt);
				//4.view
				return "/view/deletemypage/mypagedeleteresult.jsp";
			}else {
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
				String strSearchEno = request.getParameter("mypagesearch");
				
				if(strSearchEno==null||strSearchEno=="") {
					//2.비즈니스로직수행<->Service<->DAO<->DB
					MypageDeletePage deletepage  = deleteShowService.getDeletePage(pageNo,rsize);
					//3.Model
					request.setAttribute("rsize",rsize);
					request.setAttribute("deletepage", deletepage);
					//4.View
					return "/view/deletemypage/mypagedelete.jsp";
				} else {
					int searchEno = 0;
					if(strSearchEno!=null) {
						searchEno = Integer.parseInt(strSearchEno);			
					}
					MypageDeletePage deletepage = deleteShowService.getDeleteSearchEno(1,1,searchEno);
					request.setAttribute("deletepage", deletepage);
					request.setAttribute("rsize",1);
					return "/view/deletemypage/mypagedelete.jsp";
				}
			}
		}catch(NullPointerException e){
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
			String strSearchEno = request.getParameter("mypagesearch");
			if(strSearchEno==null||strSearchEno=="") {
				//2.비즈니스로직수행<->Service<->DAO<->DB
				//p652 22라인
				//ArticlePage: 목록+페이징처리 관련 내용
				MypageDeletePage deletepage  = deleteShowService.getDeletePage(pageNo,rsize);
				//3.Model
				request.setAttribute("rsize",rsize);
				request.setAttribute("deletepage", deletepage);
				//4.View
				return "/view/deletemypage/mypagedelete.jsp";
			} else {
				int searchEno = 0;
				if(strSearchEno!=null) {
					searchEno = Integer.parseInt(strSearchEno);			
				}
				MypageDeletePage deletepage = deleteShowService.getDeleteSearchEno(1,1,searchEno);
				request.setAttribute("deletepage", deletepage);
				request.setAttribute("rsize",1);
				return "/view/deletemypage/mypagedelete.jsp";
			}
		}
	}
}
