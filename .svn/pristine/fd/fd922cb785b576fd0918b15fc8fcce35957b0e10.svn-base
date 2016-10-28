package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gn_income_detail")
public class IncomeDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private IncomeDetailPK incomDetailPk;
	
	@Column
	private String sales_type;//	varchar(1)	YES
	@Column
	private String provider_type;//	varchar(1)	YES
	@Column
	private String sales_freq;//	varchar(1)	YES
	@Column
	private Integer times_refill;//	int(11)	YES
	@Column
	private Integer times_refill_modified;
	@Column
	private Integer times_refill_prospectus_id_modified;
	@Column
	private Date times_refill_datetime_modified;
	@Column
	private Double provider_amount;//	"double(12	2)"	YES
	@Column
	private Double provider_total;//	"double(12	2)"	YES
	@Column
	private Double provider_total_modified;
	@Column
	private Integer provider_total_prospectus_id_modified;
	@Column
	private Date provider_total_datetime_modified;
	@Column
	private Double sales_total;//	"double(12	2)"	YES
	@Column
	private Double profit_before_costs;//	"double(12	2)"	YES
	@Column
	private Double operative_costs;//	"double(12	2)"	YES
	@Column
	private Double profil_after_costs;//	"double(12	2)"	YES
		
	public IncomeDetailPK getIncomDetailPk() {
		return incomDetailPk;
	}
	public void setIncomDetailPk(IncomeDetailPK incomDetailPk) {
		this.incomDetailPk = incomDetailPk;
	}
	public String getSales_type() {
		return sales_type;
	}
	public void setSales_type(String sales_type) {
		this.sales_type = sales_type;
	}
	public String getProvider_type() {
		return provider_type;
	}
	public void setProvider_type(String provider_type) {
		this.provider_type = provider_type;
	}
	public String getSales_freq() {
		return sales_freq;
	}
	public void setSales_freq(String sales_freq) {
		this.sales_freq = sales_freq;
	}
	public Integer getTimes_refill() {
		return times_refill;
	}
	public void setTimes_refill(Integer times_refill) {
		this.times_refill = times_refill;
	}
	public Double getProvider_amount() {
		return provider_amount;
	}
	public void setProvider_amount(Double provider_amount) {
		this.provider_amount = provider_amount;
	}
	public Double getProvider_total() {
		return provider_total;
	}
	public void setProvider_total(Double provider_total) {
		this.provider_total = provider_total;
	}
	public Double getSales_total() {
		return sales_total;
	}
	public void setSales_total(Double sales_total) {
		this.sales_total = sales_total;
	}
	public Double getProfit_before_costs() {
		return profit_before_costs;
	}
	public void setProfit_before_costs(Double profit_before_costs) {
		this.profit_before_costs = profit_before_costs;
	}
	public Double getOperative_costs() {
		return operative_costs;
	}
	public void setOperative_costs(Double operative_costs) {
		this.operative_costs = operative_costs;
	}
	public Double getProfil_after_costs() {
		return profil_after_costs;
	}
	public void setProfil_after_costs(Double profil_after_costs) {
		this.profil_after_costs = profil_after_costs;
	}
	public Integer getTimes_refill_modified() {
		return times_refill_modified;
	}
	public void setTimes_refill_modified(Integer times_refill_modified) {
		this.times_refill_modified = times_refill_modified;
	}
	public Integer getTimes_refill_prospectus_id_modified() {
		return times_refill_prospectus_id_modified;
	}
	public void setTimes_refill_prospectus_id_modified(Integer times_refill_prospectus_id_modified) {
		this.times_refill_prospectus_id_modified = times_refill_prospectus_id_modified;
	}
	public Date getTimes_refill_datetime_modified() {
		return times_refill_datetime_modified;
	}
	public void setTimes_refill_datetime_modified(Date times_refill_datetime_modified) {
		this.times_refill_datetime_modified = times_refill_datetime_modified;
	}
	public Double getProvider_total_modified() {
		return provider_total_modified;
	}
	public void setProvider_total_modified(Double provider_total_modified) {
		this.provider_total_modified = provider_total_modified;
	}
	public Integer getProvider_total_prospectus_id_modified() {
		return provider_total_prospectus_id_modified;
	}
	public void setProvider_total_prospectus_id_modified(Integer provider_total_prospectus_id_modified) {
		this.provider_total_prospectus_id_modified = provider_total_prospectus_id_modified;
	}
	public Date getProvider_total_datetime_modified() {
		return provider_total_datetime_modified;
	}
	public void setProvider_total_datetime_modified(Date provider_total_datetime_modified) {
		this.provider_total_datetime_modified = provider_total_datetime_modified;
	}
	
}