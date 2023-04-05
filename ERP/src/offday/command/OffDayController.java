package offday.command;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emp.model.Emp;
import mvc.command.CommandController;
import offday.Service.OffDayService;
import offday.model.OffDay;

public class OffDayController implements CommandController {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("OffDayController.process()호출");
		
		Emp user =(Emp) request.getSession().getAttribute("EMP_USER");
		user.getEmpno(); //로그인한 유저사원번호
		user.getDeptno();//로그인한 유저 부서번호
		
		String offnotice =request.getParameter("offnotice");
		System.out.println("offnotice호출");
		
		
		String startday1 = request.getParameter("startday");
	      SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd");
	    Date startday = format.parse(startday1);
	    
	    System.out.println("startday1호출");
	    
	    String endday2 = request.getParameter("endday");
	      SimpleDateFormat  format2 = new SimpleDateFormat("yyyy-MM-dd");
	    Date endday = format2.parse(endday2); 
	     
	    
	    
	    
		OffDayService offdayService = new OffDayService(); //DB로갈때
		
		OffDayService offdayService2 = new OffDayService(); // DB에서 받아올때
		
		offdayService.insertOffDay(user.getEmpno(), user.getDeptno(), startday, endday, offnotice);
	
		OffDay offday =offdayService2.selectOffday(user.getEmpno(),user.getDeptno());  
		
		
		
		
		request.setAttribute("offday", offday);
		
		
		return "/view/worktime/dayoffForm.jsp";
	}

}
