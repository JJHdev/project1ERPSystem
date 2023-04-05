package worktime.model;

import java.sql.Date;

//End_OffDay와Start_OffDay  테이블에 해당하는 데이터를 받아 조인,활용하기 위한 클래스이다.
//-> 받아서 Work_in_Data로 넘겨 사용할수있게한다.

public class OffDay_His {

	private Date start_offday;
	private Date end_offday;
	private int used_offday; //사용한 연차 수량(계산하는 공식이 담긴 자바 페이지 생성필요)
	private int my_offday_count;//사용가능한 연차수량(직급별로 주어진 수량- 본인이 사용한 수량 공식)
	
	//겟터만함 가져오기만함
	public Date getStart_offday() {
		return start_offday;
	}
	public Date getEnd_offday() {
		return end_offday;
	}
	public int getUsed_offday() {
		return used_offday;
	}
	public int getMy_offday_count() {
		return my_offday_count;
	}

	public OffDay_His(Date start_offday, Date end_offday, int used_offday, int my_offday_count) {
		super();
		this.start_offday = start_offday;
		this.end_offday = end_offday;
		this.used_offday = used_offday;
		this.my_offday_count = my_offday_count;
	}
	
	@Override
	public String toString() {
		return "OffDay_His [start_offday=" + start_offday + ", end_offday=" + end_offday + ", used_offday="
				+ used_offday + ", my_offday_count=" + my_offday_count + "]";
	}
	
	
}
