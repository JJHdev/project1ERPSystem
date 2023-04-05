package hpdesk.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emp.model.Emp;
import hpdesk.service.HPDeskData;
import hpdesk.service.HPDeskNotFoundException;
import hpdesk.service.ModifyAdminService;
import hpdesk.service.ModifyHPDeskRequest;
import hpdesk.service.ModifyHPDeskService;
import hpdesk.service.ReadHPDeskService;
import mvc.command.CommandController;

//관리자모드 수정
//요청주소 /hpdesk/modifyadmin.aa
public class ModifyAdminController implements CommandController {
	private static final String FORM_VIEW="/view/hpdesk/modiyAdminForm.jsp";
	//상세보기 서비스
	private ReadHPDeskService readHPDeskService=new ReadHPDeskService();
	//수정처리 서비스
	private ModifyAdminService modifyAdminService =new ModifyAdminService();
		@Override
		public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
			if(request.getMethod().equalsIgnoreCase("GET")) {
				return processForm(request,response); //쓰기폼 보여줘			
			}else if(request.getMethod().equalsIgnoreCase("POST")){
				return processSubmit(request,response);//쓰기처리요청
			}else {
				response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); //405에러
				return null;
			}
		}
	//(해당게시글의 정보가 출력되어있는)수정폼으로 이동
	private String processForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
				try {
					//hdno=상세조회할글번호&pageNo=요청페이지&rowSize=1페이지당게시글수
					//만약 파라미터no(즉, 상세조회할글번호)가 null이면 RuntimeException발생
					String strNo = request.getParameter("hdno");//글번호
					if(strNo==null) {
						throw new RuntimeException();
					}
					int hdno = Integer.parseInt(strNo);//상세조회할글번호
					System.out.println("hdno="+hdno);
					//만약 파라미터pageNo(즉,요청페이지)가 null이면 요청페이지를 1로 설정
					String strPageNo = request.getParameter("pageNo"); //보고싶은페이지
					int pageNo = 1;   
					if(strPageNo!=null) {
						pageNo = Integer.parseInt(strPageNo);			
					}
					//만약 파라미터rowSize(페이지당게시글수)가 null이면 페이지당게시글수를 5로 설정
					String strRowSize = request.getParameter("rowSize"); //한 페이지당 보여줄 게시물수
					int rsize = 5;
					if(strRowSize != null && strRowSize.length() != 0 ) {
						rsize = Integer.parseInt(strRowSize);
					}		
					String uphdcheck=request.getParameter("hdcheck");//수정할 글확인 체크 1.미답변 2.처리중 3.답변완료
					int hdcheck = 1;
					if(uphdcheck!=null) {
						hdcheck = Integer.parseInt(uphdcheck);			
					}	
					String hdcomment = request.getParameter("hdcomment");//수정할 댓글

					//int hdno : 상세조회할 글번호  여기에서는 수정을 위해 상세보기를 진행
					//리턴유형 ArticleData: article테이블과 article_content테이블 관련 데이터  */  
					HPDeskData hpdeskData = readHPDeskService.getHPDesk(hdno);
					System.out.println("hpdeskData ="+hpdeskData);
					//로그인한 유저정보
					Emp user = (Emp)request.getSession().getAttribute("EMP_USER");
					//아래 ModifyRequest(로그인한 유저의사원번호, 글번호,작성자,게시글제목,	게시글내용,	게시글댓글,	체크
					ModifyHPDeskRequest modifyHPDeskReq = 
							new ModifyHPDeskRequest(user.getEmpno(), 
									hdno,
									hpdeskData.getHpdesk().getHpdeskWriter().getEname(),
									hpdeskData.getHpdesk().getHdtitle(),
									hpdeskData.getHpdesk().getHdcontent(),
									hpdeskData.getHdcomm().getHdcomment(),
									hpdeskData.getHpdesk().getHdcheck());
					request.setAttribute("modifyHPDeskReq", modifyHPDeskReq);
					return FORM_VIEW;
				}catch(HPDeskNotFoundException e) {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);//404에러
					return null;
				}
			}
			//수정처리
			private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
				//hdno=상세조회할글번호&pageNo=요청페이지&rowSize=1페이지당게시글수
				//만약 파라미터hdno(즉, 상세조회할글번호)가 null이면 RuntimeException발생
				String strNo = request.getParameter("hdno");//글번호
				if(strNo==null) {
					throw new RuntimeException();
				}
				int hdno = Integer.parseInt(strNo);//상세조회할글번호
				System.out.println("hdno"+hdno);
				//만약 파라미터pageNo(즉,요청페이지)가 null이면 요청페이지를 1로 설정
				String strPageNo = request.getParameter("pageNo"); //보고싶은페이지
				int pageNo = 1;   
				if(strPageNo!=null) {
					pageNo = Integer.parseInt(strPageNo);			
				}
				//만약 파라미터rowSize(페이지당게시글수)가 null이면 페이지당게시글수를 5으로 설정
				String strRowSize = request.getParameter("rowSize"); //한 페이지당 보여줄 게시물수
				int rsize = 5;
				if(strRowSize!=null) {
					rsize = Integer.parseInt(strRowSize);			
				}		
				String uphdcheck=request.getParameter("hdcheck");//수정할 글확인 체크 1.미답변 2.처리중 3.답변완료
				int hdcheck = 1;
				if(uphdcheck!=null) {
					hdcheck = Integer.parseInt(uphdcheck);			
				}			
				String hdcomment = request.getParameter("hdcomment");//수정할 댓글
				//작성자,제목,내용 가져오기
				HPDeskData hpdeskData = readHPDeskService.getHPDesk(hdno);
				//로그인한 유저정보
				Emp user = (Emp)request.getSession().getAttribute("EMP_USER");
				//아래 ModifyRequest(로그인한 유저의 int hdno, String ename, int empno, String hdtitle, String hdcontent, String hdcomment,int hdcheck이 저장된 db)
				ModifyHPDeskRequest modifyHPDeskReq = 
						new ModifyHPDeskRequest(user.getEmpno(), 
								hdno,
								hpdeskData.getHpdesk().getHpdeskWriter().getEname(),
								hpdeskData.getHpdesk().getHdtitle(),
								hpdeskData.getHpdesk().getHdcontent(),
								hdcomment,hdcheck);
				request.setAttribute("modifyHPDeskReq", modifyHPDeskReq);
				//파라미터 ModifyRequest modReq: 글 수정을 위한    수정하는 사용자id,수정할 글번호,수정할 제목,수정할 내용
				modifyAdminService.modifyHPDesk(modifyHPDeskReq);
				return "/view/hpdesk/Success.jsp";  //수정성공페이지
			}
		}
