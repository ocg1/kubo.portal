package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ln_collection_stackholder")
public class CollectionStackholder implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private CollectionStackholderPK pk;
	@Column
	private String name;
	
	public CollectionStackholderPK getPk() {
		return pk;
	}
	public void setPk(CollectionStackholderPK pk) {
		this.pk = pk;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
	
}
