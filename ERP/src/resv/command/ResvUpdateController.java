package resv.command;

import java.io.IOException;
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
import resv.service.PermissionDeniedException;
import resv.service.ResvCheckService;
import resv.service.ResvNotFoundException;
import resv.service.ResvUpdateRequest;
import resv.service.ResvUpdateService;

public class ResvUpdateController implements CommandController {

	private static final String FORM_VIEW = "/view/resv/resvUpdate.jsp";
	
	private ResvCheckService resvCheckService = new ResvCheckService();
	private ResvUpdateService resvUpdateService = new ResvUpdateService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response);//수정폼보여줘
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response);//수정처리요청
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		return null;
		}
	}
	
	//예약수정폼 보여줘 (기존수정값 불러오기)
	public String processForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");

			String strResvdate = request.getParameter("resvdate");
			String roomname = request.getParameter("roomname");
			String resvmemo = request.getParameter("resvmemo");
			String resvname = request.getParameter("resvname");
			String resvtel = request.getParameter("resvtel");
			String resvemail = request.getParameter("resvemail");
			String strResvNum = request.getParameter("resvno");
			if(strResvNum==null) {
				throw new RuntimeException();
			}
			Date resvdate = sdf.parse(strResvdate);
			int resvno = Integer.parseInt(request.getParameter("resvno"));

			//비즈니스로직(예약번호)
			//파라미터 resvno, 리턴유형 resv
			Emp user = (Emp)request.getSession().getAttribute("EMP_USER");
			Resv resv = resvCheckService.getResv(resvno);

			ResvUpdateRequest resvUpReq = 
					new ResvUpdateRequest(user.getEmpno(),resvdate,roomname,resvmemo,resvname,resvtel,resvemail,resvno);
			
			//본인 예약 수정여부 확인
			if(!canUpdate(user,resvUpReq)) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
		
			request.setAttribute("resvUpReq", resvUpReq);
			return FORM_VIEW;
			}catch(ResvNotFoundException e) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND); //404에러
				return null;
			}
	}
	
	//예약수정처리
	//수정할 것 resvno,empno,resvdate,roomname,resvmemo,resvname,resvtel,resvemail
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {

		Emp user = (Emp)request.getSession().getAttribute("EMP_USER");

		SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");

		String strResvDate = request.getParameter("resvdate");
		String roomname = request.getParameter("roomname");
		String resvmemo = request.getParameter("resvmemo");
		String resvname = request.getParameter("resvname");
		String resvtel = request.getParameter("resvtel");
		String resvemail = request.getParameter("resvemail");
		String strResvNum = request.getParameter("resvno");
		if(strResvNum==null) {
			throw new RuntimeException();
		}
		int resvno = Integer.parseInt(request.getParameter("resvno"));
		try {
			Date resvdate;
			resvdate = sdf.parse(strResvDate);
			ResvUpdateRequest resvUpReq = 
					new ResvUpdateRequest(user.getEmpno(),resvdate,roomname,resvmemo,resvname,resvtel,resvemail,resvno);
			request.setAttribute("resvUpReq", resvUpReq);

		
		//request.getParameter로 지정해야할값 (resvdate, roomname,resvmemo,resvname,resvtel,resvemail,)

		//비즈니스로직(예약번호)
		Resv resv = resvCheckService.getResv(resvno);
	
		//유효성검사
		Map<String,Boolean> errors = new HashMap<String,Boolean>();
		request.setAttribute("errors",errors);
		resvUpReq.validate(errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		resvUpdateService.update(resvUpReq);
		
		}catch(RuntimeException e){
			e.printStackTrace();
		}
		
		return "/view/resv/resvUpdateSuccess.jsp";
		
	}//processSubmit() 끝

	private boolean canUpdate(Emp user, ResvUpdateRequest resvUpReq) {
		int empno = user.getEmpno();
		
		int resvEmpno = resvUpReq.getEmpno();
		if(empno==resvEmpno) {
			return true;
		}return false;
	}


}
