package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LoanNegotiationPK implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Column
	private int proyect_loan_id;//	"int(10) unsigned"	NO
	@Column
	private int proyect_id;//		"int(10) unsigned"	NO
	@Column
	private int prospectus_id;//		"int(10) unsigned"	NO
	@Column
	private int company_id;//		"tinyint(3) unsigned"	NO
	@Column
	private int negotiation_id;//
	
	
	public int getProyect_loan_id() {
		return proyect_loan_id;
	}
	public void setProyect_loan_id(int proyect_loan_id) {
		this.proyect_loan_id = proyect_loan_id;
	}
	public int getProyect_id() {
		return proyect_id;
	}
	public void setProyect_id(int proyect_id) {
		this.proyect_id = proyect_id;
	}
	public int getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(int prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public int getNegotiation_id() {
		return negotiation_id;
	}
	public void setNegotiation_id(int negotiation_id) {
		this.negotiation_id = negotiation_id;
	}
	
}
