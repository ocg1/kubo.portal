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
			resultClass = UltimosDesembolsos.class,
			name = "getUltimosDesembolsos",    
			query = "call GETULTIMOSCREDITOSDESEMBOLSADOS()",
			hints = {    
			   			@QueryHint(name = "org.hibernate.callable", value = "true")  
					}    
	
	)
})


@Entity
public class UltimosDesembolsos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	private Integer safi_credit_id;
	@Column
	private Integer prospectus_id;
	@Column
	private Integer hs_vid;
	@Column
	private String email;
	
	
	public Integer getHs_vid() {
		return hs_vid;
	}
	public void setHs_vid(Integer hs_vid) {
		this.hs_vid = hs_vid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getSafi_credit_id() {
		return safi_credit_id;
	}
	public void setSafi_credit_id(Integer safi_credit_id) {
		this.safi_credit_id = safi_credit_id;
	}
	public Integer getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	
}