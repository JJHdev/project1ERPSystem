package mypage.message.dto;

import java.util.Date;

public class MessageDTO {

	private int messageno;
	private int sendempno;
	private int receiveempno;
	private String title;
	private Date send_time;
	private String content;
	private String sendempname;
	public String getSendempname() {
		return sendempname;
	}

	public void setSendempname(String sendempname) {
		this.sendempname = sendempname;
	}

	public MessageDTO(int messageno, int sendempno, int receiveempno, String title, Date send_time, String content) {
		this.messageno = messageno;
		this.sendempno = sendempno;
		this.receiveempno = receiveempno;
		this.title = title;
		this.send_time = send_time;
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public MessageDTO(int messageno, int sendempno, int receiveempno, Date send_time,String title,String sendempname) {
		this.sendempname = sendempname;
		this.messageno = messageno;
		this.sendempno = sendempno;
		this.receiveempno = receiveempno;
		this.title = title;
		this.send_time = send_time;
	}
	
	public MessageDTO(int sendempno, int receiveempno, String title) {
		this.sendempno = sendempno;
		this.receiveempno = receiveempno;
		this.title = title;
	}


	public int getMessageno() {
		return messageno;
	}

	public void setMessageno(int messageno) {
		this.messageno = messageno;
	}

	public int getSendempno() {
		return sendempno;
	}

	public void setSendempno(int sendempno) {
		this.sendempno = sendempno;
	}

	public int getReceiveempno() {
		return receiveempno;
	}

	public void setReceiveempno(int receiveempno) {
		this.receiveempno = receiveempno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getSend_time() {
		return send_time;
	}

	public void setSend_time(Date send_time) {
		this.send_time = send_time;
	}

	@Override
	public String toString() {
		return "MessageDTO [messageno=" + messageno + ", sendempno=" + sendempno + ", receiveempno=" + receiveempno
				+ ", title=" + title + ", send_time=" + send_time + ", content=" + content + ", sendempname="
				+ sendempname + "]";
	}

}
