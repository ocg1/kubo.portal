package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ln_alert")
public class Alert implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private AlertPK pk;
	@Column
	private String description;
	@Column
	private String tooltip;
	@Column	
	private String is_selectable;
	
	public AlertPK getPk() {
		return pk;
	}
	public void setPk(AlertPK pk) {
		this.pk = pk;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTooltip() {
		return tooltip;
	}
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}
	public String getIs_selectable() {
		return is_selectable;
	}
	public void setIs_selectable(String is_selectable) {
		this.is_selectable = is_selectable;
	}
	
}
