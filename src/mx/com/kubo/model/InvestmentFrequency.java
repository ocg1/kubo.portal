package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="in_investment_frequency")
public class InvestmentFrequency implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private InvestmentFrequencyPK pk;
	@Column
	private String frequency_letter;
	@Column
	private String frequency_name;
	@Column
	private Integer order_showed;
	@Column
	private Integer order_investment;
	
	public InvestmentFrequencyPK getPk() {
		return pk;
	}
	public void setPk(InvestmentFrequencyPK pk) {
		this.pk = pk;
	}
	public String getFrequency_letter() {
		return frequency_letter;
	}
	public void setFrequency_letter(String frequency_letter) {
		this.frequency_letter = frequency_letter;
	}
	public String getFrequency_name() {
		return frequency_name;
	}
	public void setFrequency_name(String frequency_name) {
		this.frequency_name = frequency_name;
	}
	public Integer getOrder_showed() {
		return order_showed;
	}
	public void setOrder_showed(Integer order_showed) {
		this.order_showed = order_showed;
	}
	public Integer getOrder_investment() {
		return order_investment;
	}
	public void setOrder_investment(Integer order_investment) {
		this.order_investment = order_investment;
	}

}
