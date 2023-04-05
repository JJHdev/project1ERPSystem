package hpdesk.service;

import java.util.Date;
import java.util.List;

import hpdesk.model.HPDesk;
import hpdesk.model.HPDeskWriter;

//페이징처리에 필요한 데이터와 게시글정보목록
public class HPDeskPage {
	
	private int total; //전체게시물수
	private int currentPage; //현재페이지(요청페이지)
	private List<HPDesk> hdcontent; //hpdesk목록정보(글번호,글제목,작성자정보,작성일,노출여부,글내용,글확인체크)
	//Integer hdno, String hdtitle, HPDeskWriter hpdeskWriter, Date hddate, String isshow, String hdcontent, int hdcheck
	private int totalPages; //총페이지수
	private int startPage; //시작페이지
	private int endPage; //마지막페이지

	public HPDeskPage(int total, int currentPage, int size,List<HPDesk> hdcontent) {
		//1.전체게시물 2.현재페이지(요청페이지) 3. 1page당 보여줄 게시물수 4. 출력할 게시글내용목록
		this.total = total;
		this.currentPage = currentPage;
		this.hdcontent = hdcontent;
		if (total == 0) { //게시물이 존재하지 않는 경우
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		} else { //게시물이 존재하는 경우
			totalPages = total/size; //총페이지수=전체게시물수/1page당 보여줄 게시물수
			
			if (total%size>0) {      //나머지가 0보다 크면
				totalPages++;        //전체페이수를 1씩증가
	
				
			}
			int modVal=currentPage%5;  //user가보고싶어요청페이지를 5로 나눈 나머지를 저장
			
			// 요청페이지가 1-> modVal은 1
			// 요청페이지가 2-> modVal은 2
			// 요청페이지가 3-> modVal은 3
			// 요청페이지가 4-> modVal은 4
			// 요청페이지가 5-> modVal은 0
			
			startPage =currentPage/5*5 + 1;
			// 요청페이지가 1-> startPage는 1
			// 요청페이지가 2-> startPage는 1
			// 요청페이지가 3-> startPage는 1
			// 요청페이지가 4-> startPage는 1
			// 요청페이지가 5-> startPage는 6
			
			//modVal==0은 요청페이지가 5의배수인 5 10 15....
			if (modVal==0) startPage-=5;//startPage=startPage-5;
	
			endPage = startPage + 4; 	//endPage가 전체페이수보다크면  endPage값을 전체페이수로 적용해라
			if(endPage>totalPages) endPage=totalPages;
		}
	}

	//전체게시물수 조회
	public int getTotal() {
		return total;
	}

	//게시글이 없으면 true리턴
	public boolean haNoHPDeskes() {
		return total==0;
	}	
	//게시글이 있으면 true리턴
	public boolean hasHPDeskes() {
			return total>0;
	}
	public int getCurrentPage() {
		return currentPage;
	}

	public List<HPDesk> getHdcontent() {
		return hdcontent;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	@Override
	public String toString() {
		return "HPDeskPage [total=" + total + ", currentPage=" + currentPage + ", hdcontent=" + hdcontent
				+ ", totalPages=" + totalPages + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}
	
}
