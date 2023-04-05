package offday.command;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import emp.model.Emp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import mvc.command.CommandController;
import offday.Service.OffDayHisService;
import offday.Service.OffDayUpdateService;
import offday.Service.OffdayNONotFoundException;
import offday.Service.PermissionDeniedException;
import offday.Service.ResvNotFoundException;
import offday.model.OffDay;
import offday.model.OffDayUpdate;


public class OffDayUpdateController implements CommandController {


private static final String FORM_VIEW = "/view/worktime/offlistupdate.jsp";

private OffDayHisService offdayhisService = new OffDayHisService();

private OffDayUpdateService offdayUpdateService = new OffDayUpdateService();

@Override
public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
   
	if(request.getMethod().equalsIgnoreCase("GET")) {
      return offlistForm(request,response);//수정폼보여줘
   }else if(request.getMethod().equalsIgnoreCase("POST")) {
      return processSubmit(request,response);//수정처리요청
   }else {
      response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
   return null;
   }
}

//예약수정폼 보여줘 (기존수정값 불러오기) //ResvUpdateController의 processForm 역활
public String offlistForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
   System.out.println("update offlistForm진입");
   try {
      SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");

      String deptno1 = request.getParameter("deptno");
      String deptname = request.getParameter("deptname");
      String start_day1 = request.getParameter("startday");
      String end_day1 = request.getParameter("endday");
      String offnotice = request.getParameter("offnotice");
      String offdayno1 = request.getParameter("offdayno");
      
      System.out.println("offdayno==="+offdayno1);
      System.out.println("deptno1=="+deptno1);
      System.out.println("start_day1=="+start_day1);
      if(offdayno1==null) {
         throw new RuntimeException();
      }
      Date startday;
      Date endday;
      int deptno;
      deptno = Integer.parseInt(deptno1);
      startday = sdf.parse(start_day1);
      endday = sdf.parse(end_day1);
      
      int offdayno = Integer.parseInt(request.getParameter("offdayno"));
      System.out.println("deptno1111=="+deptno1);
      System.out.println("start_day1111=="+start_day1);
      
      
      request.setAttribute("deptno",deptno1);
      request.setAttribute("deptname",deptname);
      request.setAttribute("startday",start_day1);
      request.setAttribute("endday",end_day1);
      request.setAttribute("offnotice",offnotice);
      request.setAttribute("offdayno",offdayno1);
     
      
      
      //비즈니스로직(예약번호)
      //파라미터 resvno, 리턴유형 resv
      Emp user = (Emp)request.getSession().getAttribute("EMP_USER");
      OffDay offday = offdayhisService.getoffno(offdayno);
      //상세보기(기록표)에서 가져오는 역활,모델명이다.//getoffno=getResv(ResvCheckService)이다.


      OffDayUpdate offdayupdateM = 
            new OffDayUpdate(user.getEmpno(),deptno,deptname,startday,endday,offnotice,offdayno);
      System.out.println("기존deptno1111=="+deptno1);
   
      
      
      System.out.println("sadfasdf"+offdayupdateM);
      //본인 예약 수정여부 확인
      if(!canUpdate(user,offdayupdateM)) {
         response.sendError(HttpServletResponse.SC_FORBIDDEN);
         return null;
      }
   
      request.setAttribute("OffDayUpdateRequest", offdayupdateM);
      System.out.println(offdayupdateM);
      return FORM_VIEW;
      }catch(OffdayNONotFoundException e) {
         response.sendError(HttpServletResponse.SC_NOT_FOUND); //404에러
         return null;
      }
}

//예약수정처리
//수정할 것 resvno,empno,resvdate,roomname,resvmemo,resvname,resvtel,resvemail
private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws ParseException, OffdayNONotFoundException, ResvNotFoundException, PermissionDeniedException {
   System.out.println("update processSubmit진입");

   Emp user = (Emp)request.getSession().getAttribute("EMP_USER");

   SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");

 
   String deptno1 = request.getParameter("deptno");
   String deptname = request.getParameter("deptname");
   String start_day1 = request.getParameter("startday");
   String end_day1 = request.getParameter("endday");
   String offnotice = request.getParameter("offnotice");
   String offdayno1 = request.getParameter("offdayno");
   
   System.out.println("수정처리deptno1111=="+deptno1);
   System.out.println("수정처리deptno1111=="+deptname);
   System.out.println("수정처리deptno1111=="+start_day1);
   System.out.println("수정처리deptno1111=="+end_day1);
   System.out.println("수정처리deptno1111=="+offnotice);
   System.out.println("수정처리deptno1111=="+offdayno1);
   if(offdayno1==null) {
      throw new RuntimeException();
   }
   int offdayno = Integer.parseInt(request.getParameter("offdayno"));
   try {
      Date start_offday;
      Date end_offday;
      int deptno;
      deptno = Integer.parseInt(deptno1);
      offdayno = Integer.parseInt(offdayno1);
      start_offday = sdf.parse(start_day1);
      end_offday = sdf.parse(end_day1);
      OffDayUpdate offdayupdateM = 
    		  new OffDayUpdate(user.getEmpno(),deptno,deptname,start_offday,end_offday,offnotice,offdayno);

      request.setAttribute("offdayupdateM", offdayupdateM);

      System.out.println(offdayupdateM);
   
   
   //request.getParameter로 지정해야할값 (resvdate, roomname,resvmemo,resvname,resvtel,resvemail,)


   //비즈니스로직(예약번호)
   //파라미터 resvno, 리턴유형 resv
   OffDay offday = offdayUpdateService.getoffno(offdayno);
	/*
	 * //유효성검사 Map<String,Boolean> errors = new HashMap<String,Boolean>();
	 * request.setAttribute("errors",errors);//p670 78라인 resvUpReq.validate(errors);
	 * if(!errors.isEmpty()) { return FORM_VIEW; }
	 */
   
   offdayUpdateService.update(offdayupdateM);
   System.out.println("offdayupdateM"+offdayupdateM);
   
   }catch(RuntimeException e){
      e.printStackTrace();
   }
   
   return "/view/worktime/offupdateSuccess.jsp";  //일정변경 후 돌아오는곳
   
   
}//processSubmit() 끝

private boolean canUpdate(Emp user, OffDayUpdate offdayupdateM) {
   int empno = user.getEmpno();
   
   int updateoffEmpno = offdayupdateM.getEmpno();
   if(empno==updateoffEmpno) {
      return true;
      
   }return false;
}


}
