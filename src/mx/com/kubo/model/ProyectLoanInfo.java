package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ln_proyect_loan_info")
public class ProyectLoanInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ProyectLoanInfoPK pk;
	@Column
	private String mx_ife_domicilio;
	
	public ProyectLoanInfoPK getPk() {
		return pk;
	}
	public void setPk(ProyectLoanInfoPK pk) {
		this.pk = pk;
	}
	public String getMx_ife_domicilio() {
		return mx_ife_domicilio;
	}
	public void setMx_ife_domicilio(String mx_ife_domicilio) {
		this.mx_ife_domicilio = mx_ife_domicilio;
	}

}
