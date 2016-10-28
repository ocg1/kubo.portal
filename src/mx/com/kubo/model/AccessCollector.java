package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class AccessCollector 
implements Serializable
{
	
	@Column
	private Integer company_id;
	
	@Column
	private String 	name;
	
	@Column
	private Integer menu_order;
	
	@Id
	private Integer screen_id;
	
	@Column
	private String 	resource_name;
	
	@Column
	private Integer access_id;
	
	@Column
	private Integer percentage;
	
	@Column
	private String is_obligatory;
	
	public Integer getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getMenu_order() {
		return menu_order;
	}
	public void setMenu_order(Integer menu_order) {
		this.menu_order = menu_order;
	}
	public Integer getScreen_id() {
		return screen_id;
	}
	public void setScreen_id(Integer screen_id) {
		this.screen_id = screen_id;
	}
	public String getResource_name() {
		return resource_name;
	}
	public void setResource_name(String resource_name) {
		this.resource_name = resource_name;
	}
	public Integer getAccess_id() {
		return access_id;
	}
	public void setAccess_id(Integer access_id) {
		this.access_id = access_id;
	}
	public Integer getPercentage() {
		return percentage;
	}
	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}
	public String getIs_obligatory() {
		return is_obligatory;
	}
	public void setIs_obligatory(String is_obligatory) {
		this.is_obligatory = is_obligatory;
	}
	
}
