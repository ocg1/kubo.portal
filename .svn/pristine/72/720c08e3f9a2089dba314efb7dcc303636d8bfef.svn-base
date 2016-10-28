package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity @Table(name = "gn_credit_history_attempt")
public class CreditHistoryAttempt 
implements Serializable
{
	private static final long serialVersionUID = 990390006859361639L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column int attempt_id;
	
	@Column int company_id;
		
	@Column String user;					//varchar(10)
	@Column String password;				//varchar(10)
	@Column String first_name;				//varchar(45)
	@Column String middle_name;				//varchar(45)
	@Column String father_last_name;		//varchar(45)
	@Column String mother_last_name;		//varchar(45)	
	@Column String mx_rfc;					//varchar(13)
	@Column String street;					//varchar(60)
	@Column String mx_manzana;				//varchar(5)
	@Column String mx_lote;					//varchar(5)
	@Column String address_number;			//varchar(10)
	@Column String apt_number;				//varchar(10)
	@Column String neighborhood_name;		//varchar(60)
	@Column String town_name;				//varchar(60)
	@Column String state_name;				//varchar(60)
	@Column String zip_code;				//varchar(10)
	@Column String phone;					//varchar(45)
	@Column String mx_curp;					//varchar(18)
	@Column String creditcard_four_digits;	//varchar(4)
	@Column String status_res;				//varchar(4)
	@Column String info_res;				//varchar(250)
	@Column String is_check;			//datetime
	
	@Column Integer prospectus_id;
	@Column	Integer mx_lada;				//int(5)	
	@Column Integer creditcard_is_principal;//tinyint(3)	
	@Column Integer mortgage_is_principal;	//tinyint(3)
	@Column Integer car_is_principal;		//tinyint(3)

	@Column Date consultation_date;			//datetime
	@Column java.sql.Date date_of_birth;	//date
	
	public int getAttempt_id() 
	{
		return attempt_id;
	}
	
	public void setAttempt_id(int attempt_id) 
	{
		this.attempt_id = attempt_id;
	}
	
	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public Integer getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getMiddle_name() {
		return middle_name;
	}
	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}
	public String getFather_last_name() {
		return father_last_name;
	}
	public void setFather_last_name(String father_last_name) {
		this.father_last_name = father_last_name;
	}
	public String getMother_last_name() {
		return mother_last_name;
	}
	public void setMother_last_name(String mother_last_name) {
		this.mother_last_name = mother_last_name;
	}
	public java.sql.Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(java.sql.Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public String getMx_rfc() {
		return mx_rfc;
	}
	public void setMx_rfc(String mx_rfc) {
		this.mx_rfc = mx_rfc;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getMx_manzana() {
		return mx_manzana;
	}
	public void setMx_manzana(String mx_manzana) {
		this.mx_manzana = mx_manzana;
	}
	public String getMx_lote() {
		return mx_lote;
	}
	public void setMx_lote(String mx_lote) {
		this.mx_lote = mx_lote;
	}
	public String getAddress_number() {
		return address_number;
	}
	public void setAddress_number(String address_number) {
		this.address_number = address_number;
	}
	public String getApt_number() {
		return apt_number;
	}
	public void setApt_number(String apt_number) {
		this.apt_number = apt_number;
	}
	public String getNeighborhood_name() {
		return neighborhood_name;
	}
	public void setNeighborhood_name(String neighborhood_name) {
		this.neighborhood_name = neighborhood_name;
	}
	public String getTown_name() {
		return town_name;
	}
	public void setTown_name(String town_name) {
		this.town_name = town_name;
	}
	public String getState_name() {
		return state_name;
	}
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	public Integer getMx_lada() {
		return mx_lada;
	}
	public void setMx_lada(Integer mx_lada) {
		this.mx_lada = mx_lada;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMx_curp() {
		return mx_curp;
	}
	public void setMx_curp(String mx_curp) {
		this.mx_curp = mx_curp;
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
	public Integer getMortgage_is_principal() {
		return mortgage_is_principal;
	}
	public void setMortgage_is_principal(Integer mortgage_is_principal) {
		this.mortgage_is_principal = mortgage_is_principal;
	}
	public Integer getCar_is_principal() {
		return car_is_principal;
	}
	public void setCar_is_principal(Integer car_is_principal) {
		this.car_is_principal = car_is_principal;
	}
	public String getStatus_res() {
		return status_res;
	}
	public void setStatus_res(String status_res) {
		this.status_res = status_res;
	}
	public String getInfo_res() {
		return info_res;
	}
	public void setInfo_res(String info_res) {
		this.info_res = info_res;
	}
	public Date getConsultation_date() {
		return consultation_date;
	}
	public void setConsultation_date(Date consultation_date) {
		this.consultation_date = consultation_date;
	}
	public String getIs_check() {
		return is_check;
	}
	public void setIs_check(String is_check) {
		this.is_check = is_check;
	}
}
