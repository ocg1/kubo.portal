package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gn_mx_precio_udi")
public class PrecioUdi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Date creation_date; //` DATETIME NOT NULL,
	@Column
	private Double mx_precio_udi;
	
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	public Double getMx_precio_udi() {
		return mx_precio_udi;
	}
	public void setMx_precio_udi(Double mx_precio_udi) {
		this.mx_precio_udi = mx_precio_udi;
	}
	
}
