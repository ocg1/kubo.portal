package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.QueryHint;

@NamedNativeQueries({
	@NamedNativeQuery(
			resultClass = EconActivityByInv.class,
			name		= "getEconActivityByInv",
			query 		= "call KUBOGRAFICASCON(:safi_client_id,:cuentaAhoID,3 )",
			hints		= {
							@QueryHint(name="org.hibernate.callable", value="true")
						  }
	)
})

@Entity
public class EconActivityByInv implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Column(name="ClienteID")
	private Integer 	prospectus_id;
	@Id
	private String 		description;
	@Column
	private Integer		quantity;
	
	public Integer getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}
