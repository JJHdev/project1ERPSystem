package mypage.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emp.command.MypageInfoController;
import emp.model.Emp;
import mvc.command.CommandController;
import mypage.Error.EmpNotFoundException;
import mypage.Error.InvalidException;
import mypage.Service.UpdateService;

public class MypageUpdateSalController implements CommandController {

	private static final String FORM_VIEW = "/view/updatemypage/changeSalForm.jsp";
	private UpdateService updateService = new UpdateService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//폼의 요청방식에 따라 비변변경폼보여줘 요청과  비번변경처리요청을 구분
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response);//비변변경폼보여줘
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response);//비번변경처리요청
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	//비번변경폼으로 이동-p623 32라인
		private String processForm(HttpServletRequest request, HttpServletResponse response) {
			String strempno = request.getParameter("empno");
			int empno = Integer.parseInt(strempno);
			request.setAttribute("empno",empno);
			return FORM_VIEW;
		}
		//비번변경처리-p623 37라인
		private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
			MypageInfoController mypageInfoController = new MypageInfoController();
			//1.파라미터받기
			String strcurSal = trim(request.getParameter("curSal")); //현재 비번(변경전비번)
			String strnewSal = trim(request.getParameter("newSal")); //new 비번(변경할비번)
			String strempNo  = request.getParameter("empno");
			int empno = Integer.parseInt(strempNo);
			//세션에서 로그인한 유저정보를 가져오기
			Emp user = (Emp)request.getSession().getAttribute("EMP_USER");
			Map<String,Boolean> errors = new HashMap<String,Boolean>();
			request.setAttribute("errors",errors);//p607 42라인
			//현재비번과 새비번필수입력-p623 47라인
			if(strcurSal==null || strcurSal.isEmpty()) {
				errors.put("curSal", Boolean.TRUE);
			}
			if(strnewSal==null || strnewSal.isEmpty()) {
				errors.put("newSal", Boolean.TRUE);
			}
			//p623 53라인
			if(!errors.isEmpty()) {//에러가 존재하면
				request.setAttribute("empno",empno);
				return FORM_VIEW;
			}
			//2.비즈니스로직수행<->Service<->DAO<->DB-p607 53라인
			//3.Model&4.view-p623 57라인
			try {
				int curSal = Integer.parseInt(strcurSal);
				int newSal = Integer.parseInt(strnewSal);
				updateService.updateSal(empno, curSal, newSal);
				request.setAttribute("empno",empno);
				return mypageInfoController.searchView(request, response,empno);
			}catch(InvalidException e) {
				errors.put("badCurSal", Boolean.TRUE);
				request.setAttribute("empno",empno);
				return FORM_VIEW;
			}catch(EmpNotFoundException e) {
				request.setAttribute("empno",empno);
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return null;
			}catch(NumberFormatException e) {
				request.setAttribute("empno",empno);
				errors.put("StringCurSal", Boolean.TRUE);
				return FORM_VIEW;
			}
		}
		private String trim(String str) {
			return (str==null)? null:str.trim();
		}//trim 끝

}
