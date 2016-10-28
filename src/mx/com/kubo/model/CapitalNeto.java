package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="in_mx_capital_neto")
public class CapitalNeto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column
	Date creation_date;
	
	@Column
	Double mx_capital_neto;

	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public Double getMx_capital_neto() {
		return mx_capital_neto;
	}

	public void setMx_capital_neto(Double mx_capital_neto) {
		this.mx_capital_neto = mx_capital_neto;
	}
	
}
