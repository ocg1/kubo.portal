package mx.com.kubo.model.mesa.solicitud.notas;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gn_priority_type")
public class PriorityType 
implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private PriorityTypePK priorityTypePK;
	
	@Column
	private String name;
	
	public PriorityTypePK getPriorityTypePK() 
	{
		return priorityTypePK;
	}
	
	public void setPriorityTypePK(PriorityTypePK priorityTypePK) 
	{
		this.priorityTypePK = priorityTypePK;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
}
