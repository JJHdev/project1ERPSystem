package surv.service;

import surv.model.SurvArticle;
import surv.model.SurvResult;

public class SurvResultData {

	private SurvArticle article;
	private SurvResult result;
	
	public SurvArticle getArticle() {
		return article;
	}
	public void setArticle(SurvArticle article) {
		this.article = article;
	}
	public SurvResult getResult() {
		return result;
	}
	public void setResult(SurvResult result) {
		this.result = result;
	}
	public SurvResultData(SurvArticle article, SurvResult result) {
		this.article = article;
		this.result = result;
	}
	public SurvResultData(SurvResult result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "SurvResultData [article=" + article + ", result=" + result + "]";
	}
	
	
}
