package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.QueryHint;

@NamedNativeQueries({
	@NamedNativeQuery(
			resultClass = AyudaDocumentos.class,
			name = "ayudaDocumentos_SP",
			query = "call documentation_screen_con(:dias)",
			hints = {
			   			@QueryHint(name = "org.hibernate.callable", value = "true")
					}
	
	)
})


@Entity
public class AyudaDocumentos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer access_id;
	@Column
	private Integer prospectus_id;
	@Column
	private Date access_datetime;
	@Column
	private Integer screen_id;
	
	public Integer getAccess_id() {
		return access_id;
	}
	public void setAccess_id(Integer access_id) {
		this.access_id = access_id;
	}
	public Integer getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public Date getAccess_datetime() {
		return access_datetime;
	}
	public void setAccess_datetime(Date access_datetime) {
		this.access_datetime = access_datetime;
	}
	public Integer getScreen_id() {
		return screen_id;
	}
	public void setScreen_id(Integer screen_id) {
		this.screen_id = screen_id;
	}
	
}

