package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="gn_income_detail_history")
public class IncomeDetailHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	 private IncomeDetailHistoryPK pk;
	@Column
	  private String sales_type; // 'C=Contado, A=Abono, M=Mixto'
	@Column
	  private String provider_type; // 'C=Contado, A=Abono, M=Mixto',
	@Column
	  private String sales_freq; // 'M,Q,S,D',
	@Column
	  private Integer times_refill; //'Cuantas veces se surte a la semana',
	@Column
	  private Double provider_amount;// 'Monto aproximado de las compras',
	@Column
	  private Double provider_total;//'Total de compras mensuales',
	@Column
	  private Double detail_1; // 'Detalle 1'
	@Column
	  private Double detail_2;
	@Column
	  private Double detail_3;
	@Column
	  private Double detail_4;
	@Column
	  private Double detail_5;
	@Column
	  private Double detail_6;
	@Column
	  private Double detail_7;
	@Column
	  private Double sales_total;
	@Column
	  private Double profit_before_costs;
	@Column
	  private Double operative_costs;
	@Column
	  private Double cost_rent;
	@Column
	  private Double cost_water;
	@Column
	  private Double cost_electricity;
	@Column
	  private Double cost_gas;
	@Column
	  private Double cost_phone;
	@Column
	  private Double cost_taxes;
	@Column
	  private Double cost_transport;
	@Column
	  private Double cost_maintenance;
	@Column
	  private Double cost_accountant;
	@Column
	  private Double cost_employees;
	@Column
	  private Double cost_other;
	@Column
	  private Double profil_after_costs;
	@Column
	  private Date creation_date;
	public IncomeDetailHistoryPK getPk() {
		return pk;
	}
	public void setPk(IncomeDetailHistoryPK pk) {
		this.pk = pk;
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
	public Double getDetail_1() {
		return detail_1;
	}
	public void setDetail_1(Double detail_1) {
		this.detail_1 = detail_1;
	}
	public Double getDetail_2() {
		return detail_2;
	}
	public void setDetail_2(Double detail_2) {
		this.detail_2 = detail_2;
	}
	public Double getDetail_3() {
		return detail_3;
	}
	public void setDetail_3(Double detail_3) {
		this.detail_3 = detail_3;
	}
	public Double getDetail_4() {
		return detail_4;
	}
	public void setDetail_4(Double detail_4) {
		this.detail_4 = detail_4;
	}
	public Double getDetail_5() {
		return detail_5;
	}
	public void setDetail_5(Double detail_5) {
		this.detail_5 = detail_5;
	}
	public Double getDetail_6() {
		return detail_6;
	}
	public void setDetail_6(Double detail_6) {
		this.detail_6 = detail_6;
	}
	public Double getDetail_7() {
		return detail_7;
	}
	public void setDetail_7(Double detail_7) {
		this.detail_7 = detail_7;
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
	public Double getCost_rent() {
		return cost_rent;
	}
	public void setCost_rent(Double cost_rent) {
		this.cost_rent = cost_rent;
	}
	public Double getCost_water() {
		return cost_water;
	}
	public void setCost_water(Double cost_water) {
		this.cost_water = cost_water;
	}
	public Double getCost_electricity() {
		return cost_electricity;
	}
	public void setCost_electricity(Double cost_electricity) {
		this.cost_electricity = cost_electricity;
	}
	public Double getCost_gas() {
		return cost_gas;
	}
	public void setCost_gas(Double cost_gas) {
		this.cost_gas = cost_gas;
	}
	public Double getCost_phone() {
		return cost_phone;
	}
	public void setCost_phone(Double cost_phone) {
		this.cost_phone = cost_phone;
	}
	public Double getCost_taxes() {
		return cost_taxes;
	}
	public void setCost_taxes(Double cost_taxes) {
		this.cost_taxes = cost_taxes;
	}
	public Double getCost_transport() {
		return cost_transport;
	}
	public void setCost_transport(Double cost_transport) {
		this.cost_transport = cost_transport;
	}
	public Double getCost_maintenance() {
		return cost_maintenance;
	}
	public void setCost_maintenance(Double cost_maintenance) {
		this.cost_maintenance = cost_maintenance;
	}
	public Double getCost_accountant() {
		return cost_accountant;
	}
	public void setCost_accountant(Double cost_accountant) {
		this.cost_accountant = cost_accountant;
	}
	public Double getCost_employees() {
		return cost_employees;
	}
	public void setCost_employees(Double cost_employees) {
		this.cost_employees = cost_employees;
	}
	public Double getCost_other() {
		return cost_other;
	}
	public void setCost_other(Double cost_other) {
		this.cost_other = cost_other;
	}
	public Double getProfil_after_costs() {
		return profil_after_costs;
	}
	public void setProfil_after_costs(Double profil_after_costs) {
		this.profil_after_costs = profil_after_costs;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	
	
}
