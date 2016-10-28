package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BusinessPK  
implements Serializable  
{
	private static final long serialVersionUID = 2539076699853171910L;
	
	@Column private int business_id;   //	"int(10) unsigned"	NO	PRI
	@Column private int prospectus_id; //	"int(10) unsigned"	NO	PRI
	@Column private int company_id;    //	"tinyint(3) unsigned"	NO	PRI
	
	public BusinessPK()
	{
		super();
	}
	
	public BusinessPK(int idBusiness,int company_id,int prospectus_id){
		
		this.business_id 	= idBusiness;
		this.company_id 	= company_id;
		this.prospectus_id	= prospectus_id;
		
	}
	
	public int getBusiness_id() {
		return business_id;
	}
	
	public void setBusiness_id(int business_id) {
		this.business_id = business_id;
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
