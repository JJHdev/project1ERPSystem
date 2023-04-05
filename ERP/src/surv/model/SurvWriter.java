package surv.model;

public class SurvWriter {
	
	private int survWriter_no;///사원번호
	private String survWriter_name;//사원이름
	
	public SurvWriter(int survWriter_no, String survWriter_name) {
		this.survWriter_no = survWriter_no;
		this.survWriter_name = survWriter_name;
	}
	public int getSurvWriter_no() {
		return survWriter_no;
	}
	public String getSurvWriter_name() {
		return survWriter_name;
	}
	
	
}
