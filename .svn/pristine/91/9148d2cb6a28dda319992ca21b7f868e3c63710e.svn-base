package mx.com.kubo.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="pr_screen")
public class Screen {
	
	@EmbeddedId
	private ScreenPK screenPK;
	@Column
	private String  name;//	varchar(45)
	@Column
	private String  area;//	varchar(45)
	@Column
	private String  responsible;//	varchar(90)
	@Column
	private String is_obligatory;
	@Column
	private Integer menu_order;
	@Column
	private String resource_name;
	
	public ScreenPK getScreenPK() {
		return screenPK;
	}
	public void setScreenPK(ScreenPK screenPK) {
		this.screenPK = screenPK;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getResponsible() {
		return responsible;
	}
	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}
	public String getIs_obligatory() {
		return is_obligatory;
	}
	public void setIs_obligatory(String is_obligatory) {
		this.is_obligatory = is_obligatory;
	}
	public Integer getMenu_order() {
		return menu_order;
	}
	public void setMenu_order(Integer menu_order) {
		this.menu_order = menu_order;
	}
	public String getResource_name() {
		return resource_name;
	}
	public void setResource_name(String resource_name) {
		this.resource_name = resource_name;
	}

	
}
