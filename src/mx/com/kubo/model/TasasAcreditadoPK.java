package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class TasasAcreditadoPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	private int 	tasas_acreditado_id;
	@Column
	private int 	company_id;
	
	
	public int getTasas_acreditado_id() {
		return tasas_acreditado_id;
	}
	public void setTasas_acreditado_id(int tasas_acreditado_id) {
		this.tasas_acreditado_id = tasas_acreditado_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
}
