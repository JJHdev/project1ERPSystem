package worktime.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emp.model.Emp;
import mvc.command.CommandController;
import worktime.Service.WorkTimeService;
import worktime.model.Work_In;

//이 클래스는 workinform.jsp에서 받은 사용자 정보를  request, response 해 DB와 연결해주는 다리역활이다.
// 어떤 이름으로  입력받은,저장되어있는 데이터를 유통할지 지정한다.
public class WorkTimeController implements CommandController {

	/* private static final String FORM_VIEW = "/view/worktime/worklayout.jsp"; */
	/*
	 * private Work_In = new Work_In(); //worktime.Service ->> Work_In_Data->>
	 * Work_In 항목이다.
	 */

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		
		return "/view/worktime/workForm.jsp";
		
	}

}
