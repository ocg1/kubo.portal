package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="in_profile_category")
public class ProfileCategoryInv implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ProfileCategoryInvPK pk;
	@Column
	private String profile_category;
	@Column
	private String saving_limit;
	@Column
	private String top_investment;
	@Column
	private String top_investment_bite;
	@Column
	private Integer min_points;
	@Column
	private Integer max_points;
	
	
	public String getProfile_category() {
		return profile_category;
	}
	public void setProfile_category(String profile_category) {
		this.profile_category = profile_category;
	}
	public String getSaving_limit() {
		return saving_limit;
	}
	public void setSaving_limit(String saving_limit) {
		this.saving_limit = saving_limit;
	}
	public String getTop_investment() {
		return top_investment;
	}
	public void setTop_investment(String top_investment) {
		this.top_investment = top_investment;
	}
	public String getTop_investment_bite() {
		return top_investment_bite;
	}
	public void setTop_investment_bite(String top_investment_bite) {
		this.top_investment_bite = top_investment_bite;
	}
	public ProfileCategoryInvPK getPk() {
		return pk;
	}
	public void setPk(ProfileCategoryInvPK pk) {
		this.pk = pk;
	}
	public Integer getMin_points() {
		return min_points;
	}
	public void setMin_points(Integer min_points) {
		this.min_points = min_points;
	}
	public Integer getMax_points() {
		return max_points;
	}
	public void setMax_points(Integer max_points) {
		this.max_points = max_points;
	}

}
