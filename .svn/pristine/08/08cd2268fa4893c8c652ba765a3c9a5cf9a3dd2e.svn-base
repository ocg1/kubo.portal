package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="gn_role_access")
public class RoleAccess implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private RoleAccessPK pk;
	@Column
	private String description;
	
	@ManyToOne
	@JoinColumns(value = {
	        
			@JoinColumn(name = "screen_id", referencedColumnName = "screen_id", insertable = false, updatable = false),
	        @JoinColumn(name = "company_id", referencedColumnName = "company_id", insertable = false, updatable = false)
			
	    })	
	private Screen screen;
	
	public RoleAccessPK getPk() {
		return pk;
	}
	public void setPk(RoleAccessPK pk) {
		this.pk = pk;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Screen getScreen() {
		return screen;
	}
	public void setScreen(Screen screen) {
		this.screen = screen;
	}

}
