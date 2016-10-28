package mx.com.kubo.model.investor;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="in_other_investment")
public class OtherinvestmentCat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private OtherinvestmentCatPK pk;
	@Column
	private  String description;
	public OtherinvestmentCatPK getPk() {
		return pk;
	}
	public void setPk(OtherinvestmentCatPK pk) {
		this.pk = pk;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
