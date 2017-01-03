package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "gn_legal_limit")
public class LegalLimit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private LegalLimitPK pk;
	@Column
	private String description;
	@Column
	private String value;
	
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public LegalLimitPK getPk() {
		return pk;
	}
	public void setPk(LegalLimitPK pk) {
		this.pk = pk;
	}
	
	
}
