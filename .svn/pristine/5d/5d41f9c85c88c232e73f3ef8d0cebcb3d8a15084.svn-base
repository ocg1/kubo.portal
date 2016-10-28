package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="in_investor_param")
public class InvestorParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@EmbeddedId
	private InvestorParamPK pk;
	@Column
	private String value_str; // ` VARCHAR(45) NULL,
	@Column
	private Date last_asign_date; // ` DATETIME NULL,
	
	
	public InvestorParamPK getPk() {
		return pk;
	}
	public void setPk(InvestorParamPK pk) {
		this.pk = pk;
	}
	public String getValue_str() {
		return value_str;
	}
	public void setValue_str(String value_str) {
		this.value_str = value_str;
	}
	public Date getLast_asign_date() {
		return last_asign_date;
	}
	public void setLast_asign_date(Date last_asign_date) {
		this.last_asign_date = last_asign_date;
	}
	
}
