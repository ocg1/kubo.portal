package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="pr_event_token_access")
public class EventTokenAccess implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@EmbeddedId
	private EventTokenAccessPK pk;
	@Column
	private String event_token_desc;
	@Column
	private String area;
	@Column
	private String is_active;
	
	
	public EventTokenAccessPK getPk() {
		return pk;
	}
	public void setPk(EventTokenAccessPK pk) {
		this.pk = pk;
	}
	public String getEvent_token_desc() {
		return event_token_desc;
	}
	public void setEvent_token_desc(String event_token_desc) {
		this.event_token_desc = event_token_desc;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getIs_active() {
		return is_active;
	}
	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}
	
}
