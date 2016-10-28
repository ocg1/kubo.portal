package mx.com.kubo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="view_client_to_block")
public class InactiveAccount implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	private int prospectus_id;
	@Column(name="ULTIMO_ACCESS")
	private Date last_access;
	
	public int getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(int prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public Date getLast_access() {
		return last_access;
	}
	public void setLast_access(Date last_access) {
		this.last_access = last_access;
	}
	
	
}
