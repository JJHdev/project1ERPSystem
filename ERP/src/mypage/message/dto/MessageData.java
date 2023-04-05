package mypage.message.dto;


public class MessageData {
	//필드
		private MessageDTO messageDTO;
		private MessageContent messageContent;
		
		public MessageData(MessageDTO messageDTO, MessageContent messageContent) {
			this.messageDTO = messageDTO;
			this.messageContent = messageContent;
		}
		@Override
		public String toString() {
			return "MessageData [messageDTO=" + messageDTO + ", messageContent=" + messageContent + "]";
		}
		public MessageDTO getMessageDTO() {
			return messageDTO;
		}
		public void setMessageDTO(MessageDTO messageDTO) {
			this.messageDTO = messageDTO;
		}
		public MessageContent getMessageContent() {
			return messageContent;
		}
		public void setMessageContent(MessageContent messageContent) {
			this.messageContent = messageContent;
		}
		
}
