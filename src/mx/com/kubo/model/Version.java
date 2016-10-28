package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity @Table(name = "pr_version")
public class Version 
implements Serializable
{
	private static final long serialVersionUID = 8316891784324294016L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer version_id;
	
	@Column 
	private Integer company_id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column 
	private Date creation_date;
	
	@Column
	private String description;

	public Integer getVersion_id() 
	{
		return version_id;
	}

	public void setVersion_id(Integer version_id) 
	{
		this.version_id = version_id;
	}

	public Integer getCompany_id() 
	{
		return company_id;
	}

	public void setCompany_id(Integer company_id) 
	{
		this.company_id = company_id;
	}

	public Date getCreation_date() 
	{
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
