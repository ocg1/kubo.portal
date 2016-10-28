package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="gn_income_type")
public class IncomeType implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private IncomeTypePK pk;
	@Column
	private String name;
	@Column
	private Integer operation_sign;
	@Column
	private String obs_income_type;
	@Column 
	private Integer consec; 
	
	public IncomeType(){
		
	}

	public IncomeTypePK getPk() {
		return pk;
	}

	public void setPk(IncomeTypePK pk) {
		this.pk = pk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOperation_sign() {
		return operation_sign;
	}

	public void setOperation_sign(Integer operation_sign) {
		this.operation_sign = operation_sign;
	}

	public String getObs_income_type() {
		return obs_income_type;
	}

	public void setObs_income_type(String obs_income_type) {
		this.obs_income_type = obs_income_type;
	}

	public Integer getConsec() {
		return consec;
	}

	public void setConsec(Integer consec) {
		this.consec = consec;
	}
}