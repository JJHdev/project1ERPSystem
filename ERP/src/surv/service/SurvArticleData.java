package surv.service;

import surv.model.SurvArticle;
import surv.model.SurvArticleContent;

public class SurvArticleData {
	//필드
	private SurvArticle article;
	private SurvArticleContent survContent;
	//생성자
	public SurvArticleData(SurvArticle article, SurvArticleContent survContent) {
		this.article = article;
		this.survContent = survContent;
	}
	
	public SurvArticle getArticle() {
		return article;
	}

	public SurvArticleContent getSurvContent() {
		return survContent;
	}

	public void setArticle(SurvArticle article) {
		this.article = article;
	}
	public void setSurvContent(SurvArticleContent survContent) {
		this.survContent = survContent;
	}
	@Override
	public String toString() {
		return "SurvArticleData [article=" + article + ", survContent=" + survContent + "]";
	}
	
}
