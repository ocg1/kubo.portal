package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ln_promo_parameters")
public class PromoParameters implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@EmbeddedId
	private PromoParametersPK pk;
	@Column
	private String name; // , varchar(45), YES, , ,
	@Column
	private String type; //, varchar(100), YES, , , 
	
	public PromoParametersPK getPk() {
		return pk;
	}
	public void setPk(PromoParametersPK pk) {
		this.pk = pk;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
