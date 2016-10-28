package mx.com.kubo.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gn_credit_history")
public class CreditHistory 
{
	
	@EmbeddedId
	private CreditHistoryPK CreditHistoryPK;

	@Column private Integer have_credit;            //	TINYINT UNSIGNED
	@Column private Integer first_recent_credit;    //	TINYINT UNSIGNED
	@Column private Integer creditcard_is_principal;//	TINYINT UNSIGNED
	
	@Column private Integer depcc_is_principal;     //	TINYINT UNSIGNED
	@Column private Integer mortgage_is_principal;  //	TINYINT UNSIGNED
	@Column private Integer mortgage_number;        //	INT UNSIGNED
	@Column private Integer car_is_principal;      //	TINYINT UNSIGNED
	@Column private Integer car_number;//	INT UNSIGNED
	@Column private Integer status=0;//	TINYINT UNSIGNED
	
	@Column private String  credit_company_name;//	VARCHAR
	@Column private String  creditcard_four_digits;//	VARCHAR
	@Column private String  creditcard_company;//	VARCHAR
	@Column private String  depcc_company;//	VARCHAR
	@Column private String  mortgage_company;//	VARCHAR
	@Column private String  car_company;//	VARCHAR
	
	@Column private Double  credit_limit;//	DECIMAL	
	@Column private Double  creditcard_limit;//	DECIMAL	
	@Column private Double  depcc_limit;//	DECIMAL
		
	public CreditHistoryPK getCreditHistoryPK() {
		return CreditHistoryPK;
	}
	public void setCreditHistoryPK(CreditHistoryPK creditHistoryPK) {
		CreditHistoryPK = creditHistoryPK;
	}
	public Integer getHave_credit() {
		return have_credit;
	}
	public void setHave_credit(Integer have_credit) {
		this.have_credit = have_credit;
	}
	public Integer getFirst_recent_credit() {
		return first_recent_credit;
	}
	public void setFirst_recent_credit(Integer first_recent_credit) {
		this.first_recent_credit = first_recent_credit;
	}
	public String getCredit_company_name() {
		return credit_company_name;
	}
	public void setCredit_company_name(String credit_company_name) {
		this.credit_company_name = credit_company_name;
	}
	public Double getCredit_limit() {
		return credit_limit;
	}
	public void setCredit_limit(Double credit_limit) {
		this.credit_limit = credit_limit;
	}
	public Integer getCreditcard_is_principal() {
		return creditcard_is_principal;
	}
	public void setCreditcard_is_principal(Integer creditcard_is_principal) {
		this.creditcard_is_principal = creditcard_is_principal;
	}
	public String getCreditcard_four_digits() {
		return creditcard_four_digits;
	}
	public void setCreditcard_four_digits(String creditcard_four_digits) {
		this.creditcard_four_digits = creditcard_four_digits;
	}
	public String getCreditcard_company() {
		return creditcard_company;
	}
	public void setCreditcard_company(String creditcard_company) {
		this.creditcard_company = creditcard_company;
	}
	public Double getCreditcard_limit() {
		return creditcard_limit;
	}
	public void setCreditcard_limit(Double creditcard_limit) {
		this.creditcard_limit = creditcard_limit;
	}
	public Integer getDepcc_is_principal() {
		return depcc_is_principal;
	}
	public void setDepcc_is_principal(Integer depcc_is_principal) {
		this.depcc_is_principal = depcc_is_principal;
	}
	public String getDepcc_company() {
		return depcc_company;
	}
	public void setDepcc_company(String depcc_company) {
		this.depcc_company = depcc_company;
	}
	public Double getDepcc_limit() {
		return depcc_limit;
	}
	public void setDepcc_limit(Double depcc_limit) {
		this.depcc_limit = depcc_limit;
	}
	public Integer getMortgage_is_principal() {
		return mortgage_is_principal;
	}
	public void setMortgage_is_principal(Integer mortgage_is_principal) {
		this.mortgage_is_principal = mortgage_is_principal;
	}
	public String getMortgage_company() {
		return mortgage_company;
	}
	public void setMortgage_company(String mortgage_company) {
		this.mortgage_company = mortgage_company;
	}
	public Integer getMortgage_number() {
		return mortgage_number;
	}
	public void setMortgage_number(Integer mortgage_number) {
		this.mortgage_number = mortgage_number;
	}
	public Integer getCar_is_principal() {
		return car_is_principal;
	}
	public void setCar_is_principal(Integer car_is_principal) {
		this.car_is_principal = car_is_principal;
	}
	public String getCar_company() {
		return car_company;
	}
	public void setCar_company(String car_company) {
		this.car_company = car_company;
	}
	public Integer getCar_number() {
		return car_number;
	}
	public void setCar_number(Integer car_number) {
		this.car_number = car_number;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
