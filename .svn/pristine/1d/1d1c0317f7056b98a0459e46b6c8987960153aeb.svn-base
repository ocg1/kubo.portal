package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="ln_comparison")

public class ComparisonMod implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ComparisonModPK comparisonPK;
	
	@Column
	private Integer frequency_id;
	@Column
	private String institution_name_other;
	@Column
	private Double total_payment_kubo;
	@Column
	private Double total_payment_other;
	@Column
	private Double ammount;
	@Column
	private Double payment;
	@Column
	private Integer num_payments;
	@Column
	private Double rate;
	@Column
	private Double mx_cat;
	@Column
	private Date creation_date;
	

	public Integer getFrequency_id() {
		return frequency_id;
	}
	public void setFrequency_id(Integer frequency_id) {
		this.frequency_id = frequency_id;
	}
	public ComparisonModPK getComparisonPK() {
		return comparisonPK;
	}
	public void setComparisonPK(ComparisonModPK comparisonPK) {
		this.comparisonPK = comparisonPK;
	}
	public String getInstitution_name_other() {
		return institution_name_other;
	}
	public void setInstitution_name_other(String institution_name_other) {
		this.institution_name_other = institution_name_other;
	}
	public Integer getNum_payments() {
		return num_payments;
	}
	public void setNum_payments(Integer num_payments) {
		this.num_payments = num_payments;
	}
	public Double getMx_cat() {
		return mx_cat;
	}
	public void setMx_cat(Double mx_cat) {
		this.mx_cat = mx_cat;
	}
	public Double getTotal_payment_kubo() {
		return total_payment_kubo;
	}
	public void setTotal_payment_kubo(Double total_payment_kubo) {
		this.total_payment_kubo = total_payment_kubo;
	}
	public Double getTotal_payment_other() {
		return total_payment_other;
	}
	public void setTotal_payment_other(Double total_payment_other) {
		this.total_payment_other = total_payment_other;
	}
	public Double getAmmount() {
		return ammount;
	}
	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}
	public Double getPayment() {
		return payment;
	}
	public void setPayment(Double payment) {
		this.payment = payment;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	
	
}
