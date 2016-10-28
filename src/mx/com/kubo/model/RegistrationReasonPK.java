package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RegistrationReasonPK 
implements Serializable 
{
	private static final long serialVersionUID = -5476235285692544862L;
	
	@Column private int registration_reason_id;//	"tinyint(3) unsigned"
	@Column private int company_id;           //	"tinyint(3) unsigned"
	
	public int getRegistration_reason_id() 
	{
		return registration_reason_id;
	}
	
	public void setRegistration_reason_id(int registration_reason_id) 
	{
		this.registration_reason_id = registration_reason_id;
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
