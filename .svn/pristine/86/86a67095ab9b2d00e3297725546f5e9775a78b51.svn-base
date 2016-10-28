package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name = "gn_authentication_pool")
public class AuthenticationPool 
implements Serializable 
{
	private static final long serialVersionUID = 9138660374932693428L;
	
	@EmbeddedId private AuthenticationPoolPK pk;
	
	@Column private String reference_table;
	@Column private String field;
	@Column private String description;
	
	public AuthenticationPoolPK getPk() {
		return pk;
	}
	
	public void setPk(AuthenticationPoolPK pk) {
		this.pk = pk;
	}
	
	public String getReference_table() {
		return reference_table;
	}
	
	public void setReference_table(String reference_table) {
		this.reference_table = reference_table;
	}
	
	public String getField() {
		return field;
	}
	
	public void setField(String field) {
		this.field = field;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
