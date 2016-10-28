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

@SuppressWarnings("serial")
@Entity @Table(name = "ln_status_change_log")
public class EstatusChangeLog 
implements Serializable
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long status_change_log_id;
	
	@Column private Integer company_id;
	@Column private Integer prospectus_id;
	@Column private Integer proyect_loan_id;	
	@Column private Integer proyect_id;
	@Column private Integer event_id;	
	@Column private Integer status_id;
	@Column private Integer change_prospectus_id;
	@Column private Integer change_status_id;
	@Column private Integer change_id;
		
	@Column @Temporal(TemporalType.TIMESTAMP) 
	private Date change_date;

	public final Long getStatus_change_log_id() 
	{
		return status_change_log_id;
	}
	
	public final Integer getCompany_id() 
	{
		return company_id;
	}
	
	public final Integer getProspectus_id() 
	{
		return prospectus_id;
	}
	
	public final Integer getProyect_loan_id() 
	{
		return proyect_loan_id;
	}
	
	public final Integer getProyect_id() 
	{
		return proyect_id;
	}
	
	public final Integer getStatus_id() 
	{
		return status_id;
	}

	public final Date getChange_date() 
	{
		return change_date;
	}
	
	public final Integer getChange_prospectus_id() 
	{
		return change_prospectus_id;
	}
	
	public final Integer getChange_status_id() 
	{
		return change_status_id;
	}
	
	public final Integer getEvent_id() 
	{
		return event_id;
	}
	
	public final Integer getChange_id() 
	{
		return change_id;
	}

	public final void setChange_id(Integer change_id) 
	{
		this.change_id = change_id;
	}

	public final void setEvent_id(Integer event_id) 
	{
		this.event_id = event_id;
	}

	public final void setChange_status_id(Integer change_status_id) 
	{
		this.change_status_id = change_status_id;
	}

	public final void setChange_prospectus_id(Integer change_prospectus_id) 
	{
		this.change_prospectus_id = change_prospectus_id;
	}

	public final void setStatus_change_log_id(Long status_change_id) 
	{
		this.status_change_log_id = status_change_id;
	}
	
	public final void setCompany_id(Integer company_id) 
	{
		this.company_id = company_id;
	}
	
	public final void setProspectus_id(Integer prospectus_id) 
	{
		this.prospectus_id = prospectus_id;
	}
	
	public final void setProyect_loan_id(Integer proyect_loan_id) 
	{
		this.proyect_loan_id = proyect_loan_id;
	}
	
	public final void setProyect_id(Integer proyect_id) 
	{
		this.proyect_id = proyect_id;
	}
	
	public final void setStatus_id(Integer status_id) 
	{
		this.status_id = status_id;
	}

	public final void setChange_date(Date change_date) 
	{
		this.change_date = change_date;
	}
}
