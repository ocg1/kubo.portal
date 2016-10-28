package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Change_controlPK 
implements Serializable 
{
	private static final long serialVersionUID = -8441615792243346723L;
	
	@Column private int prospectus_id;
	@Column private int company_id;
	@Column private int change_id;
	
	public Change_controlPK()
	{
		super();
	}
	
	public Change_controlPK(int prospectus_id,int company_id, int change_id)
	{
		this.prospectus_id = prospectus_id;
		this.company_id    = company_id;
		this.change_id     = change_id;
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
	public int getChange_id() {
		return change_id;
	}
	public void setChange_id(int change_id) {
		this.change_id = change_id;
	}
	

}
