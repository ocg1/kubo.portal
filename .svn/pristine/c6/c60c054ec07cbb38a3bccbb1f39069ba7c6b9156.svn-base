package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="gn_reference")
public class References implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ReferencesPK referencePK;
	@Column
	private String 	first_name;
	@Column
	private String 	middle_name;
	@Column
	private String 	father_last_name;
	@Column
	private String 	mother_last_name;
	@Column
	private String phone;
	@Column
	private String email;
	@Column
	private String relationship;
	@Column
	private Integer reference_motive_id;
	@Column
	private Integer reference_score_id;
	
	@ManyToOne
	@JoinColumns(value = {
			@JoinColumn(name = "reference_motive_id", referencedColumnName = "reference_motive_id", insertable = false, updatable = false),
			@JoinColumn(name = "company_id",       referencedColumnName = "company_id",                insertable = false, updatable = false)
	}) private ReferencesMotive motive;
	
	@ManyToOne
	@JoinColumns(value = {
			@JoinColumn(name = "reference_score_id", referencedColumnName = "reference_score_id", insertable = false, updatable = false),
			@JoinColumn(name = "company_id",       referencedColumnName = "company_id",                insertable = false, updatable = false)
	}) private ReferencesScore score;
	
	public References(){
		
	}
	
	public ReferencesPK getReferencePK() {
		return referencePK;
	}
	public void setReferencePK(ReferencesPK referencePK) {
		this.referencePK = referencePK;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getFirst_name() {
		return first_name;
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

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	public void setFather_last_name(String father_last_name) {
		this.father_last_name = father_last_name;
	}

	public void setMother_last_name(String mother_last_name) {
		this.mother_last_name = mother_last_name;
	}

	public Integer getReference_motive_id() {
		return reference_motive_id;
	}

	public void setReference_motive_id(Integer reference_motive_id) {
		this.reference_motive_id = reference_motive_id;
	}

	public Integer getReference_score_id() {
		return reference_score_id;
	}

	public void setReference_score_id(Integer reference_score_id) {
		this.reference_score_id = reference_score_id;
	}

	public ReferencesMotive getMotive() {
		return motive;
	}

	public void setMotive(ReferencesMotive motive) {
		this.motive = motive;
	}

	public ReferencesScore getScore() {
		return score;
	}

	public void setScore(ReferencesScore score) {
		this.score = score;
	}
	
}
