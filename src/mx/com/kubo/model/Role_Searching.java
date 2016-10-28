package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gn_role_searching_default")
public class Role_Searching implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private Role_Searching_PK pk;
	@Column
	private String status_id;
	@Column
	private String field_order;
	public Role_Searching_PK getPk() {
		return pk;
	}
	public void setPk(Role_Searching_PK pk) {
		this.pk = pk;
	}
	public String getStatus_id() {
		return status_id;
	}
	public void setStatus_id(String status_id) {
		this.status_id = status_id;
	}
	public String getField_order() {
		return field_order;
	}
	public void setField_order(String field_order) {
		this.field_order = field_order;
	} 
	

}
