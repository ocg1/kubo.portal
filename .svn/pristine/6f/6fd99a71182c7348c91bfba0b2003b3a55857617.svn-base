package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class SystemParamPK  implements Serializable 
{
	private static final long serialVersionUID = 3346777134547146076L;
	
	@Column private Integer system_param_id;
	@Column private Integer company_id;
	
	public SystemParamPK()
	{
		super();
	}
	
	public SystemParamPK(Integer system_param_id,Integer company_id)
	{
		this.system_param_id=system_param_id;
		this.company_id=company_id;
	}
	
	public Integer getSystem_param_id() {
		return system_param_id;
	}
	public void setSystem_param_id(Integer system_param_id) {
		this.system_param_id = system_param_id;
	}
	public Integer getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}
	
}
