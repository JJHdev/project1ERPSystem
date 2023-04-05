package comment.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import notice.model.Notice;

public class Comment {
	
	//댓글 필드
	private int comment_no;
	private int notice_no;
	private int c_writer;
	private String c_content;
	private Date c_regdate;
	private Date c_moddate;
	private String isshow;
	
	//댓글 뿌려주기위한 List
	private List<Comment> comments;
	
	//생성자
	public Comment(int comment_no, int notice_no, int c_writer, String c_content, Date c_regdate, Date c_moddate,
			String isshow) {

		this.comment_no = comment_no;
		this.notice_no = notice_no;
		this.c_writer = c_writer;
		this.c_content = c_content;
		this.c_regdate = c_regdate;
		this.c_moddate = c_moddate;
		this.isshow = isshow;
	}
	
	//댓글단 회원정보를 위한 생성자
	public Comment(int comment_no,int c_writer,String c_content) {
		this.comment_no=comment_no;
		this.c_writer=c_writer;
		this.c_content=c_content;
	}
	//글쓴이를 위한 생성자
	public Comment(int c_writer,String c_content) {
		this.c_writer=c_writer;
		this.c_content=c_content;
		
	}
	
	

	public Comment(List<Comment> comments) {
		this.comments=comments;
	}

	public int getComment_no() {
		return comment_no;
	}

	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}

	public int getNotice_no() {
		return notice_no;
	}

	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}

	public int getC_writer() {
		return c_writer;
	}

	public void setC_writer(int c_writer) {
		this.c_writer = c_writer;
	}

	public String getC_content() {
		return c_content;
	}

	public void setC_content(String c_content) {
		this.c_content = c_content;
	}

	public Date getC_regdate() {
		return c_regdate;
	}

	public void setC_regdate(Date c_regdate) {
		this.c_regdate = c_regdate;
	}

	public Date getC_moddate() {
		return c_moddate;
	}

	public void setC_moddate(Date c_moddate) {
		this.c_moddate = c_moddate;
	}

	public String getIsshow() {
		return isshow;
	}

	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public boolean hasNoComments() {
		return comments == null;
	}
	//게시글이 존재하니? 게시글 있으면 true
	public boolean hasComments() {
		return (comments!= null);
	}
	
	public void validate(Map<String ,Boolean> errors) {
		if(c_content==null || c_content.trim().isEmpty()) {
			errors.put("c_content", Boolean.TRUE);
		}
		
	}//validate() 끝
	
	public void validate2(Map<String,Boolean> errors) {
		if(c_content == null || c_content.isEmpty()) {
			errors.put("c_content", Boolean.TRUE);
		}
		
	}//validate2() 끝

	@Override
	public String toString() {
		return "Comment [comment_no=" + comment_no + ", notice_no=" + notice_no + ", c_writer=" + c_writer
				+ ", c_content=" + c_content + ", c_regdate=" + c_regdate + ", c_moddate=" + c_moddate + ", isshow="
				+ isshow + ", comments=" + comments + "]";
	}
	
	
	
	
}
