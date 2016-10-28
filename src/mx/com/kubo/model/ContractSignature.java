package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gn_contract_signature")
public class ContractSignature implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ContractSignaturePK pk;
	@Column
	private int signature_id;
	@Column
	private Date  contract_showed_date;
	@Column
	private Date  contract_accepted_date;
	@Column
	private Date  contract_signed_date;
	
	public ContractSignaturePK getPk() {
		return pk;
	}
	public void setPk(ContractSignaturePK pk) {
		this.pk = pk;
	}
	public int getSignature_id() {
		return signature_id;
	}
	public void setSignature_id(int signature_id) {
		this.signature_id = signature_id;
	}
	public Date getContract_showed_date() {
		return contract_showed_date;
	}
	public void setContract_showed_date(Date contract_showed_date) {
		this.contract_showed_date = contract_showed_date;
	}
	public Date getContract_accepted_date() {
		return contract_accepted_date;
	}
	public void setContract_accepted_date(Date contract_accepted_date) {
		this.contract_accepted_date = contract_accepted_date;
	}
	public Date getContract_signed_date() {
		return contract_signed_date;
	}
	public void setContract_signed_date(Date contract_signed_date) {
		this.contract_signed_date = contract_signed_date;
	}
	
}
