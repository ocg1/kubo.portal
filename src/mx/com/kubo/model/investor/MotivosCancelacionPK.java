package mx.com.kubo.model.investor;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MotivosCancelacionPK 
implements Serializable
{
	private static final long serialVersionUID = 3536981027149654176L;
	
	@Column private Integer company_id;
	@Column private Integer cancellation_motive_id;
	@Column private Integer product_type_id;
	
	public final Integer getCompany_id() 
	{
		return company_id;
	}
	
	public final Integer getCancellation_motive_id() 
	{
		return cancellation_motive_id;
	}
	
	public final Integer getProduct_type_id() 
	{
		return product_type_id;
	}
	
	public final void setCompany_id(Integer company_id) 
	{
		this.company_id = company_id;
	}
	
	public final void setCancellation_motive_id(Integer cancellation_motive_id) 
	{
		this.cancellation_motive_id = cancellation_motive_id;
	}
	
	public final void setProduct_type_id(Integer product_type_id) 
	{
		this.product_type_id = product_type_id;
	}
}
