package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity @Table(name = "ln_status_config")
public class EstatusConfig 
implements Serializable
{
	private static final long serialVersionUID = -1314919774185921220L;

	@EmbeddedId
	private EstatusConfigPK estatus_PK;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "status_id",  referencedColumnName = "status_id",  insertable = false, updatable = false),
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
	}) private StatusProyectCat estatus_from;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "status_id_to",  referencedColumnName = "status_id",  insertable = false, updatable = false),
	        @JoinColumn(name = "company_id",    referencedColumnName = "company_id", insertable = false, updatable = false)
	}) private StatusProyectCat estatus_to;

	public void setEstatus_PK(EstatusConfigPK estatus_PK) 
	{
		this.estatus_PK = estatus_PK;
	}
	
	public void setEstatus_from(StatusProyectCat estatus) 
	{
		this.estatus_from = estatus;
	}
	
	public void setEstatus_to(StatusProyectCat estatus) 
	{
		estatus_to = estatus;
	}
	
	public EstatusConfigPK getEstatus_PK() 
	{
		return estatus_PK;
	}

	public StatusProyectCat getEstatus_from() 
	{
		return estatus_from;
	}
	
	public StatusProyectCat getEstatus_to() 
	{
		return estatus_to;
	}	
}
