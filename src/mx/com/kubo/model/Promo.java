package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name = "ln_promo")
public class Promo implements Serializable 
{
	private static final long serialVersionUID = -6684237010105053168L;
	
	@EmbeddedId private PromoPK pk;
	
	@Column private String code;
	@Column private String description;
	@Column private String is_enabled;
	
	public PromoPK getPk() {
		return pk;
	}
	
	public void setPk(PromoPK pk) {
		this.pk = pk;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getIs_enabled() {
		return is_enabled;
	}

	public void setIs_enabled(String is_enabled) {
		this.is_enabled = is_enabled;
	}
}
