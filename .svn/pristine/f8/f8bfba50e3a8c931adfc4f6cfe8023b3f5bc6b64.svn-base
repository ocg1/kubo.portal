package mx.com.kubo.model.investor;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity @Table(name = "gn_mov_notification_log")
public class MovimientosLog 
implements Serializable
{
	private static final long serialVersionUID = -9162331371900162204L;
	
	@EmbeddedId
	private MovimientosLogPK pk;

	@Column(name = "folio_mov_notification") 
	private String folio;		
	
	@Column(name = "mov_notification_description") 
	private String description;
	
	@Column(name = "mov_notification_date") @Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	
	@Column(name = "mov_notification_status")
	private String status;

	public final MovimientosLogPK getPk() {
		return pk;
	}

	public final String getFolio() {
		return folio;
	}

	public final String getDescription() {
		return description;
	}

	public final Date getFecha() {
		return fecha;
	}

	public final String getStatus() {
		return status;
	}

	public final void setPk(MovimientosLogPK pk) {
		this.pk = pk;
	}

	public final void setFolio(String folio) {
		this.folio = folio;
	}

	public final void setDescription(String description) {
		this.description = description;
	}

	public final void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public final void setStatus(String status) {
		this.status = status;
	}
	
}
