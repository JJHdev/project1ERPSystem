package emp.command;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emp.model.Emp;
import emp.model.MypageDbEmp;
import emp.service.DuplicatedIdException;
import emp.service.RegisterService;
import mvc.command.CommandController;
import mypage.dept.service.MypageSelectDeptService;


public class RegisterController implements CommandController {
	//필드
	private static final String FORM_VIEW = "view/emp/empRegisterForm.jsp";
	//생성자
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)throws Exception{
		//폼의 요청방식에 따라 회원가입폼보여줘 요청과  가입처리요청을 구분
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response);//회원가입폼보여줘
		
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request,response);//가입처리요청
		
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}//process()
	//회원가입폼보여줘 요청.p598 31라인
	private String processForm(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		MypageSelectDeptService mypageSelectDeptService =new MypageSelectDeptService();
		List<MypageDbEmp> mypageDbEmpList = mypageSelectDeptService.deptAllSelect();
		request.setAttribute("mypageDbEmpList",mypageDbEmpList);
		return FORM_VIEW;
	}
	//가입처리요청.p598 35라인
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		//컨트롤러가 해야 할 일
		//1.파라미터받기
		String empnumber =request.getParameter("empno");
		String ename=request.getParameter("ename");
		String emppwd = request.getParameter("emppwd");
		String re_emppwd = request.getParameter("re_emppwd");
		String deptnumber = request.getParameter("deptno");
		String level = request.getParameter("level");
		String f_email = request.getParameter("f_email");
		String b_email = request.getParameter("b_email");
		String tel = request.getParameter("tel");
		String salsal = request.getParameter("sal");
		String strHiredate = request.getParameter("hiredate");
		String gradee = request.getParameter("grade");
		SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd");
		Date hiredate = format.parse(strHiredate);
		int empno=Integer.parseInt(empnumber);
		int deptno=Integer.parseInt(deptnumber);
		int grade=Integer.parseInt(gradee);
		int sal = Integer.parseInt(salsal);
		String email= f_email+"@"+b_email;
		//2.비즈니스로직<->Service<->DAO<->DB
		//3.Model처리 request,session & 4.View지정
		RegisterService registerService= new RegisterService();
		Emp empReq= new Emp();
		empReq.setEmpno(empno);
		empReq.setEname(ename);
		empReq.setEmppwd(emppwd);
		empReq.setRe_emppwd(re_emppwd);
		empReq.setDeptno(deptno);
		empReq.setErprank(level);
		empReq.setEmail(email);
		empReq.setTel(tel);
		empReq.setSal(sal);
		empReq.setHiredate(hiredate);
		empReq.setGrade(grade);
		Map<String,Boolean> errors = new HashMap<String,Boolean>();
		request.setAttribute("errors",errors);//p598 43라인
		empReq.validate(errors);//유효성검(필수입력,비번과 비번재확인 일치여부 등)
		if(!errors.isEmpty()) {//에러가 존재하면
			System.out.println("오류가있네?");
			return FORM_VIEW;
		}
		try {
			registerService.register(empReq);//p598 52라인
			return "view/emp/empRegisterForm.jsp";
		}catch(DuplicatedIdException e) {
			//errors.put("duplicateId",Boolean.TRUE);
			return FORM_VIEW;
		}
	}
}