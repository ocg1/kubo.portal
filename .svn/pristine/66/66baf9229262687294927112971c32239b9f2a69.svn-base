package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gn_reference_motive")
public class ReferencesMotive implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ReferencesMotivePK pk;
	@Column
	private String description;
	@Column
	private Integer menu_order;
	@Column
	private String is_enabled;
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getMenu_order() {
		return menu_order;
	}
	public void setMenu_order(Integer menu_order) {
		this.menu_order = menu_order;
	}
	public String getIs_enabled() {
		return is_enabled;
	}
	public void setIs_enabled(String is_enabled) {
		this.is_enabled = is_enabled;
	}
	public ReferencesMotivePK getPk() {
		return pk;
	}
	public void setPk(ReferencesMotivePK pk) {
		this.pk = pk;
	}
	
}
