package surv.service;

import java.util.List;

import surv.model.SurvArticle;

//페이징 처리에 필요한 데이터와 게시글 정보 목록
public class SurvArticlePage {

	//필드
	private int total; //전체게시물수
	private int currentPage;//현재 페이지(요청페이지)
	private List<SurvArticle> content;//게시글목록정보(글번호,제목,참여자수,최초작성일,마지막수정일,노출여부)
	//article_no,writer_id,writer_name,title,regdate,moddate,read_cnt,isshow 
	private int totalPages;		//총페이지수
	private int startPage;		//시작페이지번호
	private int endPage;		//끝페이지번호

	//생성자
	//생성자
		//파라미터를 가진 생성자 - 매개변수로 받은 데이터를 필드의 초기화
		//1번째 파라미터 total- 전체게시물수
		//2번째 파라미터 currentPage-현재 페이지(user가 보고싶은 페이지번호)
		//3번째 파라미터 size - 1page당 보여줄 게시물수(교재에서는 10건)
		//4번째 파라미터 - 출력할 게시글내용목록
	public SurvArticlePage(int total, int currentPage, 
					int size, List<SurvArticle> content) {
					this.total = total;
					this.currentPage = currentPage;
					this.content = content;
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
	

	
	endPage = startPage + 4;
	//endPage가 전체페이수보다크면  endPage값을 전체페이수로 적용해라                    
	if(endPage>totalPages) endPage=totalPages;
			}
}
	
	
	//메서드
	
	


	public int getTotal() {
		return total;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<SurvArticle> getContent() {
		return content;
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

	//게시글없니? 게시글없으면 true리턴
		public boolean hasNoArticles() {
			return total == 0;
		}

		//게시글이 존재하니? 게시글 있으면 true
		public boolean hasArticles() {
			return total > 0;
		}


		@Override
		public String toString() {
			return "SurvArticlePage [total=" + total + ", currentPage=" + currentPage + ", content=" + content
					+ ", totalPages=" + totalPages + ", startPage=" + startPage + ", endPage=" + endPage + "]";
		}


	
		

	
}
