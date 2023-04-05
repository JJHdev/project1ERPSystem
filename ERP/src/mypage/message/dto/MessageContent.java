package mypage.message.dto;

public class MessageContent {
	//필드
		private Integer messageno; //글번호
		private String content; //내용
		
		public MessageContent(Integer messageno, String content) {
			this.messageno = messageno;
			this.content = content;
		}
		public Integer getMessageno() {
			return messageno;
		}
		public void setMessageno(Integer messageno) {
			this.messageno = messageno;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		@Override
		public String toString() {
			return "MessageContent [messageno=" + messageno + ", content=" + content + "]";
		}
		

		
		
}
