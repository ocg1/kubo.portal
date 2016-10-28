package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name = "ln_institutional_investor")
public class InstitutionalInvestor implements Serializable
{
	private static final long serialVersionUID = -8522874610337823973L;

	@EmbeddedId private InstitutionalInvestorPK institutionalInvestorPK;
	
	@Column private String name;
	
	@Column private Integer term_limit;
	@Column private Integer total_credits;
	
	@Column private Double used_limit;
	@Column private Double credit_limit;
	
	@Column private String icon;
	@Column private String description;
	@Column private String short_name;
	
	public InstitutionalInvestor(
			InstitutionalInvestorPK institutionalInvestorPK, String name,
			Double credit_limit, Double used_limit, Integer total_credits,
			String icon, String description, String short_name) 
	{
		this.institutionalInvestorPK = institutionalInvestorPK;
		this.name = name;
		this.credit_limit  = credit_limit;
		this.used_limit    = used_limit;
		this.total_credits = total_credits;
		this.icon = icon;
		this.description = description;
		this.short_name = short_name;
	}
	
	public InstitutionalInvestor()
	{
		super();
	}
	
	public InstitutionalInvestorPK getInstitutionalInvestorPK() 
	{
		return institutionalInvestorPK;
	}
	
	public void setInstitutionalInvestorPK(InstitutionalInvestorPK pk) 
	{
		institutionalInvestorPK = pk;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getCredit_limit() {
		return credit_limit;
	}
	public void setCredit_limit(Double credit_limit) {
		this.credit_limit = credit_limit;
	}
	public Double getUsed_limit() {
		return used_limit;
	}
	public void setUsed_limit(Double used_limit) {
		this.used_limit = used_limit;
	}
	public Integer getTotal_credits() {
		return total_credits;
	}
	public void setTotal_credits(Integer total_credits) {
		this.total_credits = total_credits;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getShort_name() {
		return short_name;
	}

	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}

	public Integer getTerm_limit() {
		return term_limit;
	}

	public void setTerm_limit(Integer term_limit) {
		this.term_limit = term_limit;
	}
	
}
