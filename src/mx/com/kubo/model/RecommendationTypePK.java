package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class RecommendationTypePK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	private int company_id;
	@Column
	private int recommendation_type_id;
	
	
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
	public int getRecommendation_type_id() {
		return recommendation_type_id;
	}
	public void setRecommendation_type_id(int recommendation_type_id) {
		this.recommendation_type_id = recommendation_type_id;
	}
	
	
}
