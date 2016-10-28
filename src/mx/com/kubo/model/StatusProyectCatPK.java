package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import mx.com.kubo.services.mesa.solicitud.estatus.EstatusProyectLoan;

@Embeddable
public class StatusProyectCatPK 
implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Column
	private int status_id;
	
	@Column
	private int company_id;
	
	public StatusProyectCatPK()
	{
		
	}
	
	public StatusProyectCatPK(EstatusProyectLoan estatus, int company_id)
	{
		this(estatus.getId(), company_id);
	}
	
	public StatusProyectCatPK(int status_id,int company_id)
	{
		this.status_id  = status_id;
		this.company_id = company_id;
	}
	
	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
}
