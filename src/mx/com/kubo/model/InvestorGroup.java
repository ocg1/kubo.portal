package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="in_investor_group")
public class InvestorGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer investor_group_id; // ` INT UNSIGNED NOT NULL AUTO_INCREMENT,
	@Column
	private String description; // ` VARCHAR(500) NULL,
	@Column
	private String logo_location; // ` VARCHAR(500) NULL,
	@Column
	private Date creation_date; // ` DATETIME NULL,
	@Column
	private String  is_active; // 
	@Column
	private Integer prospectus_id_creator;
	@Column
	private Integer company_id;
	
	@ManyToOne
	@JoinColumns(value = {
			@JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false),
	        		@JoinColumn(name = "prospectus_id_creator", referencedColumnName = "prospectus_id", insertable = false, updatable = false)
				}) 
	private NaturalPerson person;
	
	
	public Integer getInvestor_group_id() {
		return investor_group_id;
	}
	public void setInvestor_group_id(Integer investor_group_id) {
		this.investor_group_id = investor_group_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLogo_location() {
		return logo_location;
	}
	public void setLogo_location(String logo_location) {
		this.logo_location = logo_location;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	public String getIs_active() {
		return is_active;
	}
	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}
	public Integer getProspectus_id_creator() {
		return prospectus_id_creator;
	}
	public void setProspectus_id_creator(Integer prospectus_id_creator) {
		this.prospectus_id_creator = prospectus_id_creator;
	}
	public NaturalPerson getPerson() {
		return person;
	}
	public void setPerson(NaturalPerson person) {
		this.person = person;
	}
	public Integer getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}
	
	
	
}
