package mx.com.kubo.model.mesa.solicitud.notas;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PriorityTypePK 
implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@Column
	private int priority_type_id;
	
	@Column
	private int company_id;
	
	private PriorityTypePK(int company_id)
	{
		this.company_id = company_id;
	}
	
	private PriorityTypePK(int priority_type_id, int company_id)
	{
		this.priority_type_id = priority_type_id;
		this.company_id = company_id;
	}
	
	private PriorityTypePK()
	{
		
	}
	
	public int getPriority_type_id() {
		return priority_type_id;
	}
	
	public void setPriority_type_id(int priority_type_id) 
	{
		this.priority_type_id = priority_type_id;
	}
	
	public int getCompany_id() 
	{
		return company_id;
	}
	
	public void setCompany_id(int company_id) 
	{
		this.company_id = company_id;
	}
}
