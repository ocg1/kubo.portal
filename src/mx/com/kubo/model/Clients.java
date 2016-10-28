package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @Table(name = "gn_client")
public class Clients implements Serializable 
{
	private static final long serialVersionUID = 1904621339421857966L;

	@EmbeddedId private ClientsPK pk;
	
	@Column	private String safi_client_id;
	
	public ClientsPK getPk() 
	{
		return pk;
	}
	
	public void setPk(ClientsPK pk) 
	{
		this.pk = pk;
	}
	
	public String getSafi_client_id() 
	{
		return safi_client_id;
	}
	
	public void setSafi_client_id(String safi_client_id) 
	{
		this.safi_client_id = safi_client_id;
	}
}
