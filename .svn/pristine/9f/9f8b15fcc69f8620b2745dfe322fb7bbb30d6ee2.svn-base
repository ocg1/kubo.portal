package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "ln_motive")
public class Motive 
implements Serializable 
{
	@EmbeddedId
	private MotivePK pk;
	
	@Column
	private Integer note_type_id;
	
	@Column
	private Integer status_id;
	
	@Column
	private String description;

	public Integer getStatus_id() 
	{
		return status_id;
	}
	
	public void setStatus_id(Integer status_id) {
		this.status_id = status_id;
	}
	
	public String getDescription() 
	{
		return description;
	}
	
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	public Integer getNote_type_id() 
	{
		return note_type_id;
	}
	
	public void setNote_type_id(Integer note_type_id) 
	{
		this.note_type_id = note_type_id;
	}
	
	public MotivePK getPk() 
	{
		return pk;
	}
	
	public void setPk(MotivePK pk) 
	{
		this.pk = pk;
	}
}
