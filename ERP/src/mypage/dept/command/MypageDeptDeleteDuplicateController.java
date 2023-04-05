package mypage.dept.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandController;
import mypage.dept.service.MypageDeptService;

public class MypageDeptDeleteDuplicateController implements CommandController {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//컨트롤러가 해야 할 일
		//1.파라미터얻기
		String strNewDeptno = request.getParameter("deptno");//user가 입력한 deptno
		int newDeptno = 1;   
		if(strNewDeptno!=null) {
			newDeptno = Integer.parseInt(strNewDeptno);			
		}
		//2.비즈니스로직수행<->Service<->DAO<->DB
		//Service의 메서드호출
		MypageDeptService mypageDeptService = new MypageDeptService();
		//Service참조변수.메서드명();
		int resultdept = mypageDeptService.deptNoDuplicate(newDeptno);
		int resultemp = mypageDeptService.EmpdeptNoDuplicate(newDeptno);
		//3.Model(비즈니스로직수행결과)
		request.setAttribute("resultdept",resultdept);
		request.setAttribute("resultemp",resultemp);
		request.setAttribute("Deptno",strNewDeptno);
		//4.View지정
		return "view/dept/deptDeleteForm.jsp";
	}
}
