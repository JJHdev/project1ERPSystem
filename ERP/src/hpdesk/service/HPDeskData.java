package hpdesk.service;

import hpdesk.model.HDComm;
import hpdesk.model.HPDesk;

//hpdesk,hdcomm테이블 관련 데이터를 저장,제공하는 클래스
public class HPDeskData {
	private HPDesk hpdesk;
	private HDComm hdcomm;
	public HPDeskData(HPDesk hpdesk, HDComm hdcomm) {
		super();
		this.hpdesk = hpdesk;
		this.hdcomm = hdcomm;
	}
	public HPDesk getHpdesk() {
		return hpdesk;
	}
	public void setHpdesk(HPDesk hpdesk) {
		this.hpdesk = hpdesk;
	}
	public HDComm getHdcomm() {
		return hdcomm;
	}
	public void setHdcomm(HDComm hdcomm) {
		this.hdcomm = hdcomm;
	}
	@Override
	public String toString() {
		return "HPDeskData [hpdesk=" + hpdesk + ", hdcomm=" + hdcomm + "]";
	}


}
