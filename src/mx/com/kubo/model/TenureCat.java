package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@NamedQuery(name = "lisTenureCat", query = " FROM TenureCat t where t.is_enabled = 'S' order by t.menu_order ")
@Entity @Table(name = "gn_tenure")
public class TenureCat 
implements Serializable 
{
	private static final long serialVersionUID = 742304835400373832L;
	
	@EmbeddedId
	private TenureCatPK tenureCatPK;
	
	@Column private String name;
	@Column private  Integer menu_order;
	@Column private  String is_enabled;
	
	public TenureCat()
	{
		super();
	}
	public void setTenureCatPK(TenureCatPK tenureCatPK) 
	{
		this.tenureCatPK = tenureCatPK;
	}

	public void setName(String name) 
	{
		this.name = name;
	}
	
	public TenureCatPK getTenureCatPK()
	{
		return tenureCatPK;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public Integer getMenu_order() 
	{
		return menu_order;
	}
	
	public void setMenu_order(Integer menu_order) 
	{
		this.menu_order = menu_order;
	}
	
	public String getIs_enabled() 
	{
		return is_enabled;
	}
	
	public void setIs_enabled(String is_enabled) 
	{
		this.is_enabled = is_enabled;
	}
}
