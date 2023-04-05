package surv.model;

//응답결과 모델
public class SurvResult {
	//필드
	private int answer_no;//응답번호
	private int surv_no;//설문번호
	private int empno;//사원번호
	private int answer;//응답번호
	
	//생성자
	public SurvResult(int answer_no, int surv_no, int empno, int answer) {
		this.answer_no = answer_no;
		this.surv_no = surv_no;
		this.empno = empno;
		this.answer = answer;
	}
	//메서드
	public int getAnswer_no() {
		return answer_no;
	}

	public void setAnswer_no(int answer_no) {
		this.answer_no = answer_no;
	}

	public int getSurv_no() {
		return surv_no;
	}

	public void setSurv_no(int surv_no) {
		this.surv_no = surv_no;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "SurvResult [answer_no=" + answer_no + ", surv_no=" + surv_no + ", empno=" + empno + ", answer=" + answer
				+ "]";
	}
	
	
	
	
	
}
