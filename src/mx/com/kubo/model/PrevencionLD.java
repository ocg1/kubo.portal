package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name = "gn_mx_pld")
public class PrevencionLD 
implements Serializable 
{
	private static final long serialVersionUID = 5043183131195782771L;
	
	@EmbeddedId private PrevencionLDPK pk;
		
	@Column private Double maximum_deposit;
	@Column private Double maximum_withdraw;
	@Column private Double maximum_balance;
	
	@Column private Integer account_purpose_id;
	
	@Column private String safi_account_id;
	@Column private String movement_frequency;
	@Column private String deposit_method;
	@Column private String resource_origin;
	@Column private String account_purpose_other;
	@Column private String principal_income;
	@Column private String aprox_monthly_income;
	@Column private String has_previous_account;
	@Column private String has_kubo_relative;
	@Column private String kubo_relationship;
	@Column private String kubo_first_name;
	@Column private String kubo_father_last_name;
	@Column private String kubo_mother_last_name;
	@Column private String has_pep_relative;
	@Column private String pep_relationship;
	@Column private String pep_first_name;
	@Column private String pep_father_last_name;
	@Column private String pep_mother_last_name;
	@Column private String for_third_party;
	@Column private String pr_first_name;
	@Column private String pr_father_last_name;
	@Column private String pr_mother_last_name;
	
	public PrevencionLDPK getPk() {
		return pk;
	}
	public void setPk(PrevencionLDPK pk) {
		this.pk = pk;
	}
	public String getSafi_account_id() {
		return safi_account_id;
	}
	public void setSafi_account_id(String safi_account_id) {
		this.safi_account_id = safi_account_id;
	}
	public String getMovement_frequency() {
		return movement_frequency;
	}
	public void setMovement_frequency(String movement_frequency) {
		this.movement_frequency = movement_frequency;
	}
	public String getDeposit_method() {
		return deposit_method;
	}
	public void setDeposit_method(String deposit_method) {
		this.deposit_method = deposit_method;
	}
	public Double getMaximum_deposit() {
		return maximum_deposit;
	}
	public void setMaximum_deposit(Double maximum_deposit) {
		this.maximum_deposit = maximum_deposit;
	}
	public Double getMaximum_withdraw() {
		return maximum_withdraw;
	}
	public void setMaximum_withdraw(Double maximum_withdraw) {
		this.maximum_withdraw = maximum_withdraw;
	}
	public Double getMaximum_balance() {
		return maximum_balance;
	}
	public void setMaximum_balance(Double maximum_balance) {
		this.maximum_balance = maximum_balance;
	}
	public String getResource_origin() {
		return resource_origin;
	}
	public void setResource_origin(String resource_origin) {
		this.resource_origin = resource_origin;
	}
	public Integer getAccount_purpose_id() {
		return account_purpose_id;
	}
	public void setAccount_purpose_id(Integer account_purpose_id) {
		this.account_purpose_id = account_purpose_id;
	}
	public String getAccount_purpose_other() {
		return account_purpose_other;
	}
	public void setAccount_purpose_other(String account_purpose_other) {
		this.account_purpose_other = account_purpose_other;
	}
	public String getPrincipal_income() {
		return principal_income;
	}
	public void setPrincipal_income(String principal_income) {
		this.principal_income = principal_income;
	}
	public String getAprox_monthly_income() {
		return aprox_monthly_income;
	}
	public void setAprox_monthly_income(String aprox_monthly_income) {
		this.aprox_monthly_income = aprox_monthly_income;
	}
	public String getHas_previous_account() {
		return has_previous_account;
	}
	public void setHas_previous_account(String has_previous_account) {
		this.has_previous_account = has_previous_account;
	}
	public String getHas_kubo_relative() {
		return has_kubo_relative;
	}
	public void setHas_kubo_relative(String has_kubo_relative) {
		this.has_kubo_relative = has_kubo_relative;
	}
	public String getKubo_relationship() {
		return kubo_relationship;
	}
	public void setKubo_relationship(String kubo_relationship) {
		this.kubo_relationship = kubo_relationship;
	}
	public String getKubo_first_name() {
		return kubo_first_name;
	}
	public void setKubo_first_name(String kubo_first_name) {
		this.kubo_first_name = kubo_first_name;
	}
	public String getKubo_father_last_name() {
		return kubo_father_last_name;
	}
	public void setKubo_father_last_name(String kubo_father_last_name) {
		this.kubo_father_last_name = kubo_father_last_name;
	}
	public String getKubo_mother_last_name() {
		return kubo_mother_last_name;
	}
	public void setKubo_mother_last_name(String kubo_mother_last_name) {
		this.kubo_mother_last_name = kubo_mother_last_name;
	}
	public String getHas_pep_relative() {
		return has_pep_relative;
	}
	public void setHas_pep_relative(String has_pep_relative) {
		this.has_pep_relative = has_pep_relative;
	}
	public String getPep_relationship() {
		return pep_relationship;
	}
	public void setPep_relationship(String pep_relationship) {
		this.pep_relationship = pep_relationship;
	}
	public String getPep_first_name() {
		return pep_first_name;
	}
	public void setPep_first_name(String pep_first_name) {
		this.pep_first_name = pep_first_name;
	}
	public String getPep_father_last_name() {
		return pep_father_last_name;
	}
	public void setPep_father_last_name(String pep_father_last_name) {
		this.pep_father_last_name = pep_father_last_name;
	}
	public String getPep_mother_last_name() {
		return pep_mother_last_name;
	}
	public void setPep_mother_last_name(String pep_mother_last_name) {
		this.pep_mother_last_name = pep_mother_last_name;
	}
	public String getFor_third_party() {
		return for_third_party;
	}
	public void setFor_third_party(String for_third_party) {
		this.for_third_party = for_third_party;
	}
	public String getPr_first_name() {
		return pr_first_name;
	}
	public void setPr_first_name(String pr_first_name) {
		this.pr_first_name = pr_first_name;
	}
	public String getPr_father_last_name() {
		return pr_father_last_name;
	}
	public void setPr_father_last_name(String pr_father_last_name) {
		this.pr_father_last_name = pr_father_last_name;
	}
	public String getPr_mother_last_name() {
		return pr_mother_last_name;
	}
	public void setPr_mother_last_name(String pr_mother_last_name) {
		this.pr_mother_last_name = pr_mother_last_name;
	}
}
