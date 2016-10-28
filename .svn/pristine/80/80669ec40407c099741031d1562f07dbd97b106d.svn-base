package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@NamedNativeQueries(
{
	@NamedNativeQuery(	name = "findViewEconomicInfo",     query = "select * from view_economic_info where description like :query", resultClass = ViewEconomicInfo.class),
	@NamedNativeQuery(	name = "findViewEconomicInfoByID", query = "select * from view_economic_info where  bmx_econ_activity_id=?", resultClass = ViewEconomicInfo.class)
})

@Entity
public class ViewEconomicInfo 
implements Serializable 
{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column private String  bmx_econ_activity_id;
	@Column private String  description;
	
	@Column private Integer inegi_econ_activity_id;
	@Column private Integer econ_sector_id;
	
	public String getBmx_econ_activity_id() {
		return bmx_econ_activity_id;
	}
	
	public void setBmx_econ_activity_id(String bmx_econ_activity_id) {
		this.bmx_econ_activity_id = bmx_econ_activity_id;
	}
	
	public Integer getInegi_econ_activity_id() {
		return inegi_econ_activity_id;
	}
	
	public void setInegi_econ_activity_id(Integer inegi_econ_activity_id) {
		this.inegi_econ_activity_id = inegi_econ_activity_id;
	}
	
	public Integer getEcon_sector_id() {
		return econ_sector_id;
	}
	
	public void setEcon_sector_id(Integer econ_sector_id) {
		this.econ_sector_id = econ_sector_id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
}
