package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="pr_info_screen")
public class InfoScreen implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private InfoScreenPK pk;
	@Column
	private String description; //` VARCHAR(45) NULL,
	@Column
	private String is_enabled;// 
	
	public InfoScreenPK getPk() {
		return pk;
	}
	public void setPk(InfoScreenPK pk) {
		this.pk = pk;
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
