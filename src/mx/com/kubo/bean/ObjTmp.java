package mx.com.kubo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ObjTmp {
	@Column
	private Long investor;
	@Id
	private Integer credito_id;
	
	public Long getInvestor() {
		return investor;
	}
	public void setInvestor(Long investor) {
		this.investor = investor;
	}
	public Integer getCredito_id() {
		return credito_id;
	}
	public void setCredito_id(Integer credito_id) {
		this.credito_id = credito_id;
	}
}
