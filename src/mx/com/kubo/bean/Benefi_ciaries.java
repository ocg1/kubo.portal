package mx.com.kubo.bean;

import java.io.Serializable;
import java.util.Date;

public final class Benefi_ciaries implements Serializable 
{
	private static final long serialVersionUID = 2412427606780446330L;

	private AddressBean addressbean;
	
	private Date  date_of_birth;
	
	private String same_address;
	private String middle_name;
	private String father_last_name;
	private String mother_last_name;
	private String first_name;
	private String relationship;	
	private String day;
	private String month;
	private String year;	
	
	private Integer gender_id;
	private Double percentage;
	
	private int varStatus;
	private int prospectus_id;
	private int company_id;
	private int saving_account_id;
	private int beneficiarie_id;	
	
	public String getNombre_completo()
	{
		String nombre = "";
		
		if(first_name != null && first_name.trim().length() > 0)
		{
			nombre += first_name;
		}
		
		if(middle_name != null && middle_name.trim().length() > 0)
		{
			nombre += " "+middle_name;
		}
		
		if(father_last_name != null && father_last_name.trim().length() > 0)
		{
			nombre += " " + father_last_name;
		}
		
		if(mother_last_name != null && mother_last_name.trim().length() > 0)
		{
			nombre += " " + mother_last_name;
		}
		
		return nombre;
	}
	
	public int getProspectus_id() {
		return prospectus_id;
	}
	
	public int getCompany_id() {
		return company_id;
	}
	
	public int getSaving_account_id() {
		return saving_account_id;
	}
	
	public int getBeneficiarie_id() {
		return beneficiarie_id;
	}
	
	public String getRelationship() {
		return relationship;
	}
	
	public Integer getGender_id() {
		return gender_id;
	}
	
	public String getDay() {
		return day;
	}
	
	public String getMonth() {
		return month;
	}
	
	public String getYear() {
		return year;
	}
	
	public Double getPercentage() {
		return percentage;
	}
	
	public String getSame_address() {
		return same_address;
	}
	
	public String getMiddle_name() {
		return middle_name;
	}
	
	public String getFather_last_name() {
		return father_last_name;
	}
	
	public String getMother_last_name() {
		return mother_last_name;
	}
	
	public String getFirst_name() {
		return first_name;
	}
	
	public void setProspectus_id(int prospectus_id) {
		this.prospectus_id = prospectus_id;
	}

	public AddressBean getAddressbean() {
		return addressbean;
	}

	public void setAddressbean(AddressBean address_bean) {
		this.addressbean = address_bean;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
	public void setSaving_account_id(int saving_account_id) {
		this.saving_account_id = saving_account_id;
	}
	
	public void setBeneficiarie_id(int beneficiarie_id) {
		this.beneficiarie_id = beneficiarie_id;
	}
	
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	
	public void setGender_id(Integer gender_id) {
		this.gender_id = gender_id;
	}
	
	public void setDay(String day) {
		this.day = day;
	}
	
	public void setMonth(String month) {
		this.month = month;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	
	public void setSame_address(String same_address) {
		this.same_address = same_address;
	}
	
	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}
	
	public void setFather_last_name(String father_last_name) {
		this.father_last_name = father_last_name;
	}
	
	public void setMother_last_name(String mother_last_name) {
		this.mother_last_name = mother_last_name;
	}
	
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	
	public int getVarStatus() {
		return varStatus;
	}
	
	public void setVarStatus(int varStatus) {
		this.varStatus = varStatus;
	}
}
