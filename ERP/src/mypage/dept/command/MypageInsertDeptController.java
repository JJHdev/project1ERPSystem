package mypage.dept.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandController;
import mypage.dept.service.MypageDeptService;

public class MypageInsertDeptController implements CommandController {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
				//1.파라미터받기
				String strdeptno = request.getParameter("deptno");
				String deptname = request.getParameter("deptname");
				//2.비즈니스로직
				int deptno = 1;   
				if(strdeptno!=null) {
					deptno = Integer.parseInt(strdeptno);			
				}
				MypageDeptService mypageDeptService=new MypageDeptService();
				int result = mypageDeptService.insertDept(deptno,deptname);
				//3.model수행
				response.setCharacterEncoding("UTF-8");
				request.setCharacterEncoding("UTF-8");
				if(result==1) {
					response.getWriter().print("생성하는데 성공했습니다. ");
				}else {
					response.getWriter().print("생성하는데 실패했습니다.");
				}
				//4.view지정.
				return null;
	}
}
