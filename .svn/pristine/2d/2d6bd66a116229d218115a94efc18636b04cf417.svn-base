package mx.com.kubo.model;//

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="ln_simulation")
public class SimulatorBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private SimulatorPK simulatorPK;
	@Column
	private Integer sim_order;////	"tinyint(3) unsigned"
	
	@Column
	private Date date;//	date
	@Column
	private String title;//	varchar(35)
	@Column
	private char type;//	char(1)
	@Column
	private Integer type_id;//	"smallint(5) unsigned"
	@Column
	private Integer purpose_id;//	"smallint(5) unsigned"
	@Column
	private Double ammount;//	"decimal(12
	@Column
	private Integer bc_score;//	"smallint(5) unsigned"
	@Column
	private Integer term_id;//	"tinyint(3) unsigned"
	@Column
	private Double payment;//	"decimal(12
	@Column
	private Integer frequency_id;//	"tinyint(3) unsigned"
	@Column
	private Integer num_payments;//	"tinyint(3) unsigned"
	@Column
	private Double total_payment;//	"decimal(12
	@Column
	private Double period_rate;//	"decimal(12
	@Column
	private Double yearly_rate;//	"decimal(12
	@Column
	private Double total_interest;//	"decimal(12
	@Column
	private Double yearly_interest;//	"decimal(12
	@Column
	private Double mx_cat;//	"decimal(12
	@Column
	private String origin;//	"decimal(12
	
	
	public SimulatorBean(){
		
	}
	
	public SimulatorPK getSimulatorPK() {
		return simulatorPK;
	}
	public void setSimulatorPK(SimulatorPK simulatorPK) {
		this.simulatorPK = simulatorPK;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	public Integer getPurpose_id() {
		return purpose_id;
	}
	public void setPurpose_id(Integer purpose_id) {
		this.purpose_id = purpose_id;
	}
	public Double getAmmount() {
		return ammount;
	}
	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}
	public Integer getBc_score() {
		return bc_score;
	}
	public void setBc_score(Integer bc_score) {
		this.bc_score = bc_score;
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
	public Double getTotal_interest() {
		return total_interest;
	}
	public void setTotal_interest(Double total_interest) {
		this.total_interest = total_interest;
	}
	public Double getYearly_interest() {
		return yearly_interest;
	}
	public void setYearly_interest(Double yearly_interest) {
		this.yearly_interest = yearly_interest;
	}

	public Integer getSim_order() {
		return sim_order;
	}

	public void setSim_order(Integer sim_order) {
		this.sim_order = sim_order;
	}

	public Double getMx_cat() {
		return mx_cat;
	}

	public void setMx_cat(Double mx_cat) {
		this.mx_cat = mx_cat;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	
	
}
