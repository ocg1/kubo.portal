package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ln_status")
public class StatusProyectCat 
implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private StatusProyectCatPK statusPK;
	
	@Column
	private String name;
	
	@Column
	private String url_img;
	
	@Column
	private String is_enabled;
	
	@Column
	private String is_changeable;
	
	public StatusProyectCat(){
		
	}

	public StatusProyectCatPK getStatusPK() {
		return statusPK;
	}

	public void setStatusPK(StatusProyectCatPK statusPK) {
		this.statusPK = statusPK;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getUrl_img() {
		return url_img;
	}

	public void setUrl_img(String url_img) {
		this.url_img = url_img;
	}

	public String getIs_enabled() {
		return is_enabled;
	}

	public void setIs_enabled(String is_enabled) {
		this.is_enabled = is_enabled;
	}

	public String getIs_changeable() {
		return is_changeable;
	}

	public void setIs_changeable(String is_changeable) {
		this.is_changeable = is_changeable;
	}
}
