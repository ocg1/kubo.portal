package mx.com.kubo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FieldsPK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column
	private int screen_id;
	@Column
	private int company_id;
	@Column
	private int component_id;
	
	public FieldsPK(){
		
	}

	public int getScreen_id() {
		return screen_id;
	}

	public void setScreen_id(int screen_id) {
		this.screen_id = screen_id;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	public int getComponent_id() {
		return component_id;
	}

	public void setComponent_id(int component_id) {
		this.component_id = component_id;
	}
	
	
	
}
