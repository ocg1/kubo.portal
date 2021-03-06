package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="in_profile")
public class ProfileInv implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ProfileInvPK pk;
	@Column
	private Date creation_date;
	@Column
	private Integer profile_category_id;
	@Column
	private Integer profile_categor_user_id;
	@Column
	private String no_risk;
	@Column
	private String agree_no_risk;
	@Column
	private Integer total_points;
	@Column
	private Integer other_investment_id; //` INT NULL AFTER `total_points`,
	@Column
	private String other_investment_description;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "profile_category_id", referencedColumnName = "profile_category_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
	    })	
	private ProfileCategoryInv profile_cal;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "profile_categor_user_id", referencedColumnName = "profile_category_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
	    })	
	private ProfileCategoryInv profile_user_sel;
	
	public ProfileInvPK getPk() {
		return pk;
	}
	public void setPk(ProfileInvPK pk) {
		this.pk = pk;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	public Integer getProfile_category_id() {
		return profile_category_id;
	}
	public void setProfile_category_id(Integer profile_category_id) {
		this.profile_category_id = profile_category_id;
	}
	public Integer getProfile_categor_user_id() {
		return profile_categor_user_id;
	}
	public void setProfile_categor_user_id(Integer profile_categor_user_id) {
		this.profile_categor_user_id = profile_categor_user_id;
	}
	public String getNo_risk() {
		return no_risk;
	}
	public void setNo_risk(String no_risk) {
		this.no_risk = no_risk;
	}
	public String getAgree_no_risk() {
		return agree_no_risk;
	}
	public void setAgree_no_risk(String agree_no_risk) {
		this.agree_no_risk = agree_no_risk;
	}
	public Integer getTotal_points() {
		return total_points;
	}
	public void setTotal_points(Integer total_points) {
		this.total_points = total_points;
	}
	public ProfileCategoryInv getProfile_cal() {
		return profile_cal;
	}
	public void setProfile_cal(ProfileCategoryInv profile_cal) {
		this.profile_cal = profile_cal;
	}
	public ProfileCategoryInv getProfile_user_sel() {
		return profile_user_sel;
	}
	public void setProfile_user_sel(ProfileCategoryInv profile_user_sel) {
		this.profile_user_sel = profile_user_sel;
	}
	public Integer getOther_investment_id() {
		return other_investment_id;
	}
	public void setOther_investment_id(Integer other_investment_id) {
		this.other_investment_id = other_investment_id;
	}
	public String getOther_investment_description() {
		return other_investment_description;
	}
	public void setOther_investment_description(String other_investment_description) {
		this.other_investment_description = other_investment_description;
	}

	
}
