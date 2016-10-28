package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AddressPK 
implements Serializable 
{
	private static final long serialVersionUID = 3769076958789844178L;
	
	@Column private int address_id;//	"int(10) unsigned"	
	@Column private int prospectus_id;//	"int(10) unsigned"	
	@Column private int company_id;//	"tinyint(3) unsigned
	
	public AddressPK()
	{
		super();
	}
	
	public AddressPK(int address_id,int prospectus_id,int company_id)
	{
		this.address_id    = address_id;
		this.prospectus_id = prospectus_id;
		this.company_id    = company_id;		
	}
	
	public int getAddress_id() {
		return address_id;
	}
	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}
	public int getProspectus_id() {
		return prospectus_id;
	}
	public void setProspectus_id(int prospectus_id) {
		this.prospectus_id = prospectus_id;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
}
