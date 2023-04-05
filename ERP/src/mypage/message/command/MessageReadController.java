package mypage.message.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandController;
import mypage.message.dto.MessageDTO;
import mypage.message.dto.MessageData;
import mypage.message.service.ReadMessageService;

public class MessageReadController implements CommandController {

	private ReadMessageService readMessageService = new ReadMessageService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
				String strNo = request.getParameter("no");
				if(strNo==null) {
					throw new RuntimeException();
				}
				int no = Integer.parseInt(strNo);//상세조회할글번호
				//만약 파라미터pageNo(즉,요청페이지)가 null이면 요청페이지를 1로 설정
				String strPageNo = request.getParameter("pageNo"); //보고싶은페이지
				int pageNo = 1;   
				if(strPageNo!=null) {
					pageNo = Integer.parseInt(strPageNo);			
				}
				//만약 파라미터rowSize(페이지당게시글수)가 null이면 페이지당게시글수를 3으로 설정
				String strRowSize = request.getParameter("rowSize"); //한 페이지당 보여줄 게시물수
				int rsize = 3;
				if(strRowSize!=null) {
					rsize = Integer.parseInt(strRowSize);			
				}
				MessageData messageData = readMessageService.getMessage(no);
				//3.Model
				request.setAttribute("messageData", messageData);//article테이블과 article_content테이블 관련 데이터
				request.setAttribute("pageNo", pageNo);//요청페이지
				request.setAttribute("rowSize", rsize);//1페이지당게시글수
				//4.View
				return "/view/message/readMessage.jsp";
			}
	}

