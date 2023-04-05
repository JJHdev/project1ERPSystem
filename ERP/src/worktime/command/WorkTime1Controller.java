package worktime.command;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emp.model.Emp;
import mvc.command.CommandController;
import worktime.Service.WorkTimeOutService;
import worktime.Service.WorkTimeService;
import worktime.model.Work_In;
import worktime.model.Work_Out;

public class WorkTime1Controller implements CommandController {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String btn = request.getParameter("operator");
		System.out.println("btn="+btn);
		
		if(btn.equals("출근 체크")) {
			System.out.println("workintimes.process()호출");

			Emp user =(Emp) request.getSession().getAttribute("EMP_USER");
			user.getEmpno(); //로그인한 유저사원번호
			
			String  todaynoti = request.getParameter("todaynoti"); // 특이사항 관련 내용
			
			WorkTimeService workTimeService = new WorkTimeService();//  인서트 갈때 서비스 커넥션 이다  
			
			WorkTimeService workTimeService2 = new WorkTimeService(); // 셀렉트 받아올때 서비스 커넥션이다
			
			workTimeService.insertWorkintime(todaynoti,user.getEmpno());
			
			Work_In workin =workTimeService2.selectWorkintime(todaynoti,user.getEmpno());
			System.out.println("올때todaynoti받아오는지~~~"+todaynoti); //잘받아오더라~~~
			
			request.setAttribute("workin", workin);
			request.setAttribute("workintime", workin.getwork_in_time());
			
			return "/view/worktime/workForm.jsp";
			
		}
		
		 if(btn.equals("퇴근 체크")) {
			System.out.println("workouttimes.process()호출");
			
			Emp user =(Emp) request.getSession().getAttribute("EMP_USER");
			user.getEmpno(); //로그인한 유저사원번호
			System.out.println("user.getEmpno()"+user.getEmpno());
			
			String todayoutnoti = request.getParameter("todayoutnoti");
			System.out.println("todayoutnoti"+todayoutnoti);
			
			WorkTimeOutService workTimeOutService = new WorkTimeOutService();//  인서트 갈때 서비스 커넥션 이다  
			
			WorkTimeOutService workTimeOutService2 = new WorkTimeOutService(); // 셀렉트 받아올때 서비스 커넥션이다
			
			workTimeOutService.insertWorkOutTime(todayoutnoti,user.getEmpno());
			
			Work_Out workout =workTimeOutService2.selectWorkOutTime(todayoutnoti,user.getEmpno());
			System.out.println("올때todayoutnoti받아오는지~~~"+todayoutnoti); //퇴근버튼누른 후 받아오는값
			
			request.setAttribute("workout", workout);
			
			return "/view/worktime/workForm.jsp";
			
		}
		return "/view/worktime/workForm.jsp";
		
	}

}




