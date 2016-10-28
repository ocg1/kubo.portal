package mx.com.kubo.model.mesa.solicitud.busqueda;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.QueryHint;
import javax.persistence.Table;

import mx.com.kubo.model.ContratoCreditoCollector;

@NamedNativeQueries({
	@NamedNativeQuery(
			resultClass = ClientViewFullName.class,
			name = "collectorClientViewFullName",    
			 
			// query = "	call microfin.CONTRATOSREP(100005328,86.1645,1,1,1,'2016-02-08 19:11:00','192.0.0.0','kubofinanciero.generaContratos',1,0)
			
			query = "call GETCLIENTVIEWFULLNAME(:strLIKE)",
			
			hints = {    
			   			@QueryHint (name = "org.hibernate.callable", value = "true")  
					}    
	
	)
})

@Entity @Table(name = "view_full_name")
public final class ClientViewFullName 
implements Serializable
{
	private static final long serialVersionUID = -1808493031884581748L;

	@Id 
	@Column private Integer prospectus_id;
	
	@Column private String email;
	@Column private String full_name;	
	@Column private String tracking_id;
	
	/* 
	  select 
	  	fn.prospectus_id as prospectus_id,  
		fn.email as email, 
		fn.full_name as fullname,
		'TRACKIN' AS tracking_id
		from 
			gn_full_name fn join 
			gn_prospectus pr 
				on (fn.prospectus_id = pr.prospectus_id)
		where 
			(fn.full_name like '%"+strLIKE+"%')  and pr.area='I' limit 30;
	 * */

	public ClientViewFullName()
	{
		super();
	}
	
	public ClientViewFullName(Integer prospectus_id, String email, String full_name, String tracking_id) 
	{
		this.prospectus_id = prospectus_id;
		this.email         = email;
		this.full_name     = full_name;
		this.tracking_id   = tracking_id;
	}

	public Integer getProspectus_id() 
	{
		return prospectus_id;
	}

	public void setProspectus_id(Integer prospectus_id) 
	{
		this.prospectus_id = prospectus_id;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getFull_name() 
	{
		return full_name;
	}

	public void setFull_name(String full_name) 
	{
		this.full_name = full_name;
	}

	public String getTracking_id() 
	{
		return tracking_id;
	}

	public void setTracking_id(String tracking_id) 
	{
		this.tracking_id = tracking_id;
	}
}
