package worktime.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emp.model.Emp;
import mvc.command.CommandController;
import worktime.Service.WorkTimeOutService;
import worktime.model.Work_Out;

//이 클래스는 workinform.jsp에서 받은 사용자 정보를  request, response 해 DB와 연결해주는 다리역활이다.
//어떤 이름으로  입력받은,저장되어있는 데이터를 유통할지 지정한다.
public class WorkTimeOutController implements CommandController {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("workouttimes.process()호출");
		
		String workin = request.getParameter("workin");
		System.out.println("workin");
		
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
		request.setAttribute("workin", workin);
		
		return "/view/worktime/workForm.jsp";
		
	
	}

}
