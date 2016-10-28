package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity @Table(name = "gn_business")
public class Business 
implements Serializable  
{	
	private static final long serialVersionUID = -2254818150234912596L;

	@EmbeddedId private BusinessPK businessPK;
	
	@OneToOne
	@JoinColumns(value = {
	    @JoinColumn(name = "bmx_econ_activity_id", referencedColumnName = "bmx_econ_activity_id", insertable = false, updatable = false),
		@JoinColumn(name = "company_id",           referencedColumnName = "company_id",           insertable = false, updatable = false)
	})private BmxEconActivityCat bmxEconActivityCat;
	
	@OneToOne(mappedBy = "business")
	private Address addressBusiness;
	
	@Column private Integer inegi_econ_activity_id;//	varchar(45)	YES
	@Column private Integer econ_sector_id;//	varchar(45)	YES
	@Column private Integer years_exp;//	"tinyint(3) unsigned"	YES
	@Column private Integer months_exp;//	"tinyint(3) unsigned"	YES
	@Column private Integer company_relationship_id;//	"tinyint(3) unsigned"	YES	MUL
	@Column private Integer years_since_when;
	@Column private Integer employee_number;
	@Column private Integer months_since_when;
	@Column private Integer tenure_id;
	
	@Column private String business_name;//	varchar(100)	YES
	@Column private String business_description;
	@Column private String about_your_business;
	@Column private String experience_detail;
	@Column private String since_when_detail;
	@Column private String business_goal;
	@Column private String bmx_econ_activity_id;
	@Column private String sells_cold_cuts;
	@Column private String sells_cold_milk;
	@Column private String sells_other_cold_prods;
	@Column private String sells_sigma;
	@Column private String has_sigma_fridge;
	@Column private String sells_no_sigma;
	@Column private String sigma_number;
	

	public BusinessPK getBusinessPK() {
		return businessPK;
	}
	public void setBusinessPK(BusinessPK businessPK) {
		this.businessPK = businessPK;
	}
	public String getBusiness_name() {
		return business_name;
	}
	public void setBusiness_name(String business_name) {
		this.business_name = business_name;
	}
	public Integer getYears_exp() {
		return years_exp;
	}
	public void setYears_exp(Integer years_exp) {
		this.years_exp = years_exp;
	}
	public Integer getMonths_exp() {
		return months_exp;
	}
	public void setMonths_exp(Integer months_exp) {
		this.months_exp = months_exp;
	}
	public Integer getCompany_relationship_id() {
		return company_relationship_id;
	}
	public void setCompany_relationship_id(Integer company_relationship_id) {
		this.company_relationship_id = company_relationship_id;
	}
	public Integer getInegi_econ_activity_id() {
		return inegi_econ_activity_id;
	}
	public void setInegi_econ_activity_id(Integer inegi_econ_activity_id) {
		this.inegi_econ_activity_id = inegi_econ_activity_id;
	}
	public Integer getEcon_sector_id() {
		return econ_sector_id;
	}
	public void setEcon_sector_id(Integer econ_sector_id) {
		this.econ_sector_id = econ_sector_id;
	}
	public String getBusiness_description() {
		return business_description;
	}
	public void setBusiness_description(String business_description) {
		this.business_description = business_description;
	}
	public String getAbout_your_business() {
		return about_your_business;
	}
	public void setAbout_your_business(String about_your_business) {
		this.about_your_business = about_your_business;
	}
	public String getExperience_detail() {
		return experience_detail;
	}
	public void setExperience_detail(String experience_detail) {
		this.experience_detail = experience_detail;
	}
	public String getSince_when_detail() {
		return since_when_detail;
	}
	public void setSince_when_detail(String since_when_detail) {
		this.since_when_detail = since_when_detail;
	}
	public Integer getYears_since_when() {
		return years_since_when;
	}
	public void setYears_since_when(Integer years_since_when) {
		this.years_since_when = years_since_when;
	}
	public Integer getEmployee_number() {
		return employee_number;
	}
	public void setEmployee_number(Integer employee_number) {
		this.employee_number = employee_number;
	}
	public String getBusiness_goal() {
		return business_goal;
	}
	public void setBusiness_goal(String business_goal) {
		this.business_goal = business_goal;
	}
	public String getBmx_econ_activity_id() {
		return bmx_econ_activity_id;
	}
	public void setBmx_econ_activity_id(String bmx_econ_activity_id) {
		this.bmx_econ_activity_id = bmx_econ_activity_id;
	}
	public Address getAddressBusiness() {
		return addressBusiness;
	}
	public void setAddressBusiness(Address addressBusiness) {
		this.addressBusiness = addressBusiness;
	}
	public Integer getMonths_since_when() {
		return months_since_when;
	}
	public void setMonths_since_when(Integer months_since_when) {
		this.months_since_when = months_since_when;
	}
	
	public final Integer getTenure_id() {
		return tenure_id;
	}
	
	public final void setTenure_id(Integer tenure_id) 
	{
		if(tenure_id > 0)
		{
			this.tenure_id = tenure_id;
			
		} else {
			
			this.tenure_id = null;
		}
	}
	
	public BmxEconActivityCat getBmxEconActivityCat() {
		return bmxEconActivityCat;
	}
	public void setBmxEconActivityCat(BmxEconActivityCat bmxEconActivityCat) {
		this.bmxEconActivityCat = bmxEconActivityCat;
	}
	public String getSells_cold_cuts() {
		return sells_cold_cuts;
	}
	public void setSells_cold_cuts(String sells_cold_cuts) {
		this.sells_cold_cuts = sells_cold_cuts;
	}
	public String getSells_cold_milk() {
		return sells_cold_milk;
	}
	public void setSells_cold_milk(String sells_cold_milk) {
		this.sells_cold_milk = sells_cold_milk;
	}
	public String getSells_other_cold_prods() {
		return sells_other_cold_prods;
	}
	public void setSells_other_cold_prods(String sells_other_cold_prods) {
		this.sells_other_cold_prods = sells_other_cold_prods;
	}
	public String getSells_sigma() {
		return sells_sigma;
	}
	public void setSells_sigma(String sells_sigma) {
		this.sells_sigma = sells_sigma;
	}
	public String getHas_sigma_fridge() {
		return has_sigma_fridge;
	}
	public void setHas_sigma_fridge(String has_sigma_fridge) {
		this.has_sigma_fridge = has_sigma_fridge;
	}
	public String getSells_no_sigma() {
		return sells_no_sigma;
	}
	public void setSells_no_sigma(String sells_no_sigma) {
		this.sells_no_sigma = sells_no_sigma;
	}
	public String getSigma_number() {
		return sigma_number;
	}
	public void setSigma_number(String sigma_number) {
		this.sigma_number = sigma_number;
	}
	
}
