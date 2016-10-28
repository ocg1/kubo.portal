package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="ln_loan_negotiation")
public class LoanNegotiation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	*/
	@EmbeddedId
	private LoanNegotiationPK pk;
	
	@Column
	private Character funding_type; //	char(1)	NO
	@Column
	private Double ammount;//	"decimal(12	2)"
	@Column
	private Double min_ammount;//	"decimal(12	2)"
	@Column
	private Integer days_online;//	"tinyint(3) unsigned"	NO
	@Column
	private Integer term_id;//	"tinyint(3) unsigned"	NO
	@Column
	private Integer frequency_id;//	"tinyint(3) unsigned"	NO
	@Column
	private Double payment;//	"decimal(12	2)"
	@Column
	private Double rate;//	"decimal(12	2)"
	@Column
	private String kubo_score_a;//	varchar(1)	NO
	@Column
	private String kubo_score_b;//	varchar(1)	NO
	@Column
	private Double mx_cat;//	"decimal(12	2)"
	@Column
	private Double opening_commission_amount;//	"decimal(12	2)"
	@Column
	private String opening_payment;//	varchar(1)	YES
	@Column
	private Double rate_with_opening;//	"decimal(12	2)"
	@Column
	private Date date_started;//	datetime	YES
	@Column
	private Date  date_finished;//	datetime//	YES
	@Column
	private String status;//	varchar(1)	YES
	@Column
	private Integer prospectus_id_proposed;//	"int(10) unsigned"	YES
	@Column
	private Double total_payment;//	"decimal(12	2)"
	
	public LoanNegotiationPK getPk() {
		return pk;
	}
	public void setPk(LoanNegotiationPK pk) {
		this.pk = pk;
	}
	public Character getFunding_type() {
		return funding_type;
	}
	public void setFunding_type(Character funding_type) {
		this.funding_type = funding_type;
	}
	public Double getAmmount() {
		return ammount;
	}
	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}
	public Double getMin_ammount() {
		return min_ammount;
	}
	public void setMin_ammount(Double min_ammount) {
		this.min_ammount = min_ammount;
	}
	public Integer getDays_online() {
		return days_online;
	}
	public void setDays_online(Integer days_online) {
		this.days_online = days_online;
	}
	public Integer getTerm_id() {
		return term_id;
	}
	public void setTerm_id(Integer term_id) {
		this.term_id = term_id;
	}
	public Integer getFrequency_id() {
		return frequency_id;
	}
	public void setFrequency_id(Integer frequency_id) {
		this.frequency_id = frequency_id;
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
	public String getKubo_score_a() {
		return kubo_score_a;
	}
	public void setKubo_score_a(String kubo_score_a) {
		this.kubo_score_a = kubo_score_a;
	}
	public String getKubo_score_b() {
		return kubo_score_b;
	}
	public void setKubo_score_b(String kubo_score_b) {
		this.kubo_score_b = kubo_score_b;
	}
	public Double getMx_cat() {
		return mx_cat;
	}
	public void setMx_cat(Double mx_cat) {
		this.mx_cat = mx_cat;
	}
	public Double getOpening_commission_amount() {
		return opening_commission_amount;
	}
	public void setOpening_commission_amount(Double opening_commission_amount) {
		this.opening_commission_amount = opening_commission_amount;
	}
	public String getOpening_payment() {
		return opening_payment;
	}
	public void setOpening_payment(String opening_payment) {
		this.opening_payment = opening_payment;
	}
	public Double getRate_with_opening() {
		return rate_with_opening;
	}
	public void setRate_with_opening(Double rate_with_opening) {
		this.rate_with_opening = rate_with_opening;
	}
	public Date getDate_started() {
		return date_started;
	}
	public void setDate_started(Date date_started) {
		this.date_started = date_started;
	}
	public Date getDate_finished() {
		return date_finished;
	}
	public void setDate_finished(Date date_finished) {
		this.date_finished = date_finished;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getProspectus_id_proposed() {
		return prospectus_id_proposed;
	}
	public void setProspectus_id_proposed(Integer prospectus_id_proposed) {
		this.prospectus_id_proposed = prospectus_id_proposed;
	}
	public Double getTotal_payment() {
		return total_payment;
	}
	public void setTotal_payment(Double total_payment) {
		this.total_payment = total_payment;
	}

	
}
