package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Embeddable;


@Embeddable
public class ClabeAccountPK 
implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private int prospectus_id;//	"int(10) unsigned"
	private int company_id;//	"tinyint(3) unsigned"
	private int clabe_account_id;//	"int(10) unsigned"
	
	public ClabeAccountPK()
	{
		super();
	}
	
	public ClabeAccountPK(int prospectus_id, int company_id, int clabe_account_id)
	{
		this.prospectus_id    = prospectus_id;
		this.company_id       = company_id;
		this.clabe_account_id = clabe_account_id;
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
	
	public int getClabe_account_id() {
		return clabe_account_id;
	}
	
	public void setClabe_account_id(int clabe_account_id) {
		this.clabe_account_id = clabe_account_id;
	}	
}
