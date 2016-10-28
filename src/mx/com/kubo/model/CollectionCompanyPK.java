package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CollectionCompanyPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column
	private int company_id;
	@Column
	private int collection_company_id;
	
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public int getCollection_company_id() {
		return collection_company_id;
	}
	public void setCollection_company_id(int collection_company_id) {
		this.collection_company_id = collection_company_id;
	}
	
	
	
}
