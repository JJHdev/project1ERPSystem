package mypage.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emp.service.IdDuplicateService;
import mvc.command.CommandController;
import mypage.Service.MypageSearchService;

public class MypageSearchController implements CommandController {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String SearchEno = request.getParameter("mypagesearch");
		
		MypageSearchService mypageSearchService = new MypageSearchService();
		boolean mypageSearch = mypageSearchService.mypageenoSearch(SearchEno);
		
		request.setAttribute("SearchEno", SearchEno);
		request.setAttribute("mypageSearch", mypageSearch);
		
		return null;
	}

}
