package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="in_investment_param_cat")
public class InvestmentParamCat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@EmbeddedId
	private InvestmentParamCatPK pk;
	@Column
	private String description; //` VARCHAR(500) NULL,

	public InvestmentParamCatPK getPk() {
		return pk;
	}

	public void setPk(InvestmentParamCatPK pk) {
		this.pk = pk;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
