package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gn_contact_way")
public class ContactWay implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ContactWayPK pk;
	@Column
	private String 		description;
	@Column
	private String 		enabled;
	@Column
	private Integer 	menu_order;
	
	public ContactWayPK getPk() {
		return pk;
	}
	public void setPk(ContactWayPK pk) {
		this.pk = pk;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public Integer getMenu_order() {
		return menu_order;
	}
	public void setMenu_order(Integer menu_order) {
		this.menu_order = menu_order;
	}
	
}
