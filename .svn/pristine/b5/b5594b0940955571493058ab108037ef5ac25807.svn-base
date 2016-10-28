package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;

@NamedNativeQuery(name = "viewClient", query = "select * from  view_full_name where full_name is not null and tracking_id is not null and full_name like :query", resultClass = ClientView.class)
@Entity
public class ClientView implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column
	private String full_name;
	@Column
	private String tracking_id;
	@Id
	@Column
	private Integer prospectus_id;
	
	public ClientView(){
		
	}
	
	public ClientView(String full_name,String tracking_id,Integer prospectus_id){
		this.full_name=full_name;
		this.tracking_id=tracking_id;
		this.prospectus_id=prospectus_id;
	}
	
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getTracking_id() {
		return tracking_id;
	}
	public void setTracking_id(String tracking_id) {
		this.tracking_id = tracking_id;
	}
	public Integer getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(Integer prospectus_id) {
		this.prospectus_id = prospectus_id;
	}

}
