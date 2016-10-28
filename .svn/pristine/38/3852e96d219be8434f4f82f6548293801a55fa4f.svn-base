package mx.com.kubo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQuery(name="queryAddBenefic",
query="select MAX(e.beneficPk.beneficiarie_id) from Beneficiaries e where e.beneficPk.prospectus_id=? and e.beneficPk.company_id=?"
)
@Entity @Table(name = "gn_beneficiaries")
public class Beneficiaries implements Serializable 
{
	private static final long serialVersionUID = 5412557665910408886L;

	@EmbeddedId
	private BeneficiariesPK beneficPk;
	
	@Column private Integer gender_id;
	
	@Column private Date date_of_birth;	
	
	@Column private BigDecimal percentage;
	
	@Column private String relationship;
	@Column private String same_address;
	@Column private String middle_name;
	@Column private String father_last_name;
	@Column private String mother_last_name;
	@Column private String first_name;
	
	
	public Beneficiaries(){
		
	}
	
	public BeneficiariesPK getBeneficPk() {
		return beneficPk;
	}
	public void setBeneficPk(BeneficiariesPK beneficPk) {
		this.beneficPk = beneficPk;
	}


	public String getFirst_name() {
		return first_name;
	}

	public String getRelationship() {
		return relationship;
	}

	public Integer getGender_id() {
		return gender_id;
	}

	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public BigDecimal getPercentage() {
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

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public void setGender_id(Integer gender_id) {
		this.gender_id = gender_id;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public void setPercentage(BigDecimal percentage) {
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

}
