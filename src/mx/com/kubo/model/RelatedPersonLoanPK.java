package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RelatedPersonLoanPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	private int company_id; //` TINYINT(3) UNSIGNED NOT NULL,
	@Column
	private int related_person_loan_id;//` INT UNSIGNED NOT NULL,
	
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public int getRelated_person_loan_id() {
		return related_person_loan_id;
	}
	public void setRelated_person_loan_id(int related_person_loan_id) {
		this.related_person_loan_id = related_person_loan_id;
	}
	
}
