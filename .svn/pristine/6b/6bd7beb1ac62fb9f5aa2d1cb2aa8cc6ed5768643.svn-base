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
@Table(name="ln_collection_relationship")
public class CollectionRelationship 
implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private CollectionRelationshipPK pk;
	
	@Column
	private String note;
	
	@Column
	private String is_active;
	
	@ManyToOne
	@JoinColumns(value = {
	        @JoinColumn(name = "collection_company_id", referencedColumnName = "collection_company_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id",            referencedColumnName = "company_id",            insertable = false, updatable = false)
	}) private CollectionCompany collectionCompany;
	
	public CollectionRelationshipPK getPk() {
		return pk;
	}
	public void setPk(CollectionRelationshipPK pk) {
		this.pk = pk;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getIs_active() {
		return is_active;
	}
	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}
	public CollectionCompany getCollectionCompany() {
		return collectionCompany;
	}
	public void setCollectionCompany(CollectionCompany collectionCompany) {
		this.collectionCompany = collectionCompany;
	} 
	
}
