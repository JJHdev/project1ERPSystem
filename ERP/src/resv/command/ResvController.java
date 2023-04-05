package resv.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emp.model.Emp;
import mvc.command.CommandController;
import resv.model.Resv;
import resv.service.ResvNotMatchException;
import resv.service.ResvService;

public class ResvController implements CommandController {
	
	private static final String FORM_VIEW = "/view/resv/resv.jsp";
	private ResvService resvService = new ResvService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//폼의 요청방식에 따라 쓰기폼보여줘 요청과  쓰기처리요청을 구분
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response);//쓰기폼보여줘
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response);//쓰기처리요청
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}//process끝

	//예약폼으로 이동
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		//로그인유저 세션에서 받기
		Emp user = (Emp)request.getSession().getAttribute("EMP_USER");
		
		return FORM_VIEW;
	}
	
	//예약하기
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		//로그인유저 세션에서 받기
		Emp user = (Emp)request.getSession().getAttribute("EMP_USER");
		SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");

		String strEmpno = request.getParameter("empno");
		String strResvdate = request.getParameter("resvdate");
		String roomname = request.getParameter("roomname");
		String resvmemo = request.getParameter("resvmemo");
		String resvname = request.getParameter("resvname");
		String resvtel = request.getParameter("resvtel");
		String resvemail = request.getParameter("resvemail");
		
		Date resvdate = sdf.parse(strResvdate);
		int empno=Integer.parseInt(strEmpno);


		Resv newResv = new Resv();
		newResv.setEmpno(empno);
		newResv.setResvdate(resvdate);
		newResv.setRoomname(roomname);
		newResv.setResvmemo(resvmemo);
		newResv.setResvname(resvname);
		newResv.setResvtel(resvtel);
		newResv.setResvemail(resvemail);

		
		Map<String,Boolean> errors = new HashMap<String,Boolean>();
		request.setAttribute("errors", errors);//p598 43라인

		resvService.resv(newResv);
		
		return "view/resv/resvSuccess.jsp";
		
	}


	
}
