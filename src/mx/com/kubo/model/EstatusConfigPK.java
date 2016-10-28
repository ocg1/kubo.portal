package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class EstatusConfigPK 
implements Serializable
{
	@Column Integer status_id;
	@Column Integer company_id;
	@Column Integer status_id_to;
	
	public Integer getStatus_id() 
	{
		return status_id;
	}
	
	public void setStatus_id(Integer status_id) 
	{
		this.status_id = status_id;
	}
	
	public Integer getCompany_id() 
	{
		return company_id;
	}
	
	public void setCompany_id(Integer company_id) 
	{
		this.company_id = company_id;
	}
	
	public Integer getStatus_id_to() 
	{
		return status_id_to;
	}
	
	public void setStatus_id_to(Integer status_id_to) 
	{
		this.status_id_to = status_id_to;
	}
}
