package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ln_alert_motive")
public class AlertMotive implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private AlertMotivePK pk;
	@Column
	private String description;
	
	public AlertMotivePK getPk() {
		return pk;
	}
	public void setPk(AlertMotivePK pk) {
		this.pk = pk;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
}
