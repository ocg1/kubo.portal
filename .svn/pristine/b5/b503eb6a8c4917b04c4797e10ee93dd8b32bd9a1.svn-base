package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table( name="pr_movement_notification" )
public class MovementNotification implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private MovementNotificationPK pk;
	@Column
	private Double 		montoMov; // decimal(12,2) DEFAULT NULL,
	@Column
	private Date  		fecha_carga; // datetime DEFAULT NULL,
	@Column
	private Integer 	status_notification; // int(11) DEFAULT NULL,
	
	public MovementNotificationPK getPk() {
		return pk;
	}
	
	public void setPk(MovementNotificationPK pk) {
		this.pk = pk;
	}
	
	public Double getMontoMov() {
		return montoMov;
	}
	
	public void setMontoMov(Double montoMov) {
		this.montoMov = montoMov;
	}
	
	public Date getFecha_carga() {
		return fecha_carga;
	}
	
	public void setFecha_carga(Date fecha_carga) {
		this.fecha_carga = fecha_carga;
	}
	
	public Integer getStatus_notification() {
		return status_notification;
	}
	
	public void setStatus_notification(Integer status_notification) {
		this.status_notification = status_notification;
	}

}
