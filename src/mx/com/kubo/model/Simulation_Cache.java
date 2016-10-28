package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="ln_simulation_cache")
public class Simulation_Cache implements Serializable { 
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private Simulation_Cache_PK pk; //
	
	@Column
	private Double ammount; //	"decimal(12
	@Column
	private Integer term_id; //	"tinyint(3) unsigned"
	@Column
	private Double payment; //	"decimal(12
	@Column
	private Integer frequency_id; //	"tinyint(3) unsigned"
	@Column
	private Integer num_payments; //	"tinyint(3) unsigned"
	@Column
	private Double total_payment; //	"decimal(12
	@Column
	private Double period_rate; //	"decimal(12
	@Column
	private Double yearly_rate; //	"decimal(12
	@Column
	private Double mx_cat; //	"decimal(12
	@Column
	private Date creation_date; //	DATETIME
	@Column 
	private Date consultation_last;	//DATETIME
	@Column
	private Integer consultation_count;	//int Unsigned(4,294,967,295)
	
	public Simulation_Cache(){
		
	}
	
	public Simulation_Cache_PK getPk() {
		return pk;
	}
	public void setPk(Simulation_Cache_PK pk) {
		this.pk = pk;
	}
	public Double getAmmount() {
		return ammount;
	}
	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}
	public Integer getTerm_id() {
		return term_id;
	}
	public void setTerm_id(Integer term_id) {
		this.term_id = term_id;
	}
	public Double getPayment() {
		return payment;
	}
	public void setPayment(Double payment) {
		this.payment = payment;
	}
	public Integer getFrequency_id() {
		return frequency_id;
	}
	public void setFrequency_id(Integer frequency_id) {
		this.frequency_id = frequency_id;
	}
	public Integer getNum_payments() {
		return num_payments;
	}
	public void setNum_payments(Integer num_payments) {
		this.num_payments = num_payments;
	}
	public Double getTotal_payment() {
		return total_payment;
	}
	public void setTotal_payment(Double total_payment) {
		this.total_payment = total_payment;
	}
	public Double getPeriod_rate() {
		return period_rate;
	}
	public void setPeriod_rate(Double period_rate) {
		this.period_rate = period_rate;
	}
	public Double getYearly_rate() {
		return yearly_rate;
	}
	public void setYearly_rate(Double yearly_rate) {
		this.yearly_rate = yearly_rate;
	}
	public Double getMx_cat() {
		return mx_cat;
	}
	public void setMx_cat(Double mx_cat) {
		this.mx_cat = mx_cat;
	}

	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public Date getConsultation_last() {
		return consultation_last;
	}

	public void setConsultation_last(Date consultation_last) {
		this.consultation_last = consultation_last;
	}

	public Integer getConsultation_count() {
		return consultation_count;
	}

	public void setConsultation_count(Integer consultation_count) {
		this.consultation_count = consultation_count;
	}


}
